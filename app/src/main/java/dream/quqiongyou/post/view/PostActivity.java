package dream.quqiongyou.post.view;

import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.MediaStore.Images.ImageColumns;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import dream.quqiongyou.post.view.RichTextEditor.EditData;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import dream.quqiongyou.R;

/**
 * 主Activity入口
 * 
 * @author xmuSistone
 * 
 */
@SuppressLint("SimpleDateFormat")
public class PostActivity extends FragmentActivity {
	private static final int REQUEST_CODE_PICK_IMAGE = 1023;
	private static final int REQUEST_CODE_CAPTURE_CAMEIA = 1022;
	private RichTextEditor editor;
	private View btn1, btn2;
	private OnClickListener btnListener;

	private static final File PHOTO_DIR = new File(
			Environment.getExternalStorageDirectory() + "/DCIM/Camera");
	private File mCurrentPhotoFile;// 照相机拍照得到的图片

	public static void startPostActivity(Context context) {
		Intent intent = new Intent(context, PostActivity.class);
		context.startActivity(intent);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_post);

		editor = (RichTextEditor) findViewById(R.id.richEditor);
		btnListener = new OnClickListener() {

			@Override
			public void onClick(View v) {
				editor.hideKeyBoard();
				if (v.getId() == btn1.getId()) {
					// 打开系统相册
					Intent intent = new Intent(Intent.ACTION_PICK);
					intent.setType("image/*");// 相片类型
					startActivityForResult(intent, REQUEST_CODE_PICK_IMAGE);
				} else if (v.getId() == btn2.getId()) {
					// 打开相机
					openCamera();
				}
//				else if (v.getId() == btn3.getId()) {
//					List<EditData> editList = editor.buildEditData();
//					// 下面的代码可以上传、或者保存，请自行实现
//					dealEditData(editList);
//				}
			}
		};

		btn1 = findViewById(R.id.button1);
		btn2 = findViewById(R.id.emoji);

		btn1.setOnClickListener(btnListener);
		btn2.setOnClickListener(btnListener);
	}

	/**
	 * 负责处理编辑数据提交等事宜，请自行实现
	 */
	protected void dealEditData(List<EditData> editList) {
		for (EditData itemData : editList) {
			if (itemData.inputStr != null) {
				Log.d("RichEditor", "commit inputStr=" + itemData.inputStr);
			} else if (itemData.imagePath != null) {
				Log.d("RichEditor", "commit imgePath=" + itemData.imagePath);
			}

		}
	}

	protected void openCamera() {
		try {
			// Launch camera to take photo for selected contact
			PHOTO_DIR.mkdirs();// 创建照片的存储目录
			mCurrentPhotoFile = new File(PHOTO_DIR, getPhotoFileName());// 给新照的照片文件命名
			final Intent intent = getTakePickIntent(mCurrentPhotoFile);
			startActivityForResult(intent, REQUEST_CODE_CAPTURE_CAMEIA);
		} catch (ActivityNotFoundException e) {
		}
	}

	public static Intent getTakePickIntent(File f) {
		Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE, null);
		intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));
		return intent;
	}

	/**
	 * 用当前时间给取得的图片命名
	 */
	private String getPhotoFileName() {
		Date date = new Date(System.currentTimeMillis());
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"'IMG'_yyyy-MM-dd HH:mm:ss");
		return dateFormat.format(date) + ".jpg";
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode != RESULT_OK) {
			return;
		}

		if (requestCode == REQUEST_CODE_PICK_IMAGE) {
			Uri uri = data.getData();
			insertBitmap(getRealFilePath(uri));
		} else if (requestCode == REQUEST_CODE_CAPTURE_CAMEIA) {
			insertBitmap(mCurrentPhotoFile.getAbsolutePath());
		}
	}

	/**
	 * 添加图片到富文本剪辑器
	 * 
	 * @param imagePath
	 */
	private void insertBitmap(String imagePath) {
		editor.insertImage(imagePath);
	}

	/**
	 * 根据Uri获取图片文件的绝对路径
	 */
	public String getRealFilePath(final Uri uri) {
		if (null == uri) {
			return null;
		}

		final String scheme = uri.getScheme();
		String data = null;
		if (scheme == null) {
			data = uri.getPath();
		} else if (ContentResolver.SCHEME_FILE.equals(scheme)) {
			data = uri.getPath();
		} else if (ContentResolver.SCHEME_CONTENT.equals(scheme)) {
			Cursor cursor = getContentResolver().query(uri,
					new String[] { ImageColumns.DATA }, null, null, null);
			if (null != cursor) {
				if (cursor.moveToFirst()) {
					int index = cursor.getColumnIndex(ImageColumns.DATA);
					if (index > -1) {
						data = cursor.getString(index);
					}
				}
				cursor.close();
			}
		}
		return data;
	}
}
