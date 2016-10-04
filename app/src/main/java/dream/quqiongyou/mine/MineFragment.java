package dream.quqiongyou.mine;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import dream.quqiongyou.R;

/**
 * Created by SomeOneInTheWorld on 2016/10/4.
 */
public class MineFragment extends Fragment {
    @BindView(R.id.mine_about_us)View aboutUs;
    @BindView(R.id.mine_clear_cache)View clearCache;
    @BindView(R.id.mine_collection)View collectionCache;
    @BindView(R.id.mine_feedback)View feedback;
    @BindView(R.id.mine_posts)View posts;
    @BindView(R.id.mine_origination)View origination;
    private Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mine, null);
        unbinder = ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
