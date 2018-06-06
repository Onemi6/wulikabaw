package com.example.wulikabaw;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ProblemActivity extends Activity implements OnClickListener {

	private Button tijiao_btn = null;
	private Button qingchu_btn = null;
	private EditText problem_edit = null;
	private TextView to_baidu = null;

	DBManage dbmanage = new DBManage(this);

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_problem);
		//SysApplication.getInstance().addActivity(this);

		tijiao_btn = (Button) findViewById(R.id.submit_btn);
		tijiao_btn.setOnClickListener(this);

		qingchu_btn = (Button) findViewById(R.id.clear_btn);
		qingchu_btn.setOnClickListener(this);

		problem_edit = (EditText) findViewById(R.id.problem_edit);
		
		to_baidu=(TextView)findViewById(R.id.to_baidu);
		to_baidu.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO 自动生成的方法存根
		switch (v.getId()) {
		case R.id.submit_btn:
			Intent intent_login = new Intent();
			String problem = problem_edit.getText().toString();
			
			if (problem.equals("")) {
				Toast.makeText(ProblemActivity.this, "是不是忘了写点什么",
						Toast.LENGTH_SHORT).show();
			} else {
				dbmanage.addproblem(problem);
				Toast.makeText(ProblemActivity.this, "提交成功", Toast.LENGTH_SHORT)
						.show();
				intent_login
						.setClass(ProblemActivity.this, LoginActivity.class);
				finish();
				startActivity(intent_login);
			}
			break;
		case R.id.clear_btn:
			problem_edit.setText(""); //将编辑框里内容清空
			//problem_edit.setSelection(0);
			break;
		case R.id.to_baidu:
			Intent intent_web = new Intent();
			intent_web
			.setClass(ProblemActivity.this, WebActivity.class);
	       //finish();
	       startActivity(intent_web);
		default:
			break;
		}
	}

}
