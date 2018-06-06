
package com.example.wulikabaw;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.ContentUris;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.BaseColumns;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.provider.MediaStore.MediaColumns;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MyActivity extends Activity implements OnClickListener {

	private long mExitTime;
	private TextView todocument;
	private TextView tomember;
	private String get_zhanghao;
	
	private ImageView wode_logo = null;
	private TextView wode_zhanghao = null;
	private TextView wode_phone = null;
	private TextView wode_email = null;
	private TextView wode_credit = null;
	
	private TextView shezhi = null;

	DBManage dbmanage = new DBManage(this);
	
	private static final int TAKE_PHOTO = 1;
	private static final int CROP_PHOTO = 2;
	private static final int CHOOSE_PHOTO = 3;
	private String imagePath = null;
	private Uri imageUri;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_my);

		wode_logo = (ImageView)findViewById(R.id.wode_text_logo);
		wode_logo.setOnClickListener(this);
		
		wode_zhanghao = (TextView)findViewById(R.id.wode_text_zhanghao);
		wode_phone = (TextView)findViewById(R.id.wode_text_phone);
		wode_email = (TextView)findViewById(R.id.wode_text_email);
		wode_credit = (TextView)findViewById(R.id.wode_text_credit);
		shezhi = (TextView)findViewById(R.id.wode_shezhi);
		shezhi.setOnClickListener(this);
		
		todocument = (TextView)findViewById(R.id.text_zhengjian3);
		todocument.setOnClickListener(this);
		
		tomember = (TextView)findViewById(R.id.text_huiyuan3);
		tomember.setOnClickListener(this);
		
		
		get_zhanghao=((MyApplication) getApplication()).getValue();
		//Log.d("MyActivity", get_zhanghao);
		
		wode_logo.setImageResource(dbmanage.finduser(get_zhanghao).getlogo());
		wode_zhanghao.setText(get_zhanghao);
		wode_phone.setText((dbmanage.finduser(get_zhanghao).getphone()));
		wode_email.setText((dbmanage.finduser(get_zhanghao).getemail()));
		wode_credit.setText(String.valueOf((dbmanage.finduser(get_zhanghao).getcredit())));
		//Log.d("MyActivity",Integer.toString(wode_logo.getId()));
	}

	@Override
	public void onClick(View v) {
		// TODO 自动生成的方法存根
		switch(v.getId()){
		case R.id.text_zhengjian3:
			Intent intent_document = new Intent();
			intent_document
					.setClass(MyActivity.this, DocumentActivity.class);
			finish();
			MyActivity.this.startActivity(intent_document);
			break;
		case R.id.text_huiyuan3:
			Intent intent_member = new Intent();
			intent_member
					.setClass(MyActivity.this, MemberActivity.class);
			finish();
			MyActivity.this.startActivity(intent_member);
			break;
		case R.id.wode_shezhi:
			Intent intent_shezhi = new Intent();
			intent_shezhi
					.setClass(MyActivity.this, SettingActivity.class);
			MyActivity.this.startActivity(intent_shezhi);
			break;
		case R.id.wode_text_logo:
			AlertDialog.Builder builder = new Builder(MyActivity.this);
            builder.setItems(getResources().getStringArray(R.array.ItemArray), new DialogInterface.OnClickListener()
            {
                @Override
				public void onClick(DialogInterface arg0, int arg1)
                {
                    // TODO 自动生成的方法存根
                    System.out.println(arg1);
                    if (arg1 == 0)
                    {
                    	File outputImage = new File(Environment.
        						getExternalStorageDirectory(),"output_image.jpg");
        				try{
        					if(outputImage.exists()){
        						outputImage.delete();
        						}
        					outputImage.createNewFile();
        					}catch(IOException e){
        						e.printStackTrace();
        					}
        				imageUri = Uri.fromFile(outputImage);
        				Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        				intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        				startActivityForResult(intent,TAKE_PHOTO);
                    }
                    else if(arg1 == 1){
                    	Intent intent = new Intent("android.intent.action.GET_CONTENT");
        				intent.setType("image/*");
        				startActivityForResult(intent,CHOOSE_PHOTO);//打开相册
                    }
                    arg0.dismiss();
                }
            });
            builder.show();
            Log.d("MyActivity",Integer.toString(wode_logo.getId()));
            
            //暂未实现图片存储
            dbmanage.updatelogo(R.drawable.logo1, get_zhanghao);
            
            //dbmanage.finduser(get_zhanghao).setlogo(R.drawable.logo1);
			break;
			default:
				break;
		}
	}

	
	@Override
	protected void onActivityResult(int requestCode,int resultCode,Intent data){
		
		// 用户没有进行有效的设置操作，返回
        if (resultCode == RESULT_CANCELED) {
            Toast.makeText(getApplication(), "取消", Toast.LENGTH_LONG).show();
            return;
        }
		switch(requestCode){
		case TAKE_PHOTO:
			if(resultCode == RESULT_OK){
				Intent intent = new Intent("com.android.camera.action.CROP");
				intent.setDataAndType(imageUri, "image/*");
				intent.putExtra("scale", true);
				intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
				startActivityForResult(intent,CROP_PHOTO);
			}
			break;
		case CROP_PHOTO:
			if(resultCode == RESULT_OK){
				try{
					Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri));
					wode_logo.setImageBitmap(bitmap);//将剪裁后的照片显示出来
				}catch(FileNotFoundException e){
					e.printStackTrace();
				}
			}
			
			break;
		case CHOOSE_PHOTO:
			if(resultCode == RESULT_OK){
				//判断手机系统版本号
				if(Build.VERSION.SDK_INT>=19){
					//4.4及以上系统用此方法处理图片
					handleImgeOnKitKat(data);
				}else{
					//4.4以下系统用此方法处理图片
					handleImgeBeforeKitKat(data);
				}	
			}	
			break;
			default:
				break;
		}
	}

	

	@TargetApi(19)
	private void handleImgeOnKitKat(Intent data) {
		// TODO 自动生成的方法存根
		
		Uri uri=data.getData();
		if(DocumentsContract.isDocumentUri(this, uri)){
			//如果是Document类型的Uri,则通过Document id处理
			String docId = DocumentsContract.getDocumentId(uri);
			if("com.android.providers.media.documents".equals(uri.getAuthority())){
				String id = docId.split(":")[1];//解析出数字格式的id
				String selection= BaseColumns._ID+"="+id;
				imagePath = getImagePath(
						MediaStore.Images.Media.EXTERNAL_CONTENT_URI,selection);
			}
				else if("com.android.providers.downloads.documents".equals(uri.getAuthority())){
					Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"),
							Long.valueOf(docId));
					imagePath = getImagePath(contentUri,null);
				}
		}else if("content".equalsIgnoreCase(uri.getScheme())){
			//如果不是Document类型的Uri,则使用普通方式处理
			imagePath = getImagePath(uri,null);
		}
		displayImage(imagePath);//根据图片路径显示图片
		
	}
	
	private void handleImgeBeforeKitKat(Intent data) {
		// TODO 自动生成的方法存根
		Uri uri = data.getData();
		String imagePath = getImagePath(uri,null);
		displayImage(imagePath);
	}

	private String getImagePath(Uri uri, String selection) {
		// TODO 自动生成的方法存根
		String path=null;
		//
		Cursor cursor = getContentResolver().query(uri, null, selection, null, null);
		if(cursor != null){
			if(cursor.moveToFirst()){
				path = cursor.getString(cursor.getColumnIndex(MediaColumns.DATA));
			}
			cursor.close();
		}
		return path;
	}
	
	private void displayImage(String imagePath) {
		// TODO 自动生成的方法存根
		if(imagePath !=null){
			Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
			wode_logo.setImageBitmap(bitmap);
		}else{
			Toast.makeText(this,"从相册获取图片失败",Toast.LENGTH_SHORT).show();
		}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			if ((System.currentTimeMillis() - mExitTime) > 2000) {
				Object mHelperUtils;
				Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
				mExitTime = System.currentTimeMillis();
			} else {
				finish();
			}
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
}