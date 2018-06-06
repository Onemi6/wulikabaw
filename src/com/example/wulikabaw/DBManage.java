package com.example.wulikabaw;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

public class DBManage {
	private MyDatabaseHelper dbHelper;
	private SQLiteDatabase db;

	private Context _context;
	public DBManage(Context context) {
		// TODO 自动生成的构造函数存根
		dbHelper = new MyDatabaseHelper(context, "Wulikabaw.db", null, 1);
		//db = dbHelper.getWritableDatabase();
		_context = context;
	}

	public void adduser(UserInformation user) {
		db = dbHelper.getWritableDatabase();
		db.execSQL(
				"insert into User (logo_id,zhanghao,mima,phone,email,credit) values (?,?,?,?,?,?)",
				new Object[] { user.getlogo(), user.getzhanghao(),
						user.getmima(), user.getphone(), user.getemail(), user.getcredit()});
	}

	// 修改用户信息
	public void updateuser(UserInformation user) {
		db = dbHelper.getWritableDatabase();
		db.execSQL(
				"update User set mima=?,phone=?,email=? where zhanghao=?",
				new Object[] { user.getmima(), user.getphone(),
						user.getemail(), user.getzhanghao() });
	}

	public void updatelogo(int logo_id, String zhanghao) {
		db = dbHelper.getWritableDatabase();
		db.execSQL("update User set logo_id=? where zhanghao=?", new Object[] {
				logo_id, zhanghao });
	}

	// 修改积分
	public void updatecredit(int credit, String zhanghao) {
		db = dbHelper.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("credit",credit);
		db.update("User", values, "zhanghao=?", new String[] { zhanghao});
		//db.execSQL("update User set credit=? where zhanghao=?", new Object[] {credit, zhanghao });
	}
	
	public void deteleuser(String zhanghao) {
		db = dbHelper.getWritableDatabase();
		db.execSQL("detele from User where zhanghao = ?",
				new String[] { zhanghao });
	}

	public void addproblem(String text) {
		db = dbHelper.getWritableDatabase();
		db.execSQL("insert into Problem (problem_text,zhanghao) values (?,?)",
				new String[] { text, null });
	}

	public String findproblem(String keyword) {
		db = dbHelper.getWritableDatabase();
		Cursor cursor = db
				.rawQuery(
						"select problem_text,zhanghao from Problem where problem_text like'%?%'",
						new String[] { String.valueOf(keyword) });
		if (cursor.moveToNext()) {
			return cursor.getString(cursor.getColumnIndex("problem_text"));
		}
		return null;
	}

	public void adddocument(DocumentInformation document) {
		db = dbHelper.getWritableDatabase();
		db.execSQL(
				"insert into Document (type,card_name,name,number,balance,zhanghao) values (?,?,?,?,?,?)",
				new Object[] { document.gettype(), document.getcard_name(),
						document.getname(), document.getnumber(),
						document.getbalance(), document.getzhanghao() });
	}
	public UserInformation finduser(String zhanghao) {
		db = dbHelper.getWritableDatabase();
		Cursor cursor = db.rawQuery(
				"select * from User where zhanghao=?",
				new String[] { String.valueOf(zhanghao) });
		if (cursor.moveToNext()) {
			return new UserInformation(cursor.getInt(cursor
					.getColumnIndex("logo_id")),zhanghao, cursor.getString(cursor
					.getColumnIndex("mima")), cursor.getString(cursor
					.getColumnIndex("phone")), cursor.getString(cursor
					.getColumnIndex("email")),cursor.getInt(cursor
							.getColumnIndex("credit"))
					);
		}
		return null;
	}
	public Cursor finddocument(String zhanghao) {
		db = dbHelper.getWritableDatabase();
		Cursor cursor1 = db.rawQuery(
				"select * from Document where zhanghao=?",
				new String[] { String.valueOf(zhanghao)});
		/*if (cursor1.moveToNext()) {
			return new DocumentInformation(cursor1.getString(cursor1.getColumnIndex("type")),
					cursor1.getString(cursor1.getColumnIndex("card_name")),
					cursor1.getString(cursor1.getColumnIndex("name")),
					cursor1.getString(cursor1.getColumnIndex("number")),
					cursor1.getString(cursor1.getColumnIndex("balance")),
					zhanghao);
		}*/
		return cursor1;
	}
	
	public List<DocumentInformation> querydocument(String zhanghao){
		ArrayList<DocumentInformation> documents = new ArrayList<DocumentInformation>();
		Cursor cursor = finddocument(zhanghao);
		while(cursor.moveToNext()){
			DocumentInformation document = new DocumentInformation();
			document.type=cursor.getString(cursor.getColumnIndex("type"));
			document.card_name=cursor.getString(cursor.getColumnIndex("card_name"));
			document.name=cursor.getString(cursor.getColumnIndex("name"));
			document.number=cursor.getString(cursor.getColumnIndex("number"));
			document.balance=cursor.getString(cursor.getColumnIndex("balance"));
			document.zhanghao=zhanghao;
			documents.add(document);
		}
		cursor.close();
		return documents;
	}
	
	public void closeDB(){
		db.close();
	}
}
