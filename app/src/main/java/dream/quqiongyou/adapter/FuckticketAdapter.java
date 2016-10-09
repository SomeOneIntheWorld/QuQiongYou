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

/**
 * Created by SomeOneInTheWorld on 2016/10/8.
 */
public class FuckticketAdapter extends RecyclerView.Adapter {
    private static final int TYPE_TOP = 0;
    private static final int TYPE_NORMAL = 1;

    private List<PostBean>topLists;
    private List<PostBean>normalLists;

    private Context context;

    public FuckticketAdapter(Context context){
        this.context = context;
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
        if(viewType == TYPE_NORMAL){
            View item = LayoutInflater.from(context).inflate(R.layout.item_fuckticket,null);
            return new NormalViewHolder(item);
        }else if(viewType == TYPE_TOP){
            View item = LayoutInflater.from(context).inflate(R.layout.item_fuckticket_head,null);
            return new TopViewHolder(item);
        }else{
            return null;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof NormalViewHolder){
            PostBean itemBean = normalLists.get(position - (topLists == null ? 0 : topLists.size()));
            ((NormalViewHolder)holder).commentnumTV.setText(itemBean.getCommentnum());
            ((NormalViewHolder)holder).nameTV.setText(itemBean.getPoster().getNickname());
            ((NormalViewHolder)holder).subtitleTV.setText(itemBean.getSubtitle());
            ((NormalViewHolder)holder).rankTV.setText(itemBean.getPoster().getLevel());
            ((NormalViewHolder)holder).seennumTV.setText(itemBean.getSeenum());
            ((NormalViewHolder)holder).timeTV.setText(itemBean.getTime());
            ((NormalViewHolder)holder).titleTV.setText(itemBean.getTitle());
            if(itemBean.getPoster().getHeadimg() != null){
                ImageLoaderUtils.display(context,((NormalViewHolder)holder).headView,itemBean.getPoster().getHeadimg());
            }
            //ViewStub
        }else if(holder instanceof TopViewHolder){
            PostBean itemBean = topLists.get(position);
            ((TopViewHolder)holder).contentTV.setText(itemBean.getTitle());
            ((TopViewHolder)holder).bestTV.setVisibility(itemBean.getIsgreat() ? View.VISIBLE : View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        if(topLists == null && normalLists == null){
            return 0;
        }else if(topLists == null){
            return normalLists.size();
        }else if(normalLists == null){
            return topLists.size();
        }else{
            return topLists.size() + normalLists.size();
        }
    }

    @Override
    public int getItemViewType(int position) {
        if(position>=0 && position<topLists.size()){
            return TYPE_TOP;
        }else if(position>=topLists.size() && position<topLists.size()+normalLists.size()){
            return TYPE_NORMAL;
        }else{
            return getItemViewType(position);
        }
    }

    public class NormalViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.avatar)ImageView headView;
        @BindView(R.id.rank)TextView rankTV;
        @BindView(R.id.name)TextView nameTV;
        @BindView(R.id.title)TextView titleTV;
        @BindView(R.id.subtitle)TextView subtitleTV;
        @BindView(R.id.time)TextView timeTV;
        @BindView(R.id.seen_num)TextView seennumTV;
        @BindView(R.id.comment_num)TextView commentnumTV;

        public NormalViewHolder(View parent){
            super(parent);
            ButterKnife.bind(this,parent);
        }
    }

    public class TopViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.best_tv)TextView bestTV;
        @BindView(R.id.head_content)TextView contentTV;

        public TopViewHolder(View parent){
            super(parent);
            ButterKnife.bind(this,parent);
        }
    }
}
