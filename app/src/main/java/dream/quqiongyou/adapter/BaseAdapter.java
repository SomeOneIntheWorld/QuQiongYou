package dream.quqiongyou.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import dream.quqiongyou.R;

/**
 * Created by SomeOneInTheWorld on 2016/11/6.
 */
public abstract class BaseAdapter extends RecyclerView.Adapter {
    protected static final int TYPE_NORMAL = 1;
    protected static final int TYPE_FOOTER = 2;
    protected final static int TYPE_GUESS = 3;
    protected final static int TYPE_HEAD = 4;
    protected final static int TYPE_TEXT = 5;
    protected final static int TYPE_OUT = 6;
    protected final static int TYPE_SLIDER = 7;
    private FooterViewHolder footerViewHolder = null;
    protected Context context;

    public BaseAdapter(Context context){
        this.context = context;
    }

    public void setStatusOfProgressBar(boolean gone){
        if(footerViewHolder == null){
            return;
        }
        if(gone){
            footerViewHolder.loadLayout.setVisibility(View.GONE);
            footerViewHolder.loadFinishedTV.setVisibility(View.VISIBLE);
        }else{
            footerViewHolder.loadLayout.setVisibility(View.VISIBLE);
            footerViewHolder.loadFinishedTV.setVisibility(View.GONE);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item;
        if (viewType == TYPE_FOOTER) {
            item = LayoutInflater.from(context).inflate(R.layout.item_footer, parent, false);
            return new FooterViewHolder(item);
        } else {
            View v = LayoutInflater.from(context).inflate(R.layout.item_none, parent, false);
            return new NoneViewHolder(v);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof FooterViewHolder){
            footerViewHolder = (FooterViewHolder)holder;
        }
    }

    public class FooterViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.load_finished)TextView loadFinishedTV;
        @BindView(R.id.load_layout)View loadLayout;

        public FooterViewHolder(View view){
            super(view);
            ButterKnife.bind(this,view);
        }
    }

    public class NoneViewHolder extends RecyclerView.ViewHolder {
        public NoneViewHolder(View view){
            super(view);
        }
    }
}
