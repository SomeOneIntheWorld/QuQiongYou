package dream.quqiongyou.home.fragment;

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
import butterknife.Unbinder;
import dream.quqiongyou.R;
import dream.quqiongyou.adapter.HomeAdapter;
import dream.quqiongyou.adapter.RecyclerViewLoadMoreListener;
import dream.quqiongyou.bean.HomeItemBean;
import dream.quqiongyou.bean.TopInfo;
import dream.quqiongyou.home.presenter.HomePresenter;
import dream.quqiongyou.home.presenter.HomePresenterImpl;
import dream.quqiongyou.home.view.HomeView;
import dream.quqiongyou.utils.LogUtils;

/**
 * Created by SomeOneInTheWorld on 2016/10/3.
 */
public class HomeFragment extends Fragment implements HomeView{
    @BindView(R.id.home_recycler) RecyclerView mHomeRecycler;
    @BindView(R.id.home_refresh_layout)SwipeRefreshLayout mRefreshLayout;
    HomeAdapter mHomeAdater;
    List<HomeItemBean> datas = new ArrayList<>();
    List<TopInfo> topInfos = new ArrayList<>();

    private HomePresenter mHomePresenter;
    private Unbinder unbinder;

    private final String TAG = "HomeViewTest";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_home,null);
        unbinder = ButterKnife.bind(this,view);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mHomeAdater = new HomeAdapter(getContext());
        mHomeAdater.setHomeData(datas,topInfos);
        mHomeRecycler.setHasFixedSize(true);
        mHomeRecycler.setLayoutManager(layoutManager);
        mHomeRecycler.setItemAnimator(new DefaultItemAnimator());
        mHomeRecycler.setAdapter(mHomeAdater);
        final RecyclerViewLoadMoreListener listener = new RecyclerViewLoadMoreListener(layoutManager) {
            @Override
            public void onLoadMore(int currentPage) {
                mHomeRecycler.setNestedScrollingEnabled(false);
                LogUtils.d("HOMEMODELIMPL_TEST","currentPage in HomeFragment = " + currentPage);
                mHomePresenter.loadHomeInfoByPresenter(currentPage);
                mHomeRecycler.setNestedScrollingEnabled(true);
            }
        };
        mHomeRecycler.addOnScrollListener(listener);
        mRefreshLayout.setOnRefreshListener(() -> {
            mHomePresenter.loadHomeInfoByPresenter(1);
            mHomePresenter.loadHomeTopBannerByPresenter();
            listener.setPreviousTotal(0);
        });

        mHomePresenter = new HomePresenterImpl(this);
        mHomePresenter.loadHomeInfoByPresenter(1);
        mHomePresenter.loadHomeTopBannerByPresenter();

        return view;
    }

    @Override
    public void loadTripActivitiesSuccess(List<HomeItemBean> datas) {
        this.datas.clear();
        this.datas.addAll(datas);
        mHomeAdater.notifyDataSetChanged();

        if(mRefreshLayout.isRefreshing()){
            mRefreshLayout.setRefreshing(false);
        }
    }

    @Override
    public void loadTopBannerSuccess(List<TopInfo> topInfoList) {
        LogUtils.d(TAG,"topInfoList is " + topInfoList.size() + "///" + topInfoList.get(0).getImage() + "///" + topInfoList.get(0).getTitle());
        this.topInfos.clear();

        //just for test
        for(int i=0;i<topInfoList.size();i++){
            topInfoList.get(i).setImage("http://pic3.zhimg.com/e669f7215f9d4355fafdee7eb0f8ea4e.jpg");
        }
        this.topInfos.addAll(topInfoList);

        mHomeAdater.notifyDataSetChanged();

        if(mRefreshLayout.isRefreshing()){
            mRefreshLayout.setRefreshing(false);
        }
    }

    @Override
    public void loadNoMoreData() {
        mHomeAdater.setStatusOfProgressBar(true);
    }

    @Override
    public void loadDataFail(int tag, String message) {
        LogUtils.d(TAG,"message is " + message);
        if(mRefreshLayout.isRefreshing()){
            mRefreshLayout.setRefreshing(false);
        }
        mHomeAdater.setStatusOfProgressBar(true);
    }

    @Override
    public void showProgressBar() {
        mHomeAdater.setStatusOfProgressBar(false);

    }

    @Override
    public void hideProgressBar() {
        mHomeAdater.setStatusOfProgressBar(true);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
