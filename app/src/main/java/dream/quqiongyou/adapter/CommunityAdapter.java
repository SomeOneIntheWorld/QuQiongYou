package dream.quqiongyou.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dream.quqiongyou.R;
import dream.quqiongyou.bean.TopInfo;
import dream.quqiongyou.bean.TopicBean;
import dream.quqiongyou.home.widget.Slider;

/**
 * Created by SomeOneInTheWorld on 2016/10/4.
 */
public class CommunityAdapter extends RecyclerView.Adapter{
    private List<TopicBean> communityDatas;
    private List<TopicBean> guessDatas;
    private List<TopInfo> topInfos;
    private Context context;
    private final static int TYPE_GUESS = 1;
    private final static int TYPE_TOPIC = 2;
    private final static int TYPE_TEXT = 3;
    private final static int TYPE_OUT = 4;
    private final static int TYPE_SLIDER = 5;

    public CommunityAdapter(Context context){
        this.context = context;
    }

    public void setDatas(List<TopicBean>communityDatas, List<TopicBean>guessDatas){
        this.communityDatas = communityDatas;
        this.guessDatas = guessDatas;
    }

    public void setTopInfos(List<TopInfo>topInfos){
        this.topInfos =topInfos;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType == TYPE_TOPIC || viewType == TYPE_GUESS){
            View item = LayoutInflater.from(context).inflate(R.layout.item_community,parent,false);
            return new CommunityViewHolder(item);
        }else if(viewType == TYPE_TEXT){
            View item = LayoutInflater.from(context).inflate(R.layout.item_community,parent,false);
            ViewStub viewStub = (ViewStub)item.findViewById(R.id.stub);
            viewStub.inflate();
            return new CommunityViewHolder(item);
        }else if(viewType == TYPE_SLIDER){
            View item = LayoutInflater.from(context).inflate(R.layout.item_community_head,parent,false);
            return new HeadViewHolder(item);
        }else{
            return null;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int type = getItemViewType(position);
        TopicBean topicBean;
        if(holder instanceof HeadViewHolder){
            ((HeadViewHolder) holder).slider.setTopInfos(topInfos);
            return;
        }
        position = position - 1;
        if(type == TYPE_TOPIC) {
            topicBean = communityDatas.get(position);
        }else if(type == TYPE_GUESS || type == TYPE_TEXT) {
            topicBean = guessDatas.get(position - communityDatas.size());
        }else{
            return;
        }

        ((CommunityViewHolder)holder).titleTV.setText(topicBean.getTitle());
        ((CommunityViewHolder)holder).topnumTV.setText(topicBean.getTopicnum());
        ((CommunityViewHolder)holder).menuIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,"you click the menu",Toast.LENGTH_SHORT).show();
            }
        });

//        if(topicBean.getImgurl() == null){
//            ((CommunityViewHolder)holder).communityIV.setImageResource(R.mipmap.community_normal);
//            return;
//        }
//        ImageLoaderUtils.display(context,((CommunityViewHolder)holder).communityIV, topicBean.getImgurl());
        ((CommunityViewHolder)holder).communityIV.setBackgroundResource(topicBean.getImgId());
    }

    @Override
    public int getItemViewType(int position) {
        if(position == 0){
            return TYPE_SLIDER;
        }
        if(position > 0 && position <= communityDatas.size()){
            return TYPE_TOPIC;
        }
        if(position == communityDatas.size() + 1){
            return TYPE_TEXT;
        }
        if(position > communityDatas.size() + 1 && position <= communityDatas.size() + guessDatas.size()){
            return TYPE_GUESS;
        }
        return TYPE_OUT;
    }

    @Override
    public int getItemCount() {
        if(communityDatas.size() == 0 && guessDatas.size() == 0){
            return 1;
        }
        if(communityDatas.size() == 0){
            return guessDatas.size() + 1;
        }
        if(guessDatas.size() == 0){
            return communityDatas.size() + 1;
        }
        return communityDatas.size() + guessDatas.size() + 1;
    }

    public class CommunityViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.community_img)ImageView communityIV;
        @BindView(R.id.community_title_tv)TextView titleTV;
        @BindView(R.id.community_topnum_tv)TextView topnumTV;
        @BindView(R.id.imageView)ImageView menuIV;

        public CommunityViewHolder(View parent){
            super(parent);
            ButterKnife.bind(this,parent);
        }

        public String getTitleTV(){
            return titleTV.getText().toString();
        }
    }

    public class HeadViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.slider)Slider slider;

        public HeadViewHolder(View header){
            super(header);
            ButterKnife.bind(this,header);
        }

        @OnClick(R.id.slider)
        void onClick(View view){
            Toast.makeText(context,"你点击了轮播图",Toast.LENGTH_SHORT).show();
        }
    }
}
