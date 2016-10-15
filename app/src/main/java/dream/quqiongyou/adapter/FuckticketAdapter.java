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
public class FuckticketAdapter extends RecyclerView.Adapter {
    private static final int TYPE_POST_TOP = 0;
    private static final int TYPE_POST_NORMAL = 1;
    private static final int TYPE_POST_FOOTER = 2;
    private static final String TAG = "FuckTicketTest";

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
        LogUtils.d(TAG,"viewType is " + viewType);
        if(viewType == TYPE_POST_NORMAL){
            View item = LayoutInflater.from(context).inflate(R.layout.item_fuckticket,parent,false);
            return new PostNormalViewHolder(item);
        }else if(viewType == TYPE_POST_TOP){
            View item = LayoutInflater.from(context).inflate(R.layout.item_fuckticket_head,parent,false);
            return new PostTopViewHolder(item);
        }else{
            View item = LayoutInflater.from(context).inflate(R.layout.item_footer,parent,false);
            return new FooterViewHolder(item);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof PostNormalViewHolder){
            PostBean itemBean = normalLists.get(position - (topLists == null ? 0 : topLists.size()));
            ((PostNormalViewHolder)holder).commentnumTV.setText(String.valueOf(itemBean.getCommentnum()));
            ((PostNormalViewHolder)holder).nameTV.setText(itemBean.getPoster().getNickname());
            ((PostNormalViewHolder)holder).subtitleTV.setText(itemBean.getSubtitle());
            ((PostNormalViewHolder)holder).rankTV.setText(String.valueOf(itemBean.getPoster().getLevel()));
            ((PostNormalViewHolder)holder).seennumTV.setText(String.valueOf(itemBean.getSeenum()));
            ((PostNormalViewHolder)holder).timeTV.setText(itemBean.getTime());
            ((PostNormalViewHolder)holder).titleTV.setText(itemBean.getTitle());
            if(itemBean.getPoster().getHeadingimg() != null){
                ImageLoaderUtils.display(context,((PostNormalViewHolder)holder).headView,itemBean.getPoster().getHeadingimg());
            }
            //ViewStub
        }else if(holder instanceof PostTopViewHolder){
            PostBean itemBean = topLists.get(position);
            ((PostTopViewHolder)holder).contentTV.setText(itemBean.getTitle());
            ((PostTopViewHolder)holder).bestTV.setVisibility(itemBean.getIsgreat() ? View.VISIBLE : View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        int count;
        if(topLists == null && normalLists == null){
            count = 1;
        }else if(topLists == null){
            count = normalLists.size() + 1;
        }else if(normalLists == null){
            count = topLists.size() + 1;
        }else{
            count = topLists.size() + normalLists.size() + 1;
        }
        LogUtils.d("FUCKACTEST","count = " + count);
        return count;
    }

    @Override
    public int getItemViewType(int position) {
        if(position>=0 && position<topLists.size()){
            return TYPE_POST_TOP;
        }else if(position>=topLists.size() && position<topLists.size()+normalLists.size()-1){
            return TYPE_POST_NORMAL;
        }else{
            LogUtils.d(TAG,"is foot");
            return TYPE_POST_FOOTER;
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

    public class FooterViewHolder extends RecyclerView.ViewHolder {
        public FooterViewHolder(View view){
            super(view);
        }
    }
}
