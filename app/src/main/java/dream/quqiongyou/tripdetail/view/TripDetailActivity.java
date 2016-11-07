package dream.quqiongyou.tripdetail.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dream.quqiongyou.R;
import dream.quqiongyou.bean.TripDetail;
import dream.quqiongyou.tripdetail.presenter.TripDetailPresenter;
import dream.quqiongyou.tripdetail.presenter.TripDetailPresenterImpl;
import dream.quqiongyou.utils.ImageLoaderUtils;

/**
 * Created by SaladJack on 2016/11/6.
 */

public class TripDetailActivity extends AppCompatActivity implements TripDetailView {

    private static String TRIP_ID = "tripId";
    @BindView(R.id.tripImg)
    ImageView tripImg;
    @BindView(R.id.tripName)
    TextView tripName;
    @BindView(R.id.peopleNum)
    TextView peopleNum;
    @BindView(R.id.seenNum)
    TextView seenNum;
    @BindView(R.id.begin_time)
    TextView beginTime;
    @BindView(R.id.end_time)
    TextView endTime;
    @BindView(R.id.tripLocation)
    TextView tripLocation;
    @BindView(R.id.consumeNum)
    TextView consumeNum;
    private String tripId;

    public static void startTripDetailActivity(Context context, String tripId) {
        Intent intent = new Intent(context, TripDetailActivity.class);
        intent.putExtra(TRIP_ID, tripId);
        context.startActivity(intent);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_detail);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        if (intent != null) {
            tripId = intent.getStringExtra(TRIP_ID);
        }
        TripDetailPresenter presenter = new TripDetailPresenterImpl(this);
        presenter.loadTripDetail(tripId);
    }


    @OnClick({R.id.back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
        }
    }

    @Override
    public void showProgressBar() {

    }

    @Override
    public void hideProgressBar() {

    }

    @Override
    public void showTripDetail(TripDetail tripDetail) {
        ImageLoaderUtils.display(this,tripImg,tripDetail.getImage());
        tripName.setText(tripDetail.getName());
        peopleNum.setText(tripDetail.getPeopleNum());
        seenNum.setText(tripDetail.getSeenNum());
        beginTime.setText(tripDetail.getBegintime());
        endTime.setText(tripDetail.getEndtime());
        tripLocation.setText(tripDetail.getLocation());
        consumeNum.setText(tripDetail.getConsumeNum());
    }

    @Override
    public void showErrorMessage(String message) {
        Toast.makeText(this, "message", Toast.LENGTH_SHORT).show();
    }
}
