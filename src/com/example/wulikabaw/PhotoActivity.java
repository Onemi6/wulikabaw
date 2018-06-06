/*package com.example.wulikabaw;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ContentUris;
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
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class PhotoActivity extends Activity{

	DBManage dbmanage = new DBManage(this);
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_photo);
		takePhoto =(Button)findViewById(R.id.take_photo);
		picture =(ImageView)findViewById(R.id.change_logo);
		choosePhoto =(Button)findViewById(R.id.choose_photo);
		
		Intent get_intent = getIntent();
		zhanghao = get_intent.getStringExtra("zhanghao");
		
		Log.d("PhotoActivity",zhanghao);
		
		takePhoto.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				//创建File对象,用于存储拍照后的照片
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
		});
		
		choosePhoto.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				Intent intent = new Intent("android.intent.action.GET_CONTENT");
				intent.setType("image/*");
				startActivityForResult(intent,CHOOSE_PHOTO);//打开相册
			}
		});
}
	
}
*/