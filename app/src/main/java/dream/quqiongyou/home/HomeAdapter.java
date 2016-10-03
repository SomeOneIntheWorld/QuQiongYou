package dream.quqiongyou.home;

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
import dream.quqiongyou.utils.ImageLoaderUtils;
import dream.quqiongyou.utils.LogUtils;

/**
 * Created by SomeOneInTheWorld on 2016/10/3.
 */
public class HomeAdapter extends RecyclerView.Adapter {
    private final static String TAG = HomeAdapter.class.getName() + "test";
    private List<HomeItemBean> homeDatas;
    private Context context;

    public HomeAdapter(Context context){
        this.context = context;
    }

    public void setHomeData(List<HomeItemBean>homeDatas){
        this.homeDatas = homeDatas;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(context).inflate(R.layout.item_home_normal,null);
        return new ItemViewHolder(item);
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
        }
    }

    @Override
    public int getItemCount(){
        if(homeDatas == null){
            LogUtils.d(TAG,"item is null");
            return 0;
        }else{
            return homeDatas.size();
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
}
