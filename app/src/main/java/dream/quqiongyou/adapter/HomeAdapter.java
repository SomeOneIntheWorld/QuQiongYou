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

/**
 * Created by SomeOneInTheWorld on 2016/10/3.
 */
public class HomeAdapter extends BaseAdapter {
    private final static String TAG = "testttxt";
    private List<HomeItemBean> homeDatas;
    private List<TopInfo> topInfos;

    public HomeAdapter(Context context){
        super(context);
    }

    public void setHomeData(List<HomeItemBean>homeDatas,List<TopInfo>topInfos){
        this.homeDatas = homeDatas;
        this.topInfos = topInfos;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType == TYPE_NORMAL){
            View item = LayoutInflater.from(context).inflate(R.layout.item_home_normal,parent,false);
            return new ItemViewHolder(item);
        }else if(viewType == TYPE_HEAD){
            View v = LayoutInflater.from(context).inflate(R.layout.item_home_head,parent,false);
            return new HeadViewHolder(v);
        }else{
            return super.onCreateViewHolder(parent, viewType);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof ItemViewHolder){
            position = position - 1;
            HomeItemBean itemBean = homeDatas.get(position);

            int[]time = getLeftTime(itemBean.getEndtime(),itemBean.getBegintime());

            ((ItemViewHolder)holder).titleTV.setText(itemBean.getAcname());
            ((ItemViewHolder)holder).hourTV.setText(String.valueOf(time[0]));
            ((ItemViewHolder)holder).minTV.setText(String.valueOf(time[1]));
            ((ItemViewHolder)holder).secondTV.setText(String.valueOf(time[2]));
            if(itemBean.getImageurl() == null){
                ((ItemViewHolder)holder).tripIV.setBackgroundResource(R.mipmap.pictures_of_the_event_2);
                return;
            }
            ImageLoaderUtils.display(context, ((ItemViewHolder)holder).tripIV,itemBean.getImageurl());
        }else if (holder instanceof HeadViewHolder) {
            ((HeadViewHolder) holder).slider.setTopInfos(topInfos);
        }else{
            super.onBindViewHolder(holder, position);
        }
    }

    private int[] getLeftTime(String startTime,String endTime){
        int endDay = Integer.parseInt(endTime.substring(endTime.lastIndexOf("-") + 1,endTime.lastIndexOf("-") + 3));
        int endHour = Integer.parseInt(endTime.substring(endTime.indexOf(" ") + 1,endTime.indexOf(" ") + 3));
        int endMinute = Integer.parseInt(endTime.substring(endTime.indexOf(":") + 1,endTime.indexOf(":") + 3));
        int endSecond = Integer.parseInt(endTime.substring(endTime.lastIndexOf(":") + 1,endTime.lastIndexOf(":") + 3));
        int startDay = Integer.parseInt(startTime.substring(startTime.lastIndexOf("-") + 1,startTime.lastIndexOf("-") + 3));
        int startHour = Integer.parseInt(startTime.substring(startTime.indexOf(" ") + 1,startTime.indexOf(" ") + 3));
        int startMinute = Integer.parseInt(startTime.substring(startTime.indexOf(":") + 1,startTime.indexOf(":") + 3));
        int startSecond = Integer.parseInt(startTime.substring(startTime.lastIndexOf(":") + 1,startTime.lastIndexOf(":") + 3));
        int allLeftTime = (endDay - startDay) * 86400
                        + (endHour - startHour) * 3600
                        + (endMinute - startMinute) * 60
                        + (endSecond - startSecond);

        int[]time = new int[3];
        time[0] = allLeftTime / 3600;
        time[1] = (allLeftTime - time[0] * 3600) / 60;
        time[2] = allLeftTime - time[0] * 3600 - time[1] * 60;
        return time;
    }

    @Override
    public int getItemViewType(int position){
        if(position == 0) {
            return TYPE_HEAD;
        }
        if(position  == homeDatas.size() + 1){
            return TYPE_FOOTER;
        }else if(position <= homeDatas.size()){
            return TYPE_NORMAL;
        }else{
            return TYPE_OUT;
        }
    }

    @Override
    public int getItemCount(){
        int begin = 2;//header and footer
        if(homeDatas == null){
            return begin;
        }else{
            return homeDatas.size() + begin;
        }
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.join)TextView joinTV;
        @BindView(R.id.img) ImageView tripIV;
        @BindView(R.id.acname) TextView titleTV;
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
