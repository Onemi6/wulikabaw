package com.example.wulikabaw;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MemberActivity extends Activity {

	private long mExitTime;
	private TextView todocument;
	private TextView tomy;
	private String get_zhanghao;
	private ListView creditchangelist1;
	private ListView creditchangelist2;
	private List<Credit_change> credit_change_list1 = new ArrayList<Credit_change>();
	private List<Credit_change> credit_change_list2 = new ArrayList<Credit_change>();

	DBManage dbmanage = new DBManage(this);

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_member);

		get_zhanghao = ((MyApplication) getApplication()).getValue();
		Log.d("MemberActivity", get_zhanghao);

		init();
		CreditChangeAdapter adapter1 = new CreditChangeAdapter(
				MemberActivity.this, R.layout.credit_need_item,
				credit_change_list1);
		CreditChangeAdapter adapter2 = new CreditChangeAdapter(
				MemberActivity.this, R.layout.credit_need_item,
				credit_change_list2);
		creditchangelist1 = (ListView) findViewById(R.id.list_document_results1);
		creditchangelist1.setAdapter(adapter1);
		creditchangelist2 = (ListView) findViewById(R.id.list_document_results2);
		creditchangelist2.setAdapter(adapter2);

		creditchangelist1.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				final Credit_change thing1 = credit_change_list1.get(position);
				AlertDialog.Builder builder = new AlertDialog.Builder(
						MemberActivity.this);
				builder.setMessage("需要"+thing1.getValue()+"积分,"+"确认兑换吗？");
				builder.setTitle("提示");
				builder.setPositiveButton("确定",
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								// TODO 自动生成的方法存根
								if(dbmanage.finduser(get_zhanghao).getcredit() - thing1.getValue()<0)
								{
									Toast.makeText(MemberActivity.this, "积分不足",
											Toast.LENGTH_SHORT).show();
								}
								else{
								dbmanage.updatecredit(
										(dbmanage.finduser(get_zhanghao)
												.getcredit() - thing1
												.getValue()), get_zhanghao);
								Toast.makeText(MemberActivity.this, "兑换成功",
										Toast.LENGTH_SHORT).show();
								}
							}
						});
				builder.setNegativeButton("取消",
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								// TODO 自动生成的方法存根
							}
						});
				builder.show();
				/*
				 * Toast.makeText(MemberActivity.this, thing.getCredit_Need(),
				 * Toast.LENGTH_SHORT).show();
				 */
			}
		});
		creditchangelist2.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				final Credit_change thing2 = credit_change_list2.get(position);
				AlertDialog.Builder builder = new AlertDialog.Builder(
						MemberActivity.this);
				builder.setMessage("需要"+thing2.getValue()+"积分,"+"确认兑换吗？");
				builder.setTitle("提示");
				builder.setPositiveButton("确定",
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								// TODO 自动生成的方法存根
								if(dbmanage.finduser(get_zhanghao).getcredit() - thing2.getValue()<0)
								{
									Toast.makeText(MemberActivity.this, "积分不足",
											Toast.LENGTH_SHORT).show();
								}
								else{
								dbmanage.updatecredit(
										(dbmanage.finduser(get_zhanghao)
												.getcredit() - thing2
												.getValue()), get_zhanghao);
								Toast.makeText(MemberActivity.this, "兑换成功",
										Toast.LENGTH_SHORT).show();
								}
							}
						});
				builder.setNegativeButton("取消",
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								// TODO 自动生成的方法存根
							}
						});
				builder.show();
				/*
				 * Toast.makeText(MemberActivity.this, thing.getCredit_Need(),
				 * Toast.LENGTH_SHORT).show();
				 */
			}
		});

		todocument = (TextView) findViewById(R.id.text_zhengjian2);
		todocument.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent_document = new Intent();
				intent_document.setClass(MemberActivity.this,
						DocumentActivity.class);
				finish();
				MemberActivity.this.startActivity(intent_document);
			}
		});
		tomy = (TextView) findViewById(R.id.text_wode2);
		tomy.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent_my = new Intent();
				intent_my.setClass(MemberActivity.this, MyActivity.class);
				finish();
				MemberActivity.this.startActivity(intent_my);
			}
		});
	}

	private void init() {
		Credit_change thing1 = new Credit_change("5积分", R.drawable.p1, 5);
		credit_change_list1.add(thing1);
		Credit_change thing2 = new Credit_change("15积分", R.drawable.p2, 15);
		credit_change_list2.add(thing2);
		Credit_change thing3 = new Credit_change("40积分", R.drawable.p3, 40);
		credit_change_list1.add(thing3);
		Credit_change thing4 = new Credit_change("50积分", R.drawable.p4, 50);
		credit_change_list2.add(thing4);
		Credit_change thing5 = new Credit_change("100积分", R.drawable.p5, 100);
		credit_change_list1.add(thing5);
		Credit_change thing6 = new Credit_change("200积分", R.drawable.p6, 200);
		credit_change_list2.add(thing6);
		Credit_change thing7 = new Credit_change("500积分", R.drawable.p7, 500);
		credit_change_list1.add(thing7);
		Credit_change thing8 = new Credit_change("1000积分", R.drawable.p8, 1000);
		credit_change_list2.add(thing8);
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
