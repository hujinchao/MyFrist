package com.example.adpater;

import java.util.List;

import com.example.idcard.R;
import com.example.util.CardInfo;
import com.example.util.MyImage;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MyListAdpater extends BaseAdapter {

	private List<CardInfo> list;
	private LayoutInflater inflater;
	private Context context;

	public MyListAdpater(List<CardInfo> list, Context context) {
		super();
		this.list = list;
		this.context = context;
		inflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	class Holder {
		TextView name_text_item;
		TextView sex_text_item;
		TextView number_text_item;
		ImageView head_image_item;

	}

	@Override
	public View getView(int position, View view, ViewGroup arg2) {
		Holder holder = null;
		if (view == null) {
			holder = new Holder();
			view = inflater.inflate(R.layout.list_item, null);
			holder.name_text_item = (TextView) view
					.findViewById(R.id.name_text_item);
			holder.sex_text_item = (TextView) view
					.findViewById(R.id.sex_text_item);
			holder.number_text_item = (TextView) view
					.findViewById(R.id.number_text_item);
			holder.head_image_item = (ImageView) view
					.findViewById(R.id.head_image_item);
			view.setTag(holder);
		} else {
			holder = (Holder) view.getTag();
		}
		holder.name_text_item.setText("姓名：" + list.get(position).getIcon());
		holder.sex_text_item.setText("性别：" + list.get(position).getSex());
		holder.number_text_item.setText("身份证号："
				+ list.get(position).getCardId());
		MyImage image = new MyImage(context);
		Bitmap bitmap = image.getBitmapFromCache(list.get(position).getName());
		if (bitmap == null) {
			holder.head_image_item
					.setImageResource(R.drawable.biz_pc_main_info_profile_avatar_bg_dark);
		} else {
			holder.head_image_item.setImageBitmap(image.getBitmapFromCache(list
					.get(position).getName()));

		}
		return view;
	}

}
