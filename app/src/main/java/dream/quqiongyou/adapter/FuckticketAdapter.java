package dream.quqiongyou.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import dream.quqiongyou.R;
import dream.quqiongyou.bean.PostBean;
import dream.quqiongyou.utils.ImageLoaderUtils;
import dream.quqiongyou.utils.LogUtils;

/**
 * Created by SomeOneInTheWorld on 2016/10/8.
 */
public class FuckticketAdapter extends BaseAdapter {
    private static final String TAG = "FuckTicketTest";
    private List<PostBean>topLists;
    private List<PostBean>normalLists;

    public FuckticketAdapter(Context context){
        super(context);
    }

    public void setTopLists(List<PostBean>topLists){
        this.topLists = topLists;
    }
    public void setNormalLists(List<PostBean>normalLists){
        this.normalLists = normalLists;
    }
    public void setTopAndNormalLists(List<PostBean>topLists,List<PostBean>normalLists){
        this.topLists = topLists;
        this.normalLists = normalLists;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LogUtils.d(TAG,"viewType is " + viewType);
        View item;
        if(viewType == TYPE_NORMAL){
            item = LayoutInflater.from(context).inflate(R.layout.item_fuckticket,parent,false);
            return new PostNormalViewHolder(item);
        }else if(viewType == TYPE_HEAD){
            item = LayoutInflater.from(context).inflate(R.layout.item_fuckticket_head,parent,false);
            return new PostTopViewHolder(item);
        }else{
            return super.onCreateViewHolder(parent,viewType);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof PostNormalViewHolder){
            PostBean itemBean = normalLists.get(position - (topLists == null ? 0 : topLists.size()));
            PostNormalViewHolder tempHolder = ((PostNormalViewHolder)holder);
            tempHolder.commentnumTV.setText(String.valueOf(itemBean.getCommentNum()));
            tempHolder.nameTV.setText(itemBean.getAuthor());
            tempHolder.subtitleTV.setText(itemBean.getSubtitle());
            tempHolder.rankTV.setText(itemBean.getLevel());
            tempHolder.seennumTV.setText(String.valueOf(itemBean.getSeenNum()));
            tempHolder.timeTV.setText(itemBean.getTime());
            tempHolder.titleTV.setText(itemBean.getTitle());
            if(itemBean.getH_image() != null){
                ImageLoaderUtils.display(context,tempHolder.headView,itemBean.getH_image());
            }
            //TODO ViewStub
        }else if(holder instanceof PostTopViewHolder){
            PostBean itemBean = topLists.get(position);
            PostTopViewHolder tempHolder = ((PostTopViewHolder)holder);
            tempHolder.contentTV.setText(itemBean.getTitle());
            tempHolder.bestTV.setVisibility(itemBean.isGreat() ? View.VISIBLE : View.GONE);
        }else{
            super.onBindViewHolder(holder, position);
        }
    }

    @Override
    public int getItemCount() {
        int count = 1;
        if(topLists == null){
            count += normalLists.size();
        }else if(normalLists == null){
            count += topLists.size();
        }else{
            count += topLists.size() + normalLists.size();
        }
        LogUtils.d("FUCKACTEST","count = " + count);
        return count;
    }

    @Override
    public int getItemViewType(int position) {
        if(position>=0 && position<topLists.size()){
            return TYPE_HEAD;
        }else if(position>=topLists.size() && position<topLists.size()+normalLists.size()){
            return TYPE_NORMAL;
        }else if(position == topLists.size()+normalLists.size()){
            LogUtils.d(TAG,"is foot");
            return TYPE_FOOTER;
        }else{
            return TYPE_OUT;
        }
    }

    public class PostNormalViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.avatar)ImageView headView;
        @BindView(R.id.rank)TextView rankTV;
        @BindView(R.id.name)TextView nameTV;
        @BindView(R.id.title)TextView titleTV;
        @BindView(R.id.subtitle)TextView subtitleTV;
        @BindView(R.id.time)TextView timeTV;
        @BindView(R.id.seen_num)TextView seennumTV;
        @BindView(R.id.comment_num)TextView commentnumTV;

        public PostNormalViewHolder(View parent){
            super(parent);
            ButterKnife.bind(this,parent);
        }
    }

    public class PostTopViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.best_tv)TextView bestTV;
        @BindView(R.id.head_content)TextView contentTV;

        public PostTopViewHolder(View parent){
            super(parent);
            ButterKnife.bind(this,parent);
        }
    }
}
