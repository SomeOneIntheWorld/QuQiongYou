package dream.quqiongyou.main.widget;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import dream.quqiongyou.R;
import dream.quqiongyou.bean.QuUser;
import dream.quqiongyou.community.view.CommunityFragment;
import dream.quqiongyou.home.fragment.HomeFragment;
import dream.quqiongyou.mine.MineFragment;

/**
 * Created by SomeOneInTheWorld on 2016/10/3.
 */
public class MainActivity extends AppCompatActivity {
    private final static String USER = "user";
    @BindView(R.id.main_viewpager) ViewPager mViewPager;
    @BindView(R.id.home_ll) LinearLayout homeLl;
    @BindView(R.id.community_ll) LinearLayout communityLl;
    @BindView(R.id.mine_ll) LinearLayout mineLl;
    @BindView(R.id.toolbar_title) TextView toolbarTitle;
    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.home_img) ImageView homeImg;
    @BindView(R.id.community_img) ImageView communityImg;
    @BindView(R.id.mine_img) ImageView mineImg;
    private MainPagerAdapter mMainPagerAdapter;
    private SparseArray<Fragment> fragmentList = new SparseArray<>();
    private Unbinder unbinder;

    public static void startMainActivity(Context context, QuUser user) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra(USER, user);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        unbinder = ButterKnife.bind(this);

        mMainPagerAdapter = new MainPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mMainPagerAdapter);
        mViewPager.setOnPageChangeListener(vpSlide);

    }

    public ViewPager.OnPageChangeListener vpSlide = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }
        @Override
        public void onPageScrollStateChanged(int state) {

        }
        @Override
        public void onPageSelected(int arg0) {
            // TODO Auto-generated method stub
            if (mViewPager.getCurrentItem() == 0) {
                changeSelection(0);
            } else if (mViewPager.getCurrentItem() == 1) {
                changeSelection(1);
            } else if (mViewPager.getCurrentItem() == 2) {
                changeSelection(2);
            }
        }
    };

    /**
     * 设置选项图案变化
     * @param selectedIndex
     */
    private void changeSelection(int selectedIndex) {
        switch (selectedIndex) {
            case 0:
                homeImg.setImageResource(R.mipmap.home_pressed);
                communityImg.setImageResource(R.mipmap.community_normal);
                mineImg.setImageResource(R.mipmap.mine_normal);
                break;
            case 1:
                homeImg.setImageResource(R.mipmap.home_normal);
                communityImg.setImageResource(R.mipmap.community_pressed);
                mineImg.setImageResource(R.mipmap.mine_normal);
                break;
            case 2:
                homeImg.setImageResource(R.mipmap.home_normal);
                communityImg.setImageResource(R.mipmap.community_normal);
                mineImg .setImageResource(R.mipmap.mine_pressed);
                break;
        }
    }

    @OnClick({R.id.home_ll, R.id.community_ll, R.id.mine_ll})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.home_ll:
                changeSelection(0);
                changeFragment(0);
                break;
            case R.id.community_ll:
                changeSelection(1);
                changeFragment(1);
                break;
            case R.id.mine_ll:
                changeSelection(2);
                changeFragment(2);
                break;
        }
    }

    private void changeFragment(int position){
        mViewPager.setCurrentItem(position);
    }

    private class MainPagerAdapter extends FragmentStatePagerAdapter {
        private static final int NUM_PAGES = 3;

        public MainPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return getFragment(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            //super.destroyItem(container, position, object);
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }

        private Fragment getFragment(int position){
            Fragment fragment = fragmentList.get(position);
            if(fragment != null){
                return fragment;
            }
            switch (position) {
                case 0:
                    fragment = new HomeFragment();
                    break;
                case 1:
                    fragment = new CommunityFragment();
                    break;
                case 2:
                    fragment = new MineFragment();
                    break;
                default:
                    fragment = new HomeFragment();
                    break;
            }
            fragmentList.put(position,fragment);
            return fragment;
        }
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        unbinder.unbind();
    }
}
