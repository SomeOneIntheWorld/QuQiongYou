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
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemClick;
import butterknife.OnItemLongClick;
import butterknife.Unbinder;
import dream.quqiongyou.R;
import dream.quqiongyou.adapter.UISharePictureAdapter;
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
    private String photoPath;

    @BindView(R.id.origination_add)GridView addPictureGV;

    public static void startOriginationActivity(Context context){
        Intent intent = new Intent(context,OriginationActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_origination);
        unbinder = ButterKnife.bind(this);

        Bitmap bp = BitmapFactory.decodeResource(getResources(), R.drawable.menu);
        sharedList.add(bp);
        sharePictureAdapter = new UISharePictureAdapter(getApplicationContext(), sharedList, addPictureGV);
        addPictureGV.setAdapter(sharePictureAdapter);
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
            } else {
                Toast.makeText(OriginationActivity.this, "点击第" + (position + 1) + " 号图片", Toast.LENGTH_SHORT).show();
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
}