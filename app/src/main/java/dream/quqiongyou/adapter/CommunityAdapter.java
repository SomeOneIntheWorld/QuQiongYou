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
import dream.quqiongyou.R;
import dream.quqiongyou.bean.CommunityItemBean;
import dream.quqiongyou.utils.ImageLoaderUtils;

/**
 * Created by SomeOneInTheWorld on 2016/10/4.
 */
public class CommunityAdapter extends RecyclerView.Adapter{
    private List<CommunityItemBean> communityDatas;
    private List<CommunityItemBean> guessDatas;
    private Context context;
    private final static int TYPE_GUESS = 1;
    private final static int TYPE_TOPIC = 2;
    private final static int TYPE_TEXT = 3;
    private final static int TYPE_OUT = 4;

    public CommunityAdapter(Context context){
        this.context = context;
    }

    public void setDatas(List<CommunityItemBean>communityDatas,List<CommunityItemBean>guessDatas){
        this.communityDatas = communityDatas;
        this.guessDatas = guessDatas;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType == TYPE_TOPIC || viewType == TYPE_GUESS){
            View item = LayoutInflater.from(context).inflate(R.layout.item_community,null);
            return new CommunityViewHolder(item);
        }else if(viewType == TYPE_TEXT){
            View item = LayoutInflater.from(context).inflate(R.layout.item_community,null);
            ViewStub viewStub = (ViewStub)item.findViewById(R.id.stub);
            viewStub.inflate();
            return new CommunityViewHolder(item);
        }else{
            return null;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int type = getItemViewType(position);
        CommunityItemBean communityItemBean;
        if(type == TYPE_TOPIC) {
            communityItemBean = communityDatas.get(position);
        }else if(type == TYPE_GUESS || type == TYPE_TEXT) {
            communityItemBean = guessDatas.get(position - communityDatas.size());
        }else{
            return;
        }

        ((CommunityViewHolder)holder).titleTV.setText(communityItemBean.getTitle());
        ((CommunityViewHolder)holder).topnumTV.setText(communityItemBean.getTopicnum());
        ((CommunityViewHolder)holder).menuIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,"you click the menu",Toast.LENGTH_SHORT).show();
            }
        });
        if(communityItemBean.getImgurl() == null){
            ((CommunityViewHolder)holder).communityIV.setImageResource(R.mipmap.community_normal);
            return;
        }
        ImageLoaderUtils.display(context,((CommunityViewHolder)holder).communityIV,communityItemBean.getImgurl());
    }

    @Override
    public int getItemViewType(int position) {
        if(position >= 0 && position < communityDatas.size()){
            return TYPE_TOPIC;
        }
        if(position == communityDatas.size()){
            return TYPE_TEXT;
        }
        if(position > communityDatas.size() && position < communityDatas.size() + guessDatas.size()){
            return TYPE_GUESS;
        }
        return TYPE_OUT;
    }

    @Override
    public int getItemCount() {
        if(communityDatas.size() == 0 && guessDatas.size() == 0){
            return 0;
        }
        if(communityDatas.size() == 0){
            return guessDatas.size();
        }
        if(guessDatas.size() == 0){
            return communityDatas.size();
        }
        return communityDatas.size() + guessDatas.size() ;
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
}
