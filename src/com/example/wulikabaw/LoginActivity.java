package com.example.wulikabaw;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends Activity implements OnClickListener {

	private ImageView logo=null;
	private Button button = null;
	private EditText et_zhanghao = null;
	private EditText et_mima = null;
	private SharedPreferences pref;
	private SharedPreferences.Editor editor;
	// private ProgressBar progressBar = null;

	private CheckBox rememberPass;
	private TextView btn_wfdl = null;
	private TextView btn_zhuce = null;

	DBManage dbmanage = new DBManage(this);

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_login);
		pref = PreferenceManager.getDefaultSharedPreferences(this);
		//SysApplication.getInstance().addActivity(this);

		button = (Button) findViewById(R.id.btn_login);
		button.setOnClickListener(this);

		rememberPass = (CheckBox)findViewById(R.id.remember_pass);
		
		logo=(ImageView)findViewById(R.id.profile_image);
		logo.setBackgroundColor(Color.TRANSPARENT);
		//logo.setAlpha(20);
		et_zhanghao = (EditText) findViewById(R.id.edit_zhanghao);
		et_mima = (EditText) findViewById(R.id.edit_mima);

		// progressBar = (ProgressBar) findViewById(R.id.progress_login);
		// progressBar.setVisibility(View.GONE);

		btn_wfdl = (TextView) findViewById(R.id.text_wfdl);
		btn_wfdl.setOnClickListener(this);

		btn_zhuce = (TextView) findViewById(R.id.text_zhuce);
		btn_zhuce.setOnClickListener(this);

		Intent get_intent = getIntent();
		String get_zhanghao = get_intent
				.getStringExtra("com.example.wulikabaw.USERZHANGHAO");
		String get_mima = get_intent
				.getStringExtra("com.example.wulikabaw.USERMIMA");
		et_zhanghao.setText(get_zhanghao);
		et_mima.setText(get_mima);
		
		boolean isRemember = pref.getBoolean("remember_password", false);
		if(isRemember){
			String account = pref.getString("accout","");
			String password = pref.getString("password","");
			et_zhanghao.setText(account);
			et_mima.setText(password);
			rememberPass.setChecked(true);
		}

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

		String _zhanghao = et_zhanghao.getText().toString();
		String _mima = et_mima.getText().toString();

		Intent intent_document = new Intent();

		intent_document.setClass(LoginActivity.this, DocumentActivity.class);
		switch (v.getId()) {
		case R.id.btn_login:

			if (_zhanghao.equals("")) {
				Toast.makeText(LoginActivity.this, "账号不能为空", Toast.LENGTH_SHORT)
						.show();
				// progressBar.setVisibility(View.GONE);
			} else if (_mima.equals("")) {
				Toast.makeText(LoginActivity.this, "密码不能为空", Toast.LENGTH_SHORT)
						.show();
				// progressBar.setVisibility(View.GONE);
			} else {
				if ((dbmanage.finduser(_zhanghao)!=null)&&(dbmanage.finduser(_zhanghao).getmima().equals(_mima))) {
					((MyApplication) getApplication()).setValue(_zhanghao);//赋值操作
					
					editor = pref.edit();
					if(rememberPass.isChecked()){
						editor.putBoolean("remember_password", true);
						editor.putString("accout", _zhanghao);
						editor.putString("password", _mima);
						
					}else{
						editor.clear();
					}
					editor.commit();
					Toast.makeText(LoginActivity.this, "登录中",
							Toast.LENGTH_SHORT).show();

					finish();//结束当前活动
					LoginActivity.this.startActivity(intent_document);
				} else {
					Toast.makeText(LoginActivity.this, "账号或密码错误",
							Toast.LENGTH_SHORT).show();
					// progressBar.setVisibility(View.GONE);
				}
			}
			et_mima.setText("");
			break;
		case R.id.text_wfdl:
			Intent intent_pronlem = new Intent();
			intent_pronlem
					.setClass(LoginActivity.this, ProblemActivity.class);
			//finish();
			LoginActivity.this.startActivity(intent_pronlem);
			break;
		case R.id.text_zhuce:
			Intent intent_register = new Intent();
			intent_register
					.setClass(LoginActivity.this, RegisterActivity.class);
			//finish();
			LoginActivity.this.startActivity(intent_register);
			break;
		default:
			break;
		}
		onDestroy();
	}

	/**
	 * 如果下面的语句不要，那么系统运行的时候会直接进入本程序中，而不是先进入主菜单 再进入选择应用程序界面进入本程序
	 * 为了方便调试，这里就不进入主菜单界面了
	 */

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	@Override
	public void onDestroy()
	{
		super.onDestroy();
	}
}
