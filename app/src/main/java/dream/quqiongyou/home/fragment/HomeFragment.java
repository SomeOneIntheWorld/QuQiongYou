package dream.quqiongyou.home.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import dream.quqiongyou.R;
import dream.quqiongyou.bean.HomeItemBean;
import dream.quqiongyou.adapter.HomeAdapter;
import dream.quqiongyou.bean.TopInfo;
import dream.quqiongyou.home.presenter.HomePresenter;
import dream.quqiongyou.home.presenter.HomePresenterImpl;
import dream.quqiongyou.home.view.HomeView;

/**
 * Created by SomeOneInTheWorld on 2016/10/3.
 */
public class HomeFragment extends Fragment implements HomeView{
    @BindView(R.id.home_recycler)
    RecyclerView mHomeRecycler;
    HomeAdapter mHomeAdater;
    List<HomeItemBean> datas = new ArrayList<>();;
    List<TopInfo> topInfos = new ArrayList<>();

    private HomePresenter mHomePresenter;
    private Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_home,null);
        unbinder = ButterKnife.bind(this,view);

        mHomeAdater = new HomeAdapter(getContext());
        mHomeAdater.setHomeData(datas,topInfos);
        mHomeRecycler.setHasFixedSize(true);
        mHomeRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        mHomeRecycler.setItemAnimator(new DefaultItemAnimator());
        mHomeRecycler.setAdapter(mHomeAdater);

        mHomePresenter = new HomePresenterImpl(this);
        mHomePresenter.loadTopInfo();
        mHomePresenter.loadTrips();

        return view;
    }



    @Override
    public void loadTripsSuccess(List<HomeItemBean> datas) {
        this.datas.clear();
        this.datas.addAll(datas);
        mHomeAdater.notifyDataSetChanged();
        mHomeAdater.isShowFooter(false);
    }

    @Override
    public void loadTopInfoSuccess(List<TopInfo> topInfos) {
        this.topInfos.clear();
        this.topInfos.addAll(topInfos);
        mHomeAdater.notifyDataSetChanged();
        mHomeAdater.isShowFooter(false);
    }

    @Override
    public void loadTripFail(String errorMessage) {
        Toast.makeText(getContext(), errorMessage, Toast.LENGTH_SHORT).show();
        mHomeAdater.isShowFooter(true);
    }

    @Override
    public void showProgressBar() {

    }

    @Override
    public void hideProgressBar() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


}
