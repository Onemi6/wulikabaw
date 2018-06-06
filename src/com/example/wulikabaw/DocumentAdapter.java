package com.example.wulikabaw;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class DocumentAdapter extends ArrayAdapter<DocumentInformation> {

	private int resourceId;
	public DocumentAdapter(Context context, int resource,
			List<DocumentInformation> document_list) {
		super(context, resource,document_list);
		// TODO �Զ����ɵĹ��캯�����
		resourceId = resource;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		DocumentInformation document = getItem(position);
		View view ;
		ViewHolder viewHolder;
		if(convertView == null){
		 view = LayoutInflater.from(getContext()).inflate(resourceId, null);
		 viewHolder = new ViewHolder();
		 viewHolder.document_card_name = (TextView) view
					.findViewById(R.id.document_card_name);
		 viewHolder.document_name = (TextView) view
					.findViewById(R.id.document_name);
		 viewHolder.document_number = (TextView) view
					.findViewById(R.id.document_number);
		 viewHolder.document_balance = (TextView) view
					.findViewById(R.id.document_balance);
		 /*viewHolder.document_qrcode = (ImageView) view
					.findViewById(R.id.document_tupian);*/
		 view.setTag(viewHolder);
		}
		else{
			view = convertView;
			viewHolder = (ViewHolder)view.getTag();
		}
		viewHolder.document_card_name.setText(document.getcard_name());
		viewHolder.document_name.setText(document.getname());
		viewHolder.document_number.setText(document.getnumber());
		viewHolder.document_balance.setText(document.getbalance());
		/*String contents = "����:"+document.getcard_name()
				+"\n����:"+ document.getname()+"\n����:"+document.getnumber()
				+"\n���:"+ document.getbalance() ;
		// ���÷���createCode���ɶ�ά��
		Bitmap bm = createQRImage(contents, 400,400);
		// ����ά���ڽ�������ʾ
		viewHolder.document_qrcode.setImageBitmap(bm);*/
		return view;
	}
	
	class ViewHolder{
		TextView document_card_name;
		TextView document_name;
		TextView document_number;
		TextView document_balance;
		//ImageView document_qrcode;
	}
	
	/**
	 * ���ɶ�ά�� Ҫת���ĵ�ַ���ַ���,����������
	 * 
	 * @param url
	 * @param width
	 * @param height
	 * @return
	 *//*
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
	}*/

}
