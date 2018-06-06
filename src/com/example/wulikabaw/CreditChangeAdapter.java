package com.example.wulikabaw;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CreditChangeAdapter extends ArrayAdapter<Credit_change> {
	private int resourceId;

	public CreditChangeAdapter(Context context, int resource,
			List<Credit_change> objects) {
		super(context, resource, objects);
		// TODO 自动生成的构造函数存根
		resourceId = resource;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		Credit_change credit_change1 = getItem(position);
		View view ;
		ViewHolder viewHolder;
		if(convertView == null){
		 view = LayoutInflater.from(getContext()).inflate(resourceId, null);
		 viewHolder = new ViewHolder();
		 viewHolder.credit_need_image = (ImageView) view
					.findViewById(R.id.credit_need_image);
		 viewHolder.credit_need_text = (TextView) view
					.findViewById(R.id.credit_need_text);
		 view.setTag(viewHolder);
		}
		else{
			view = convertView;
			viewHolder = (ViewHolder)view.getTag();
		}
		viewHolder.credit_need_image.setImageResource(credit_change1.getImageid());
		viewHolder.credit_need_text.setText(credit_change1.getCredit_Need());
		return view;
	}
	
	class ViewHolder{
		ImageView credit_need_image;
		
		TextView credit_need_text;
	}

}
