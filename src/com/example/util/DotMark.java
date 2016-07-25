package com.example.util;

import android.content.Context;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.idcard.R;

public class DotMark extends LinearLayout {

	private Context context;
	private int count;

	public DotMark(Context context, int count) {
		super(context);
		this.context = context;
		this.count = count;
		inidDot();
	}

	/**
	 * 初始化点
	 */
	@SuppressWarnings("deprecation")
	private void inidDot() {
		LayoutParams lp = new LayoutParams(50, 50, 1f);
		lp.setMargins(2, 2, 2, 2);
		for (int i = 0; i < count; i++) {
			ImageView imageView = new ImageView(context);
			imageView.setLayoutParams(lp);
			imageView.setImageResource(R.drawable.a4);
			if (i == 0) {// 不透明
				imageView.setAlpha(255);
			} else {
				imageView.setAlpha(100);
			}
			addView(imageView);
		}
	}

	/**
	 * 滑到当前位置
	 * 
	 * @param position
	 */
	@SuppressWarnings("deprecation")
	public void currentPosition(int position) {
		for (int i = 0; i < count; i++) {
			ImageView imageView = (ImageView) getChildAt(i);
			if (i == position) {
				imageView.setAlpha(255);
			} else {
				imageView.setAlpha(100);
			}
		}
	}
}
