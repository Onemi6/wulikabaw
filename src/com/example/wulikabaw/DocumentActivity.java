package com.example.wulikabaw;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

public class DocumentActivity extends Activity {

	private long mExitTime;
	private Button search;
	private Button add; // ���
	private EditText content; // ����
	private ListView listview;
	private TextView tomember;
	private TextView tomy;

	private ArrayAdapter<String> adapter_type;
	private ArrayAdapter<String> adapter_name;

	private DocumentAdapter adapter;
	private ListView documentlist;
	private List<DocumentInformation> document_list = new ArrayList<DocumentInformation>();

	DBManage dbmanage = new DBManage(this);
	private String get_zhanghao;

	/*
	 * private String document_type; private String document_cardname; private
	 * String document_name; private String document_number; private String
	 * document_balance;
	 */

	private int p1,p2,i = 0;;
	private String[] data;
	/*
	 * public List<String> getTypeDataSource() { List<String> list = new
	 * ArrayList<String>(); list.add("��"); list.add("֤��"); return list; }
	 */
	private String[] type = new String[] { "��", "֤��" };
	private String[][] name = new String[][] {
			{ "����", "����", "��ӰԺ", "KTV", "����" },
			{ "ѧ��֤", "����֤", "�м�֤", "����", "ǩ֤", "����֤" } };
	
	

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_document);
		
		tomember = (TextView) findViewById(R.id.text_huiyuan1);

		tomy = (TextView) findViewById(R.id.text_wode1);

		add = (Button) findViewById(R.id.document_add_btn);
		search = (Button) findViewById(R.id.document_search_btn);
		content = (EditText) findViewById(R.id.document_searchkey);

		get_zhanghao = ((MyApplication) getApplication()).getValue();
		Log.d("DocumentActivity", get_zhanghao);
		
		init();
		if (i == 1) {
			adapter.clear();
			adapter.notifyDataSetChanged();
			i = 0;
		}
		adapter = new DocumentAdapter(
				DocumentActivity.this,
				R.layout.document_item,
				document_list);
		documentlist = (ListView) findViewById(R.id.list_document_results);
		documentlist.setAdapter(adapter);
		
		documentlist.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO �Զ����ɵķ������
				DocumentInformation document=document_list.get(position);
				
				String contents = "����:"+document.getcard_name()
						+"\n����:"+ document.getname()+"\n����:"+document.getnumber()
						+"\n���:"+ document.getbalance() ;
				Log.d("DocumentActivity", contents);
				
				// ���÷���createCode���ɶ�ά��
				Bitmap bm = createQRImage(contents, 400,400);
				ImageView qr_img = new ImageView(DocumentActivity.this);
				qr_img.setImageBitmap(bm);
				AlertDialog.Builder builder_qr=new AlertDialog.Builder(DocumentActivity.this);
				builder_qr.setTitle("��ά��");
				builder_qr.setView(qr_img);
				builder_qr.setPositiveButton("ȷ��", null);
				builder_qr.show();
				
				Toast.makeText(DocumentActivity.this , document.getcard_name(), Toast.LENGTH_SHORT).show();
			}
			
		});

		tomember.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent_member = new Intent();
				intent_member.setClass(DocumentActivity.this,
						MemberActivity.class);

				finish();
				DocumentActivity.this.startActivity(intent_member);
			}
		});

		tomy.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent_my = new Intent();
				intent_my.setClass(DocumentActivity.this, MyActivity.class);
				finish();
				DocumentActivity.this.startActivity(intent_my);
			}
		});
		search.setOnClickListener(new OnClickListener() {		
			@Override
			public void onClick(View v) {
				// TODO �Զ����ɵķ������

				if (content.getText().toString().equals("")) {
					Toast.makeText(DocumentActivity.this, "���ѵ�ʲô��",
							Toast.LENGTH_SHORT).show();
				} else {
					adapter.clear();
					adapter.notifyDataSetChanged();
					init();
					AlertDialog.Builder builder = new AlertDialog.Builder(
							DocumentActivity.this);
					builder.setMessage("ȷ�������ؼ���:"
							+ content.getText().toString());
					builder.setTitle("����");
					builder.setPositiveButton("ȷ��",
							new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									// TODO �Զ����ɵķ������
									Iterator<DocumentInformation> dl = document_list.iterator();
									while(dl.hasNext())
									{
										if(content.getText().toString().equals(dl.next().getcard_name())){
										}
										else{
											dl.remove();
										}
									}
									adapter = new DocumentAdapter(
											DocumentActivity.this,
											R.layout.document_item,
											document_list);
									documentlist = (ListView) findViewById(R.id.list_document_results);
									documentlist.setAdapter(adapter);
									content.setText("");	
									i = 1;
									Toast.makeText(DocumentActivity.this,
											"�����ɹ�", Toast.LENGTH_SHORT).show();
								}
							});
					builder.setNegativeButton("ȡ��",
							new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									// TODO �Զ����ɵķ������
								}
							});
					builder.show();

				}
			}
		});
		
		add.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				AlertDialog.Builder builder = new AlertDialog.Builder(
						DocumentActivity.this);
				builder.setIcon(R.drawable.ic_launcher);
				builder.setTitle("���");
				// ͨ��LayoutInflater������һ��xml�Ĳ����ļ���Ϊһ��View����
				final View view2 = LayoutInflater.from(DocumentActivity.this)
						.inflate(R.layout.dialog_add, null);
				// �����Զ���Ĳ����ļ���Ϊ�������Content
				builder.setView(view2);
				final Spinner spinner_type = (Spinner) view2
						.findViewById(R.id.spinner_type);

				final Spinner spinner_name = (Spinner) view2
						.findViewById(R.id.spinner_name);

				adapter_type = new ArrayAdapter<>(DocumentActivity.this,
						android.R.layout.simple_spinner_dropdown_item, type);
				spinner_type.setAdapter(adapter_type);

				adapter_name = new ArrayAdapter<>(DocumentActivity.this,
						android.R.layout.simple_spinner_dropdown_item, name[0]);
				spinner_name.setAdapter(adapter_name);

				spinner_type
						.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
							@Override
							public void onItemSelected(AdapterView<?> arg0,
									View arg1, int position1, long arg3) {
								adapter_name = new ArrayAdapter<>(
										DocumentActivity.this,
										android.R.layout.simple_spinner_dropdown_item,
										name[position1]);
								p1 = position1;
								spinner_name
										.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
											@Override
											public void onItemSelected(
													AdapterView<?> arg0,
													View arg1, int position2,
													long arg3) {
												p2=position2;
											}

											@Override
											public void onNothingSelected(
													AdapterView<?> parent) {
												// TODO �Զ����ɵķ������
											}
										});
								spinner_name.setAdapter(adapter_name);
							}

							@Override
							public void onNothingSelected(AdapterView<?> parent) {
								// TODO �Զ����ɵķ������

							}
						});
				builder.setPositiveButton("ȷ��",
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								// TODO �Զ����ɵķ������

								EditText personal_name = (EditText) view2
										.findViewById(R.id.document_edit_name);
								EditText number = (EditText) view2
										.findViewById(R.id.document_edit_num);
								EditText balance = (EditText) view2
										.findViewById(R.id.document_edit_balance);
								
								if(personal_name.getText().toString().equals("")||number.getText().toString().equals("")||balance.getText().toString().equals(""))
								{
									AlertDialog.Builder builder = new AlertDialog.Builder(
											DocumentActivity.this);
									builder.setMessage("����û������,���ʧ��");
									builder.setTitle("��ʾ");
									builder.setPositiveButton("ȷ��",
											new DialogInterface.OnClickListener() {
												@Override
												public void onClick(DialogInterface dialog,
														int which) {
													// TODO �Զ����ɵķ������
												}
											});
									builder.show();
									/*Toast.makeText(DocumentActivity.this, "����û������",
											Toast.LENGTH_SHORT).show();*/
								}
								else{
								DocumentInformation document = new DocumentInformation(
										type[p1], name[p1][p2], personal_name
												.getText().toString(), number
												.getText().toString(), balance
												.getText().toString(),
										get_zhanghao);
								dbmanage.adddocument(document);
								adapter.clear();
								adapter.notifyDataSetChanged();
								init();
								adapter = new DocumentAdapter(
										DocumentActivity.this,
										R.layout.document_item,
										document_list);
								documentlist = (ListView) findViewById(R.id.list_document_results);
								documentlist.setAdapter(adapter);
								i=1;
								Toast.makeText(DocumentActivity.this, "��ӳɹ�",
										Toast.LENGTH_SHORT).show();
								}
							}
						});
				builder.setNegativeButton("ȡ��",
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								// TODO �Զ����ɵķ������
							}
						});
				builder.show();
			}
		});
	}

	private void init() {
		Log.d("DocumentActivity", get_zhanghao);
		
		List<DocumentInformation> result = dbmanage.querydocument(get_zhanghao);
		if(result != null){
			for(DocumentInformation document:result){
				document_list.add(document);
			}
		}

	}

	/**
	 * ���ɶ�ά�� Ҫת���ĵ�ַ���ַ���,����������
	 * 
	 * @param url
	 * @param width
	 * @param height
	 * @return
	 */
	public Bitmap createQRImage(String url, final int width, final int height) {
		try {
			// �ж�URL�Ϸ���
			if (url == null || "".equals(url) || url.length() < 1) {
				return null;
			}
			Hashtable<EncodeHintType, String> hints = new Hashtable<EncodeHintType, String>();
			hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
			// ͼ������ת����ʹ���˾���ת��
			BitMatrix bitMatrix = new QRCodeWriter().encode(url,
					BarcodeFormat.QR_CODE, width, height, hints);
			int[] pixels = new int[width * height];
			// �������ﰴ�ն�ά����㷨��������ɶ�ά���ͼƬ��
			// ����forѭ����ͼƬ����ɨ��Ľ��
			for (int y = 0; y < height; y++) {
				for (int x = 0; x < width; x++) {
					if (bitMatrix.get(x, y)) {
						pixels[y * width + x] = 0xff000000;
					} else {
						pixels[y * width + x] = 0xffffffff;
					}
				}
			}
			// ���ɶ�ά��ͼƬ�ĸ�ʽ��ʹ��ARGB_8888
			Bitmap bitmap = Bitmap.createBitmap(width, height,
					Bitmap.Config.ARGB_8888);
			bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
			return bitmap;
		} catch (WriterException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			if ((System.currentTimeMillis() - mExitTime) > 2000) {
				Object mHelperUtils;
				Toast.makeText(this, "�ٰ�һ���˳�����", Toast.LENGTH_SHORT).show();
				mExitTime = System.currentTimeMillis();
			} else {
				finish();
			}
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
	
	

}
