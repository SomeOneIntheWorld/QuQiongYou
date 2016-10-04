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

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import dream.quqiongyou.R;
import dream.quqiongyou.bean.HomeItemBean;
import dream.quqiongyou.adapter.HomeAdapter;
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

    private HomePresenter mHomePresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_home,null);
        ButterKnife.bind(this,view);

        mHomeAdater = new HomeAdapter(getContext());
        mHomeRecycler.setHasFixedSize(true);
        mHomeRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        mHomeRecycler.setItemAnimator(new DefaultItemAnimator());
        mHomeRecycler.setAdapter(mHomeAdater);

        mHomePresenter = new HomePresenterImpl(this);
        mHomePresenter.loadTrips();

        return view;
    }

    @Override
    public void loadTripsSuccess(List<HomeItemBean> datas) {
        mHomeAdater.setHomeData(datas);
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
}
