package dream.quqiongyou.community.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import dream.quqiongyou.R;
import dream.quqiongyou.adapter.CommunityAdapter;
import dream.quqiongyou.adapter.OnItemTouchListener;
import dream.quqiongyou.bean.CommunityItemBean;
import dream.quqiongyou.community.presenter.CommunityPresenter;
import dream.quqiongyou.community.presenter.CommunityPresenterImpl;
import dream.quqiongyou.fuckticket.view.FuckticketActivity;

/**
 * Created by SomeOneInTheWorld on 2016/10/4.
 */
public class CommunityFragment extends Fragment implements CommunityView {
    @BindView(R.id.community_rv)RecyclerView recyclerView;
    CommunityAdapter communityAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_community,null);
        ButterKnife.bind(this,view);

        communityAdapter = new CommunityAdapter(getContext());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(communityAdapter);
        recyclerView.addOnItemTouchListener(new OnItemTouchListener(recyclerView) {
            @Override
            public void onItemClick(RecyclerView.ViewHolder vh) {
                if(vh instanceof CommunityAdapter.CommunityViewHolder){
                    CommunityAdapter.CommunityViewHolder holder = (CommunityAdapter.CommunityViewHolder)vh;
                    if(holder.getTitleTV().equals("上边")){
                        FuckticketActivity.startFuckticketActivity(getContext());
                    }
                }
            }
        });

        CommunityPresenter communityPresenter = new CommunityPresenterImpl(this);
        communityPresenter.loadSomethingInCommunity();
        return view;
    }

    @Override
    public void loadSomethingSuccess(List<CommunityItemBean> communityDatas, List<CommunityItemBean> guessDatas) {
        communityAdapter.setDatas(communityDatas,guessDatas);
    }

    @Override
    public void loadSomethingFail(String errorMessage) {

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }
}
