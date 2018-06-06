package com.example.wulikabaw;

import java.util.regex.Pattern;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends Activity implements OnClickListener {

	private Button zhuce_btn = null;
	private EditText zc_et_zhanghao = null;
	private EditText zc_et_mima = null;
	private EditText zc_et_mima_queren = null;
	private EditText zc_et_phone = null;
	private EditText zc_et_email = null;

	DBManage dbmanage = new DBManage(this);

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_register);
		// SysApplication.getInstance().addActivity(this);

		zhuce_btn = (Button) findViewById(R.id.btn_zhuce);
		zhuce_btn.setOnClickListener(this);

		zc_et_zhanghao = (EditText) findViewById(R.id.zhuce_edit_zhanghao);
		zc_et_mima = (EditText) findViewById(R.id.zhuce_edit_mima);
		zc_et_phone = (EditText) findViewById(R.id.zhuce_edit_phone);
		zc_et_email = (EditText) findViewById(R.id.zhuce_edit_email);
		zc_et_mima_queren = (EditText) findViewById(R.id.zhuce_edit_mima_queren);

	}

	@Override
	public void onClick(View v) {
		// TODO 自动生成的方法存根
		switch (v.getId()) {
		case R.id.btn_zhuce:

			Pattern pattern = Pattern
					.compile("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+");

			String _zhanghao = zc_et_zhanghao.getText().toString();
			String _mima = zc_et_mima.getText().toString();
			String _mima_queren = zc_et_mima_queren.getText().toString();
			String _phone = zc_et_phone.getText().toString();
			String _email = zc_et_email.getText().toString();

			if (_zhanghao.equals("")) {
				Toast.makeText(RegisterActivity.this, "账号不能为空",
						Toast.LENGTH_SHORT).show();
			} else if (_mima.equals("")) {
				Toast.makeText(RegisterActivity.this, "密码不能为空",
						Toast.LENGTH_SHORT).show();
			} else if (_phone.equals("")) {
				Toast.makeText(RegisterActivity.this, "手机不能为空",
						Toast.LENGTH_SHORT).show();
			} else if (!(_mima_queren.equals(_mima))) {
				Toast.makeText(RegisterActivity.this, "两次输入的密码不一样哦",
						Toast.LENGTH_SHORT).show();
			} else if (_email.equals("")) {
				Toast.makeText(RegisterActivity.this, "邮箱不能为空",
						Toast.LENGTH_SHORT).show();
			}else if(_phone.length()!=11){
				Toast.makeText(RegisterActivity.this, "手机号确定是对的??",
						Toast.LENGTH_SHORT).show();
			}
			else if (!pattern.matcher(_email).matches()) {
				Toast.makeText(RegisterActivity.this, "这个好像不是正确的邮箱格式哦",
						Toast.LENGTH_SHORT).show();
			} else if ((dbmanage.finduser(_zhanghao) != null)) {
				Toast.makeText(RegisterActivity.this, "账号同款啦,赶紧改改",
						Toast.LENGTH_SHORT).show();
			} else {
				Intent intent_login = new Intent();
				intent_login.putExtra("com.example.wulikabaw.USERZHANGHAO",
						_zhanghao);
				intent_login.putExtra("com.example.wulikabaw.USERMIMA", _mima);

				UserInformation oneuser = new UserInformation(R.drawable.logo1,_zhanghao, _mima,
						_phone, _email,500);
				dbmanage.adduser(oneuser);
				Toast.makeText(RegisterActivity.this, "注册成功",
						Toast.LENGTH_SHORT).show();

				intent_login.setClass(RegisterActivity.this,
						LoginActivity.class);
				finish();
				startActivity(intent_login);
			}
			break;
		default:
			break;
		}

	}

}