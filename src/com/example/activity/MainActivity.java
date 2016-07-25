package com.example.activity;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.adpater.MyMainAdpater;
import com.example.fragment.Frag1;
import com.example.fragment.Frag2;
import com.example.fragment.Frag3;
import com.example.idcard.R;

public class MainActivity extends FragmentActivity implements OnClickListener {

	private ViewPager viewpager_main;
	private List<Fragment> fragment;
	private Button enetring_main;
	private Button search_main;
	private Button setup_main;
	private TextView center_text;
	private ImageView left_image;

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.activity_main);
		initUI();
		initdata();
		setListener();
	}

	private void initUI() {
		viewpager_main = (ViewPager) findViewById(R.id.viewpager_main);
		enetring_main = (Button) findViewById(R.id.enetring_main);
		search_main = (Button) findViewById(R.id.search_main);
		setup_main = (Button) findViewById(R.id.setup_main);
		center_text = (TextView) findViewById(R.id.center_text);
		left_image = (ImageView) findViewById(R.id.left_image);
		fragment = new ArrayList<Fragment>();

	}

	private void initdata() {
		fragment.add(new Frag1());
		fragment.add(new Frag2());
		fragment.add(new Frag3());
		viewpager_main.setAdapter(new MyMainAdpater(fragment,
				getSupportFragmentManager()));
		center_text.setText(getString(R.string.main));
		left_image.setImageResource(R.drawable.finish);
		viewpager_main.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int arg0) {
				if (arg0 == 0) {
					enetring_main.setTextColor(getResources().getColor(
							R.color.red));
					setup_main.setTextColor(getResources().getColor(
							R.color.white));
					search_main.setTextColor(getResources().getColor(
							R.color.white));
				} else if (arg0 == 1) {
					enetring_main.setTextColor(getResources().getColor(
							R.color.white));
					setup_main.setTextColor(getResources().getColor(
							R.color.white));
					search_main.setTextColor(getResources().getColor(
							R.color.red));
				} else if (arg0 == 2) {
					enetring_main.setTextColor(getResources().getColor(
							R.color.white));
					setup_main.setTextColor(getResources()
							.getColor(R.color.red));
					search_main.setTextColor(getResources().getColor(
							R.color.white));
				}
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub

			}
		});
	}

	private void setListener() {
		left_image.setOnClickListener(this);
		enetring_main.setOnClickListener(this);
		setup_main.setOnClickListener(this);
		search_main.setOnClickListener(this);
	}

	@Override
	public void onClick(View view) {
		int key = view.getId();
		switch (key) {
		case R.id.enetring_main:
			viewpager_main.setCurrentItem(0);
			enetring_main.setTextColor(getResources().getColor(R.color.red));
			setup_main.setTextColor(getResources().getColor(R.color.white));
			search_main.setTextColor(getResources().getColor(R.color.white));
			break;
		case R.id.setup_main:
			viewpager_main.setCurrentItem(2);
			enetring_main.setTextColor(getResources().getColor(R.color.white));
			setup_main.setTextColor(getResources().getColor(R.color.red));
			search_main.setTextColor(getResources().getColor(R.color.white));
			break;
		case R.id.search_main:
			viewpager_main.setCurrentItem(1);
			enetring_main.setTextColor(getResources().getColor(R.color.white));
			setup_main.setTextColor(getResources().getColor(R.color.white));
			search_main.setTextColor(getResources().getColor(R.color.red));
			break;
		case R.id.left_image:
			finish();
			break;

		}

	}

}
