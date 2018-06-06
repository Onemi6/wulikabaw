package com.example.wulikabaw;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDatabaseHelper extends SQLiteOpenHelper {
	// ����user��
	public static final String CREATE_USER = "create table User("
			+ "id integer primary key autoincrement," +"logo_id integer,"+ " zhanghao text ,"
			+ "mima text ," + "phone text ," + "email text,"+"credit integer)";
	// ����document��
	public static final String CREATE_DOCUMENT = "create table Document("
			+ "id integer primary key autoincrement," + "type text,"+"card_name text,"+"name text,"
			+ "number text,"+"balance text," + "zhanghao text)"; 
	
	// ����problem��
	public static final String CREATE_PROBLEM = "create table Problem("
			+ "id integer primary key autoincrement," +" problem_text text,"+"zhanghao text)";

	public static final String DROP_USER = "drop table if exists User";

	 private Context mContext;

	public MyDatabaseHelper(Context context, String name,
			CursorFactory factory, int version) {
		super(context, name, factory, version);
		// TODO �Զ����ɵĹ��캯�����
		 mContext = context;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO �Զ����ɵķ������
		db.execSQL(CREATE_USER);
		db.execSQL(CREATE_DOCUMENT);
		db.execSQL(CREATE_PROBLEM);
		
		
		// Toast.makeText(mContext, "Create succeeded",
		// Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO �Զ����ɵķ������

		/*
		 * //db.execSQL(DROP_USER); switch (oldVersion) { case 1:
		 * db.execSQL(CREATE_DOCUMENT); default: } onCreate(db);
		 */
	}

}
