package com.example.adpater;

import java.util.List;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

public class Guideadpater extends PagerAdapter {

	List<View> list;

	public Guideadpater(List<View> list) {
		super();
		this.list = list;
	}

	/**
	 * 获取list的数量
	 */
	@Override
	public int getCount() {
		return list.size();
	}

	/**
	 * 删
	 */
	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		container.removeView(list.get(position));
	}

	/**
	 * 增
	 */
	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		container.addView(list.get(position));
		return list.get(position);
	}

	@Override
	public boolean isViewFromObject(View view, Object position) {
		return view == position;
	}

}
