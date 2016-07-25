package com.example.adpater;

import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

public class MyMainAdpater extends PagerAdapter {

	private List<Fragment> fragments;
	private FragmentManager fm;

	public MyMainAdpater(List<Fragment> fragments, FragmentManager fm) {
		super();
		this.fragments = fragments;
		this.fm = fm;
	}

	@Override
	public int getCount() {
		return fragments.size();
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		return arg0 == arg1;
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		container.removeView(fragments.get(position).getView());

	}

	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		Fragment fragment = fragments.get(position);
		if (!fragment.isAdded()) {
			FragmentTransaction ft = fm.beginTransaction();
			ft.add(fragment, fragment.getClass().getSimpleName());
			ft.commit();
			fm.executePendingTransactions();
		}
		View view = fragment.getView();
		if (view.getParent() == null) {
			container.addView(view);
		}

		return view;
	}
}
