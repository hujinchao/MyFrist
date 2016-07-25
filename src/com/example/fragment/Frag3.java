package com.example.fragment;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.idcard.R;
import com.example.util.MyImage;

public class Frag3 extends Fragment implements OnClickListener {

	private EditText name_frag3;
	private EditText password_frag3;
	private SharedPreferences preferences;
	private SharedPreferences.Editor editor;
	private String name;
	private String password;
	private Button dete_frag3;
	private ImageView head_frag3;

	@SuppressWarnings("static-access")
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		preferences = getActivity().getSharedPreferences("yuandi",
				getActivity().MODE_PRIVATE);

		editor = preferences.edit();
		name = preferences.getString("manager", "");
		password = preferences.getString("password", "");
		return inflater.inflate(R.layout.frag3, null);
	}

	@SuppressLint("NewApi")
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		name_frag3 = (EditText) getView().findViewById(R.id.name_frag3);
		password_frag3 = (EditText) getView().findViewById(R.id.password_frag3);
		dete_frag3 = (Button) getView().findViewById(R.id.dete_frag3);
		head_frag3 = (ImageView) getView().findViewById(R.id.head_frag3);
		head_frag3.setOnClickListener(this);
		dete_frag3.setOnClickListener(this);
		name_frag3.setText(name);
		password_frag3.setText(password);
		head_frag3
				.setImageResource(R.drawable.biz_pc_main_info_profile_avatar_bg_dark);
		String manager = preferences.getString("BitMap", "");
		if (manager != null && !manager.isEmpty()) {
			MyImage myImage = new MyImage(getActivity());
			Bitmap bitmap = myImage.getBitmapFromCache(manager);
			head_frag3.setImageBitmap(bitmap);
		}

	}

	@Override
	public void onClick(View arg0) {
		int key = arg0.getId();
		switch (key) {
		case R.id.head_frag3:
			Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
			startActivityForResult(intent, 1);
			break;
		case R.id.dete_frag3:
			editor.putString("manager", name_frag3.getText().toString());
			editor.putString("password", password_frag3.getText().toString());
			if (ImageName != null) {
				editor.putString("BitMap", ImageName);
			}
			editor.commit();
			Toast.makeText(getActivity(), "保存并修改成功！", Toast.LENGTH_SHORT)
					.show();

			break;
		}

	}

	private String ImageName;

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == Activity.RESULT_OK) {
			String sdStatus = Environment.getExternalStorageState();
			if (!sdStatus.equals(Environment.MEDIA_MOUNTED)) {// 检测SDK是否可用
				return;
			}
			SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd_hhmmss");
			String name = format.format(new Date()) + ".jpg";
			// System.out.println("**************" + name);
			Bitmap bitmap = (Bitmap) data.getExtras().get("data");
			MyImage myImage = new MyImage(getActivity());
			myImage.saveCachFile(name, bitmap);
			head_frag3.setImageBitmap(bitmap);
			head_frag3.invalidate();
			ImageName = name;
		}
	}

}
