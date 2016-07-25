package com.example.activity;

import java.lang.ref.SoftReference;
import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.example.adpater.Guideadpater;
import com.example.idcard.R;
import com.example.util.DotMark;
import com.example.util.Skip;

/**
 * 引导页面 2016/07/04
 * dssfadsfdaffadsf
 * @author 付朝伟
 * 
 */
@SuppressLint("HandlerLeak")
public class GuideActivity extends Activity {
	/** 引导页面 */
	private ViewPager viewpager_guide;
	/** 点标签 */
	private LinearLayout linear_guide_drop;
	/** 配置文件加载器 */
	private LayoutInflater inflater;
	private SoftReference<Context> reference;
	private ArrayList<View> list;
	private DotMark dotMark;
	private SharedPreferences preferences;
	private SharedPreferences.Editor editor;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_guide);
		preferences = getSharedPreferences("yuandi", MODE_PRIVATE);
		editor = preferences.edit();
		boolean is = preferences.getBoolean("judger", true);
		if (is) {
			editor.putBoolean("judger", false);
			editor.putString("manager", "admin");
			editor.putString("password", "123456");
			editor.commit();
			reference = new SoftReference<Context>(this);
			inflater = LayoutInflater.from(reference.get());
			initUI();
			initdata();
			setListener();
		} else {
			Skip.NoParaSkip(this, SplashActivity.class);
			finish();
		}

	}

	private void initUI() {
		viewpager_guide = (ViewPager) findViewById(R.id.viewpager_guide);
		linear_guide_drop = (LinearLayout) findViewById(R.id.linear_guide_drop);
	}

	private void initdata() {
		new Thread(new myrunn()).start();

	}

	class myrunn implements Runnable {

		@Override
		public void run() {
			list = new ArrayList<View>();
			View view1 = inflater.inflate(R.layout.pager1, null);
			list.add(view1);
			View view2 = inflater.inflate(R.layout.pager2, null);
			list.add(view2);
			View view3 = inflater.inflate(R.layout.pager3, null);
			list.add(view3);
			handler.obtainMessage(0, list).sendToTarget();
		}
	}

	Handler handler = new Handler() {
		@SuppressLint("HandlerLeak")
		public void handleMessage(android.os.Message msg) {
			Guideadpater guideadpater = new Guideadpater(list);
			viewpager_guide.setAdapter(guideadpater);
			dotMark = new DotMark(reference.get(), list.size());
			linear_guide_drop.addView(dotMark);

		};
	};

	private void setListener() {
		viewpager_guide.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int arg0) {
				dotMark.currentPosition(arg0);
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {

			}

			@Override
			public void onPageScrollStateChanged(int arg0) {

			}
		});

	}

	public void onClick(View view) {
		Skip.NoParaSkip(reference.get(), SplashActivity.class);
		finish();

	}
}
