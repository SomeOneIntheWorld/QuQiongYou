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
import dream.quqiongyou.bean.CommentBean;
import dream.quqiongyou.bean.PostBean;
import dream.quqiongyou.utils.ImageLoaderUtils;

/**
 * Created by SomeOneInTheWorld on 2016/10/10.
 */
public class PostDetailAdapter extends BaseAdapter{
    private List<CommentBean>commentBeanList;
    private PostBean postBean;

    public PostDetailAdapter(Context context){
        super(context);
    }

    public void setCommentBeanList(List<CommentBean>commentBeanList){
        this.commentBeanList = commentBeanList;
    }

    public void setPostBean(PostBean postBean){
        this.postBean = postBean;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType == TYPE_HEAD){
            View view = LayoutInflater.from(context).inflate(R.layout.item_postdetail_head,parent,false);
            return new PostDetailTopViewHolder(view);
        }else if(viewType == TYPE_NORMAL){
            View view = LayoutInflater.from(context).inflate(R.layout.item_postdetail_comment,parent,false);
            return new PostDetailAnswerHolder(view);
        }else{
            return super.onCreateViewHolder(parent,viewType);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof PostDetailTopViewHolder){
            PostDetailTopViewHolder topViewHolder = (PostDetailTopViewHolder)holder;
            topViewHolder.goodjobTV.setText(String.valueOf(postBean.getGoodJobNum()));
            topViewHolder.nameTV.setText(postBean.getAuthor());
            topViewHolder.phoneTV.setText(postBean.getPhone());
            topViewHolder.subtitleTV.setText(postBean.getSubtitle());
            topViewHolder.rankTV.setText(String.valueOf(postBean.getLevel()));
            topViewHolder.timeTV.setText(postBean.getTime());
            topViewHolder.titleTV.setText(postBean.getTitle());
            String headSource = postBean.getH_image();
            if(headSource == null){
                return;
            }
            ImageLoaderUtils.display(context,topViewHolder.headView,headSource);
        }else if(holder instanceof PostDetailAnswerHolder){
            position = position - 1;
            PostDetailAnswerHolder answerHolder = (PostDetailAnswerHolder)holder;
            if(position >= commentBeanList.size()){
                return;
            }
            CommentBean comment = commentBeanList.get(position);
            if(comment == null){
                return;
            }
            answerHolder.answerTV.setText(comment.getComment());
            answerHolder.commentnumTV.setText(String.valueOf(comment.getCommentnum()));
            answerHolder.nameTV.setText(comment.getAnsweruser().getNickname());
            answerHolder.phoneTV.setText(comment.getSource());
            answerHolder.timeTV.setText(comment.getAnswertime().toString());
            answerHolder.goodjobTV.setText(String.valueOf(comment.getGoodjobnum()));
            answerHolder.rankTV.setText(String.valueOf(comment.getAnsweruser().getLevel()));
            String imgsrc = comment.getAnsweruser().getHeadingimg();
            if(imgsrc == null){
                return;
            }
            ImageLoaderUtils.display(context,answerHolder.headIV,imgsrc);
        }
    }

    @Override
    public int getItemCount() {
        if(commentBeanList == null && postBean == null){
            return 1;//the footer
        }else if(commentBeanList == null){
            return 2;
        }else{
            return commentBeanList.size() + 2;
        }
    }

    @Override
    public int getItemViewType(int position) {
        if(position == 0) {
            return TYPE_HEAD;
        }else if(position == commentBeanList.size()){
            return TYPE_FOOTER;
        }else{
            return TYPE_NORMAL;
        }
    }

    public class PostDetailTopViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.avatar)ImageView headView;
        @BindView(R.id.rank)TextView rankTV;
        @BindView(R.id.name)TextView nameTV;
        @BindView(R.id.title)TextView titleTV;
        @BindView(R.id.subtitle)TextView subtitleTV;
        @BindView(R.id.time)TextView timeTV;
        @BindView(R.id.phone)TextView phoneTV;
        @BindView(R.id.good_job)TextView goodjobTV;

        public PostDetailTopViewHolder(View parent){
            super(parent);
            ButterKnife.bind(this,parent);
        }
    }

    public class PostDetailAnswerHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.avatar) ImageView headIV;
        @BindView(R.id.name) TextView nameTV;
        @BindView(R.id.rank) TextView rankTV;
        @BindView(R.id.delete) TextView deleteTV;
        @BindView(R.id.answer) TextView answerTV;
        @BindView(R.id.time) TextView timeTV;
        @BindView(R.id.phone) TextView phoneTV;
        @BindView(R.id.comment_num) TextView commentnumTV;
        @BindView(R.id.good_job) TextView goodjobTV;

        public PostDetailAnswerHolder(View parent){
            super(parent);
            ButterKnife.bind(this,parent);
        }
    }
}
