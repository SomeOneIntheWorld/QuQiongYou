package dream.quqiongyou.community.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import dream.quqiongyou.R;
import dream.quqiongyou.adapter.CommunityAdapter;
import dream.quqiongyou.adapter.OnItemTouchListener;
import dream.quqiongyou.bean.TopInfo;
import dream.quqiongyou.bean.TopicBean;
import dream.quqiongyou.community.presenter.CommunityPresenter;
import dream.quqiongyou.community.presenter.CommunityPresenterImpl;
import dream.quqiongyou.fuckticket.view.FuckticketActivity;

/**
 * Created by SomeOneInTheWorld on 2016/10/4.
 */
public class CommunityFragment extends Fragment implements CommunityView {
    @BindView(R.id.community_rv)RecyclerView recyclerView;
    @BindView(R.id.community_refresh_layout)SwipeRefreshLayout refreshLayout;
    private CommunityAdapter communityAdapter;
    private List<TopicBean>communityList = new ArrayList<>();
    private List<TopicBean>guessList = new ArrayList<>();
    private List<TopInfo>topInfoList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_community,null);
        ButterKnife.bind(this,view);

        communityAdapter = new CommunityAdapter(getContext());
        communityAdapter.setDatas(communityList,guessList);
        communityAdapter.setTopInfos(topInfoList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(communityAdapter);
        recyclerView.addOnItemTouchListener(new OnItemTouchListener(recyclerView) {
            @Override
            public void onItemClick(RecyclerView.ViewHolder vh) {
                int position = vh.getAdapterPosition();
                TopicBean topicBean = new TopicBean();
                if(position >= 1 && position <  1 + communityList.size()){
                    topicBean = communityList.get(position - 1);
                }else if(position >= 1 + communityList.size() && position < 1 + communityList.size() + guessList.size()){
                    topicBean = guessList.get(position - communityList.size() - 1);
                }
                FuckticketActivity.startFuckticketActivity(getContext(),topicBean);
            }
        });

        final CommunityPresenter communityPresenter = new CommunityPresenterImpl(this);
        communityPresenter.loadSomethingInCommunity();

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                communityPresenter.loadSomethingInCommunity();
            }
        });
        return view;
    }

    @Override
    public void loadSomethingSuccess(List<TopicBean> communityDatas, List<TopicBean> guessDatas,List<TopInfo>topInfoList) {
        if(refreshLayout.isRefreshing()){
            refreshLayout.setRefreshing(false);
        }
        this.communityList.clear();
        this.communityList.addAll(communityDatas);
        this.guessList.clear();
        this.guessList.addAll(guessDatas);
        this.topInfoList.clear();
        this.topInfoList.addAll(topInfoList);

        this.communityAdapter.notifyDataSetChanged();
    }

    @Override
    public void loadSomethingFail(String errorMessage) {
        if(refreshLayout.isRefreshing()){
            refreshLayout.setRefreshing(false);
        }
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }
}
