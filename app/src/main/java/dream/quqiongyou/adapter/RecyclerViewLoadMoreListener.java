package dream.quqiongyou.adapter;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import dream.quqiongyou.utils.LogUtils;

/**
 * Created by SomeOneInTheWorld on 2016/10/14.
 */
public abstract class RecyclerViewLoadMoreListener extends RecyclerView.OnScrollListener {
    private LinearLayoutManager mLinearLayoutManager;

    private int totalItemCount;

    private int previousTotal = 0;

    private int visibleItemCount;

    private int firstVisibleItem;

    private boolean loading = true;

    private int currentPage = 0;

    private static final String TAG = "RecyclerTest";

    public RecyclerViewLoadMoreListener(LinearLayoutManager linearLayoutManager) {
        this.mLinearLayoutManager = linearLayoutManager;
    }

    public void setPreviousTotal(int previousTotal){
        this.previousTotal = previousTotal;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        visibleItemCount = recyclerView.getChildCount();
        totalItemCount = mLinearLayoutManager.getItemCount();
        firstVisibleItem = mLinearLayoutManager.findFirstVisibleItemPosition();
        LogUtils.d(TAG,"visibleItemCount = " + visibleItemCount + " totalItemCount = "
        + totalItemCount + " firstVisibleItem = " + firstVisibleItem);
        LogUtils.d("testttt","loading is " + loading);

        if (loading) {
            if (totalItemCount > previousTotal) {
                loading = false;
                previousTotal = totalItemCount;
            }
        }
        if (!loading && totalItemCount - visibleItemCount <= firstVisibleItem) {
            currentPage++;
            LogUtils.d("testttt","onLoadMore now");
            onLoadMore(currentPage);
            loading = true;
        }
    }

    public abstract void onLoadMore(int currentPage);
}
