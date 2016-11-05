package dream.quqiongyou.origination.view;

import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.OptionsPickerView;
import com.bigkoo.pickerview.TimePickerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemClick;
import butterknife.OnItemLongClick;
import butterknife.Unbinder;
import dream.quqiongyou.R;
import dream.quqiongyou.adapter.UISharePictureAdapter;
import dream.quqiongyou.bean.OriginationDetailBean;
import dream.quqiongyou.origination.presenter.OriginationPresenter;
import dream.quqiongyou.utils.BitmapUtils;

/**
 * Created by SomeOneInTheWorld on 2016/10/4.
 */
public class OriginationActivity extends AppCompatActivity implements OriginationView{
    private OriginationPresenter presenter;
    private Unbinder unbinder;
    private List<Bitmap>sharedList = new ArrayList<>();
    private UISharePictureAdapter sharePictureAdapter;
    private OriginationDetailBean originationDetailBean = new OriginationDetailBean();
    private String photoPath;
    private List<String>imgList = new ArrayList<>();

    @BindView(R.id.origination_add)GridView addPictureGV;
    @BindView(R.id.start_time_tv)TextView startTimeTV;
    @BindView(R.id.end_time_tv)TextView endTimeTV;
    @BindView(R.id.price_tv)TextView priceTV;
    @BindView(R.id.origination_commit)TextView commitTV;
    @BindView(R.id.phone_et)TextView phoneTV;
    @BindView(R.id.maxnumber_et)EditText maxNumberET;
    @BindView(R.id.other_tv)TextView otherTV;
    @BindView(R.id.place_et)EditText placeET;
    @BindView(R.id.topic_et)EditText topicET;
    private TimePickerView pvTime;
    private OptionsPickerView pvOptions;

    public static void startOriginationActivity(Context context){
        Intent intent = new Intent(context,OriginationActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_origination);
        unbinder = ButterKnife.bind(this);

        Bitmap bp = BitmapFactory.decodeResource(getResources(), R.mipmap.add_pictures_for_activity_initiate);
        sharedList.add(bp);
        sharePictureAdapter = new UISharePictureAdapter(getApplicationContext(), sharedList, addPictureGV);
        addPictureGV.setAdapter(sharePictureAdapter);

        pvTime = new TimePickerView(this, TimePickerView.Type.MONTH_DAY_HOUR_MIN);
        pvOptions = new OptionsPickerView(this);

        final ArrayList<Integer> options1Items = new ArrayList<>();
        final ArrayList<ArrayList<Integer>> options2Items = new ArrayList<>();
        options1Items.add(0);
        options1Items.add(50);
        options1Items.add(100);
        options1Items.add(200);
        options1Items.add(300);
        options1Items.add(400);
        options2Items.add(options1Items);
        options2Items.add(options1Items);
        options2Items.add(options1Items);
        options2Items.add(options1Items);
        options2Items.add(options1Items);
        options2Items.add(options1Items);

        pvOptions.setPicker(options1Items, options2Items, true);
        pvOptions.setTitle("选择价格区间");
        pvOptions.setCyclic(false, false, true);
        pvOptions.setSelectOptions(1, 1, 1);

        pvOptions.setOnoptionsSelectListener(new OptionsPickerView.OnOptionsSelectListener() {

            @Override
            public void onOptionsSelect(int options1, int option2, int options3) {
                String tx =  options1Items.get(options1).toString() + "-"
                        + options2Items.get(options1).get(option2).toString();
                String showTx = "价格区间：" + tx;
                priceTV.setText(showTx);
            }
        });
    }

    @OnItemClick(R.id.origination_add)
    void onItemClick(int position){
        if (sharedList.size() == 10) {
            Toast.makeText(OriginationActivity.this, "图片数9张已满", Toast.LENGTH_SHORT).show();
        } else {
            if (position == sharedList.size() - 1) {
                Toast.makeText(OriginationActivity.this, "添加图片", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Intent.ACTION_PICK, null);
                intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                startActivityForResult(intent, 0x1);
            }
        }
    }

    @OnItemLongClick(R.id.origination_add)
    boolean onItemLongClick(int position){
        if(position == sharedList.size() - 1){
            return true;
        }
        dialog(position);
        return true;
    }

    @OnClick({R.id.start_time,R.id.end_time,R.id.price,R.id.origination_commit})
    void onClick(View view){
        switch(view.getId()){
            case R.id.start_time:
                doEvent(startTimeTV);
                break;
            case R.id.end_time:
                doEvent(endTimeTV);
                break;
            case R.id.price:
                pvOptions.show();
                break;
            case R.id.origination_commit:
                initOriginationDetailBean();
                OriginationSuccessActivity.startOriginationSuccessActivity(this,originationDetailBean);
                break;
        }
    }

    private void initOriginationDetailBean(){
        originationDetailBean.setImglist(imgList);
        originationDetailBean.setLocation(placeET.getText().toString());
        originationDetailBean.setMaxpeoplenum(maxNumberET.getText().toString().equals("") ? 100 : Integer.parseInt(maxNumberET.getText().toString()));
        originationDetailBean.setStarttime(startTimeTV.getText().toString());
        originationDetailBean.setEndtime(endTimeTV.getText().toString());
        originationDetailBean.setPrice(priceTV.getText().toString());
        originationDetailBean.setTopic(topicET.getText().toString());
        originationDetailBean.setPhone(phoneTV.getText().toString());
    }

    private void doEvent(final TextView view){
        pvTime.setTime(new Date());
        pvTime.setCyclic(false);
        pvTime.setCancelable(true);
        pvTime.setOnTimeSelectListener(new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date) {
                SimpleDateFormat format = new SimpleDateFormat("MM-dd HH:mm");
                view.setText(format.format(date));
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pvTime.show();
            }
        });
    }

    @Override
    public void showProgressBar() {

    }

    @Override
    public void hideProgressBar() {

    }

    @Override
    public void uploadResult(boolean isSuccess) {

    }

    protected void dialog(final int position) {
        android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(OriginationActivity.this);
        builder.setMessage("确认移除已添加图片吗？");
        builder.setTitle("提示");
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                sharedList.remove(position);
                sharePictureAdapter.notifyDataSetChanged();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 0x1 && resultCode == RESULT_OK) {
            if (data != null) {
                ContentResolver resolver = getContentResolver();
                try {
                    Uri uri = data.getData();
                    String[] proj = { MediaStore.Images.Media.DATA };
                    Cursor cursor = managedQuery(uri, proj, null, null, null);
                    int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                    cursor.moveToFirst();
                    photoPath = cursor.getString(column_index);
                    if(!TextUtils.isEmpty(photoPath)){
                        imgList.add(photoPath);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!TextUtils.isEmpty(photoPath)) {
            Bitmap newBp = BitmapUtils.decodeSampledBitmapFromPath(photoPath, 300, 300);
            sharedList.remove(sharedList.size() - 1);
            Bitmap bp = BitmapFactory.decodeResource(getResources(), R.drawable.menu);
            sharedList.add(newBp);
            sharedList.add(bp);
            photoPath = null;
            sharePictureAdapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        unbinder.unbind();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if(pvTime.isShowing()){
                pvTime.dismiss();
                return true;
            }
            if(pvOptions.isShowing()){
                pvOptions.dismiss();
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}