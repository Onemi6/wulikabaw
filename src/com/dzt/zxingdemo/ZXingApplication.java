package com.dzt.zxingdemo;

import android.app.Application;
import android.content.Context;
import android.util.Log;

public class ZXingApplication extends Application {

	public static final String TAG = "zxing_dzt";
	private static final boolean mIsShowLog = true;
	private static ZXingApplication instance;

	/**
	 * 全局的打印消息函数
	 * 
	 * @param className
	 *            类名
	 * @param msg
	 *            要打印的消息
	 * @date 2014.07.28
	 */
	public static void print_i(String className, String msg) {
		if (mIsShowLog)
			Log.i(TAG, className + "---------->" + msg);
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		instance = this;
	}

	@Override
	public void onLowMemory() {
		// TODO Auto-generated method stub
		super.onLowMemory();
		System.gc();
	}

	public static Context getAppContext() {
		return instance;
	}
}
