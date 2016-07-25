package com.example.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.ImageView;

import com.example.idcard.R;
import com.example.util.Skip;

/**
 * …¡∆¡“≥√Ê 2016/07/05
 * 
 * @author ∏∂≥ØŒ∞
 * 
 */
public class SplashActivity extends Activity implements AnimationListener {

	private ImageView image_splash;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		image_splash = (ImageView) findViewById(R.id.image_splash);
		Animation animation = new AlphaAnimation(0f, 1f);
		animation.setDuration(3000);
		animation.setFillAfter(true);
		animation.setAnimationListener(this);
		image_splash.setAnimation(animation);

	}

	@Override
	public void onAnimationEnd(Animation arg0) {
		Skip.NoParaSkip(SplashActivity.this, LoginPageActivity.class);
		finish();
	}

	@Override
	public void onAnimationRepeat(Animation arg0) {

	}

	@Override
	public void onAnimationStart(Animation arg0) {

	}
}
