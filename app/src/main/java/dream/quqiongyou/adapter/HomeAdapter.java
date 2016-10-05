package dream.quqiongyou.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dream.quqiongyou.R;
import dream.quqiongyou.bean.HomeItemBean;
import dream.quqiongyou.bean.TopInfo;
import dream.quqiongyou.home.widget.Slider;
import dream.quqiongyou.origination.view.OriginationActivity;
import dream.quqiongyou.utils.ImageLoaderUtils;
import dream.quqiongyou.utils.LogUtils;

/**
 * Created by SomeOneInTheWorld on 2016/10/3.
 */
public class HomeAdapter extends RecyclerView.Adapter {
    private final static String TAG = "test";
    private List<HomeItemBean> homeDatas;
    private List<TopInfo> topInfos;
    private Context context;
    private final int TYPE_FOOTER = 0;
    private final int TYPE_HEAD = 1;
    private final int TYPE_ITEM = 2;
    private boolean isShowFooter = true;

    public HomeAdapter(Context context){
        this.context = context;
    }

    public void setHomeData(List<HomeItemBean>homeDatas,List<TopInfo>topInfos){
        this.homeDatas = homeDatas;
        this.topInfos = topInfos;
//        notifyDataSetChanged();
    }

    public void isShowFooter(boolean showFooter){
        this.isShowFooter = showFooter;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType == TYPE_ITEM){
            View item = LayoutInflater.from(context).inflate(R.layout.item_home_normal,null);
            return new ItemViewHolder(item);
        }else if(viewType == TYPE_FOOTER){
            View v = LayoutInflater.from(context).inflate(R.layout.footer,null);
            v.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));
            FooterViewHolder fvh = new FooterViewHolder(v);
            return fvh;
        }else{
            View v = LayoutInflater.from(context).inflate(R.layout.item_home_head,null);
            return new HeadViewHolder(v);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof ItemViewHolder){
            HomeItemBean itemBean = homeDatas.get(position);
            String time = itemBean.getLefttime();
            ((ItemViewHolder)holder).titleTV.setText(itemBean.getTitle());
            ((ItemViewHolder)holder).hourTV.setText(time);
            ((ItemViewHolder)holder).minTV.setText(time);
            ((ItemViewHolder)holder).secondTV.setText(time);
            if(itemBean.getImageurl() == null){
                ((ItemViewHolder)holder).tripIV.setImageResource(R.mipmap.community_normal);
                return;
            }
            ImageLoaderUtils.display(context, ((ItemViewHolder)holder).tripIV,itemBean.getImageurl());
        }else if (holder instanceof HeadViewHolder){
            ((HeadViewHolder)holder).slider.setTopInfos(topInfos);
        }
    }

    @Override
    public int getItemViewType(int position){
        if(!isShowFooter && position == 0){
            return TYPE_HEAD;
        }else if(!isShowFooter){
            return TYPE_ITEM;
        }

        if(position + 1 == getItemCount()){
            return TYPE_FOOTER;
        }else{
            return TYPE_ITEM;
        }
    }

    @Override
    public int getItemCount(){
        int begin = isShowFooter ? 1 : 0;
        if(homeDatas == null){
            LogUtils.d(TAG,"item is null");
            return begin;
        }else{
            return homeDatas.size() + begin;
        }
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.join)TextView joinTV;
        @BindView(R.id.img) ImageView tripIV;
        @BindView(R.id.title) TextView titleTV;
        @BindView(R.id.hour) TextView hourTV;
        @BindView(R.id.min) TextView minTV;
        @BindView(R.id.second) TextView secondTV;

        public ItemViewHolder(View v){
            super(v);
            ButterKnife.bind(this,v);
        }

        @OnClick(R.id.join)
        void onClickJoin(){
            Toast.makeText(context, "lalla", Toast.LENGTH_SHORT).show();
        }
    }

    public class FooterViewHolder extends RecyclerView.ViewHolder {
        public FooterViewHolder(View view){
            super(view);
        }
    }

    public class HeadViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.slider)Slider slider;
        @BindView(R.id.findtrips)View findTripsView;
        @BindView(R.id.createtrips)View createTripsView;

        public HeadViewHolder(View header){
            super(header);
            ButterKnife.bind(this,header);
        }

        @OnClick({R.id.slider,R.id.findtrips,R.id.createtrips})
        void onClick(View view){
            switch(view.getId()){
                case R.id.slider:
                    break;
                case R.id.findtrips:
                    break;
                case R.id.createtrips:
                    OriginationActivity.startOriginationActivity(context);
                    break;
            }
        }
    }
}
