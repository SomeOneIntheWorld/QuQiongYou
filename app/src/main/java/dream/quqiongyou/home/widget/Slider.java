package dream.quqiongyou.home.widget;

import android.content.Context;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.TextView;



import java.util.ArrayList;
import java.util.List;

import dream.quqiongyou.R;
import dream.quqiongyou.bean.TopInfo;
import dream.quqiongyou.utils.ImageLoaderUtils;


public class Slider extends FrameLayout implements OnClickListener {
    private List<TopInfo> topInfos;
    private List<View> views;
    private Context context;
    private ViewPager vp;
    private boolean isAutoPlay;
    private int currentItem;
    private int delayTime = 2000;
    private LinearLayout ll_dot;
    private List<ImageView> iv_dots;
    private Handler handler = new Handler();
    private OnItemClickListener mItemClickListener;
    private boolean isRestart = false;

    public Slider(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.context = context;
        this.topInfos = new ArrayList<>();
        currentItem = -1;
        initView();
    }

    private void initView() {
        views = new ArrayList<View>();
        iv_dots = new ArrayList<ImageView>();
    }

    public Slider(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Slider(Context context) {
        this(context, null);
    }

    public void setTopInfos(List<TopInfo> topInfos) {
        this.topInfos = topInfos;
        reset();
    }

    private void reset() {
        views.clear();
        iv_dots.clear();
        initUI();
    }

    private void initUI() {
        View view = LayoutInflater.from(context).inflate(
                R.layout.slider_layout, this, true);
        vp = (ViewPager) view.findViewById(R.id.vp);
        ll_dot = (LinearLayout) view.findViewById(R.id.ll_dot);
        ll_dot.removeAllViews();

        int len = topInfos.size();
        if (len == 0)
            return;
        for (int i = 0; i < len; i++) {
            ImageView iv_dot = new ImageView(context);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            params.leftMargin = 5;
            params.rightMargin = 5;
            ll_dot.addView(iv_dot, params);
            iv_dots.add(iv_dot);
        }

        for (int i = 0; i <= len + 1; i++) {
            View fm = LayoutInflater.from(context).inflate(
                    R.layout.slider_content_layout, null);
            ImageView iv = (ImageView) fm.findViewById(R.id.iv_title);
            TextView tv_title = (TextView) fm.findViewById(R.id.tv_title);
            iv.setScaleType(ScaleType.CENTER_CROP);
            if (i == 0) {
                ImageLoaderUtils.display(iv.getContext(),iv,topInfos.get(len - 1).getImage());
                tv_title.setText(topInfos.get(len - 1).getTitle());
            } else if (i == len + 1) {
                ImageLoaderUtils.display(iv.getContext(),iv,topInfos.get(0).getImage());
                tv_title.setText(topInfos.get(0).getTitle());
            } else {
                ImageLoaderUtils.display(iv.getContext(),iv,topInfos.get(i - 1).getImage());
                tv_title.setText(topInfos.get(i - 1).getTitle());
            }
            fm.setOnClickListener(this);
            views.add(fm);
        }
        vp.setAdapter(new MyPagerAdapter());
        vp.setFocusable(true);
        vp.setCurrentItem(currentItem == -1 ? 1 : currentItem);
        currentItem = 1;
        vp.addOnPageChangeListener(new MyOnPageChangeListener());
        if (!isRestart)
            startPlay();
    }

    private void startPlay() {
        isAutoPlay = true;
        isRestart = true;
        handler.postDelayed(task, delayTime);
    }


    private final Runnable task = new Runnable() {

        @Override
        public void run() {
            if (isAutoPlay) {
                currentItem = currentItem % (topInfos.size() + 1) + 1;
                if (currentItem == 1) {
                    vp.setCurrentItem(currentItem, false);
                    handler.post(task);
                } else {
                    vp.setCurrentItem(currentItem);
                    handler.postDelayed(task, 5000);
                }
            } else {
                handler.postDelayed(task, 5000);
            }
        }
    };

    class MyPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return views.size();
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(views.get(position));
            return views.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

    }

    class MyOnPageChangeListener implements OnPageChangeListener {

        @Override
        public void onPageScrollStateChanged(int arg0) {
            switch (arg0) {
                case 1:
                    isAutoPlay = false;
                    break;
                case 2:
                    isAutoPlay = true;
                    break;
                case 0:
                    if (vp.getCurrentItem() == 0) {
                        vp.setCurrentItem(topInfos.size(), false);
                    } else if (vp.getCurrentItem() == topInfos.size() + 1) {
                        vp.setCurrentItem(1, false);
                    }
                    currentItem = vp.getCurrentItem();
                    isAutoPlay = true;
                    break;
            }
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }

        @Override
        public void onPageSelected(int arg0) {
            for (int i = 0; i < iv_dots.size(); i++) {
                if (i == arg0 - 1) {
                    iv_dots.get(i).setImageResource(R.drawable.dot_focus);
                } else {
                    iv_dots.get(i).setImageResource(R.drawable.dot_blur);
                }
            }

        }

    }


    public void setOnItemClickListener(OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    public interface OnItemClickListener {
        void click(View v,TopInfo entity);
    }

    @Override
    public void onClick(View v) {
        if (mItemClickListener != null) {
            TopInfo entity = topInfos.get(vp.getCurrentItem() - 1);
            mItemClickListener.click(v, entity);
        }
    }
}
