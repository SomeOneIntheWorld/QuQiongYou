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
import dream.quqiongyou.common.Constants;
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
    private LinearLayoutManager layoutManager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_home,null);
        unbinder = ButterKnife.bind(this,view);

        layoutManager = new LinearLayoutManager(getActivity());
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
        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mHomePresenter.loadHomeInfoByPresenter(1);
                listener.setPreviousTotal(0);
            }
        });

        mHomePresenter = new HomePresenterImpl(this);
        mHomePresenter.loadHomeInfoByPresenter(1);

        return view;
    }

    @Override
    public void loadHomeInfoSuccess(List<HomeItemBean> datas, List<TopInfo> topInfos) {
        this.datas.clear();
        this.datas.addAll(datas);
        this.topInfos.clear();
        this.topInfos.addAll(topInfos);

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
    public void loadHomeInfoFail(List<?> infos, int tag, String message) {
        if(mRefreshLayout.isRefreshing()){
            mRefreshLayout.setRefreshing(false);
        }

        if(tag == Constants.HOME_TAG_HOME_ITEM_SUCCESS){
            this.datas.clear();
            this.datas.addAll((List<HomeItemBean>)infos);
            mHomeAdater.notifyDataSetChanged();
        }else if(tag == Constants.HOME_TAG_TOP_INFO_SUCCESS){
            this.topInfos.clear();
            this.topInfos.addAll((List<TopInfo>)infos);
            mHomeAdater.notifyDataSetChanged();
        }else{
            mHomeAdater.setStatusOfProgressBar(true);
        }
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
