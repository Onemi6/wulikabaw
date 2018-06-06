package com.example.wulikabaw;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class SettingActivity extends Activity implements OnClickListener {

	DBManage dbmanage = new DBManage(this);
	
	private TextView mima_xiugai=null;
	private TextView phone_xiugai=null;
	private TextView email_xiugai=null;
	
	private TextView fankui=null;
	private TextView guanyu=null;
	
	private String get_zhanghao;
	private EditText old_mima;
	private EditText new_mima;
	private EditText new_phone;
	private EditText new_email;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_setting);
		// SysApplication.getInstance().addActivity(this);
		
		get_zhanghao=((MyApplication) getApplication()).getValue();
		
		mima_xiugai = (TextView) findViewById(R.id.xiugai_mima);
		mima_xiugai.setOnClickListener(this);
		
		phone_xiugai = (TextView) findViewById(R.id.xiugai_phone);
		phone_xiugai.setOnClickListener(this);
		
		email_xiugai = (TextView) findViewById(R.id.xiugai_email);
		email_xiugai.setOnClickListener(this);
		
		fankui = (TextView) findViewById(R.id.yijian);
		fankui.setOnClickListener(this);
		
		guanyu = (TextView) findViewById(R.id.about);
		guanyu.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		// TODO 自动生成的方法存根
		switch (v.getId()) {
		case R.id.yijian:
			Intent to_problem = new Intent();
			to_problem
					.setClass(SettingActivity.this, ProblemActivity.class);
			//finish();
			SettingActivity.this.startActivity(to_problem);
			break;
		case R.id.xiugai_mima:
			AlertDialog.Builder builder1 = new AlertDialog.Builder(
					SettingActivity.this);
			builder1.setIcon(R.drawable.ic_launcher);
			builder1.setTitle("修改信息");
			// 通过LayoutInflater来加载一个xml的布局文件作为一个View对象
			final View view1 = LayoutInflater.from(SettingActivity.this)
					.inflate(R.layout.modify_information, null);
			// 设置自定义的布局文件作为弹出框的Content
			builder1.setView(view1);
			
			old_mima = (EditText)view1.findViewById(R.id.old_mima_edit);
			new_mima = (EditText)view1.findViewById(R.id.new_mima_edit);
			new_phone = (EditText)view1.findViewById(R.id.new_phone_edit);
			new_email = (EditText)view1.findViewById(R.id.new_email_edit);
			
			new_phone.setText(dbmanage.finduser(get_zhanghao).getphone());
			new_email.setText(dbmanage.finduser(get_zhanghao).getemail());
			
			builder1.setPositiveButton("确定",
					new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog,
								int which) {
							// TODO 自动生成的方法存根
							if(old_mima.getText().toString().equals(dbmanage.finduser(get_zhanghao).getmima())){
								UserInformation new_user = new UserInformation(dbmanage.finduser(get_zhanghao).getlogo(),get_zhanghao,new_mima.getText().toString()
										,new_phone.getText().toString(),new_email.getText().toString(),dbmanage.finduser(get_zhanghao).getcredit());
								dbmanage.updateuser(new_user);
								Toast.makeText(SettingActivity.this, "修改成功",
										Toast.LENGTH_SHORT).show();
							}
							else{
								Toast.makeText(SettingActivity.this, "密码错误,修改失败",
										Toast.LENGTH_SHORT).show();
							}
						}
					});
			builder1.setNegativeButton("取消",
					new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog,
								int which) {
							// TODO 自动生成的方法存根
						}
					});
			builder1.show();
			break;
		case R.id.xiugai_phone:
			AlertDialog.Builder builder2 = new AlertDialog.Builder(
					SettingActivity.this);
			builder2.setIcon(R.drawable.ic_launcher);
			builder2.setTitle("修改信息");
			// 通过LayoutInflater来加载一个xml的布局文件作为一个View对象
			final View view2 = LayoutInflater.from(SettingActivity.this)
					.inflate(R.layout.modify_information, null);
			// 设置自定义的布局文件作为弹出框的Content
			builder2.setView(view2);
			
			old_mima = (EditText)view2.findViewById(R.id.old_mima_edit);
			new_mima = (EditText)view2.findViewById(R.id.new_mima_edit);
			new_phone = (EditText)view2.findViewById(R.id.new_phone_edit);
			new_email = (EditText)view2.findViewById(R.id.new_email_edit);
			
			new_phone.setText(dbmanage.finduser(get_zhanghao).getphone());
			new_email.setText(dbmanage.finduser(get_zhanghao).getemail());
			
			builder2.setPositiveButton("确定",
					new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog,
								int which) {
							// TODO 自动生成的方法存根
							if(old_mima.getText().toString().equals(dbmanage.finduser(get_zhanghao).getmima())){
								UserInformation new_user = new UserInformation(dbmanage.finduser(get_zhanghao).getlogo(),get_zhanghao,new_mima.getText().toString()
										,new_phone.getText().toString(),new_email.getText().toString(),dbmanage.finduser(get_zhanghao).getcredit());
								dbmanage.updateuser(new_user);
								Toast.makeText(SettingActivity.this, "修改成功",
										Toast.LENGTH_SHORT).show();
							}
							else{
								Toast.makeText(SettingActivity.this, "密码错误,修改失败",
										Toast.LENGTH_SHORT).show();
							}
						}
					});
			builder2.setNegativeButton("取消",
					new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog,
								int which) {
							// TODO 自动生成的方法存根
						}
					});
			builder2.show();
			break;
		case R.id.xiugai_email:
			AlertDialog.Builder builder3 = new AlertDialog.Builder(
					SettingActivity.this);
			builder3.setIcon(R.drawable.ic_launcher);
			builder3.setTitle("修改信息");
			// 通过LayoutInflater来加载一个xml的布局文件作为一个View对象
			final View view3 = LayoutInflater.from(SettingActivity.this)
					.inflate(R.layout.modify_information, null);
			// 设置自定义的布局文件作为弹出框的Content
			builder3.setView(view3);
			
			old_mima = (EditText)view3.findViewById(R.id.old_mima_edit);
			new_mima = (EditText)view3.findViewById(R.id.new_mima_edit);
			new_phone = (EditText)view3.findViewById(R.id.new_phone_edit);
			new_email = (EditText)view3.findViewById(R.id.new_email_edit);
			
			new_phone.setText(dbmanage.finduser(get_zhanghao).getphone());
			new_email.setText(dbmanage.finduser(get_zhanghao).getemail());
			
			builder3.setPositiveButton("确定",
					new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog,
								int which) {
							// TODO 自动生成的方法存根
							if(old_mima.getText().toString().equals(dbmanage.finduser(get_zhanghao).getmima())){
								UserInformation new_user = new UserInformation(dbmanage.finduser(get_zhanghao).getlogo(),get_zhanghao,new_mima.getText().toString()
										,new_phone.getText().toString(),new_email.getText().toString(),dbmanage.finduser(get_zhanghao).getcredit());
								dbmanage.updateuser(new_user);
								Toast.makeText(SettingActivity.this, "修改成功",
										Toast.LENGTH_SHORT).show();
							}
							else{
								Toast.makeText(SettingActivity.this, "密码错误,修改失败",
										Toast.LENGTH_SHORT).show();
							}
						}
					});
			builder3.setNegativeButton("取消",
					new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog,
								int which) {
							// TODO 自动生成的方法存根
						}
					});
			builder3.show();
			break;
		default:
			break;
		}

	}
}
