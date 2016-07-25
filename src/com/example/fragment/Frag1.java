package com.example.fragment;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Toast;

import com.example.db.DBManager;
import com.example.idcard.R;
import com.example.util.CardInfo;
import com.example.util.MyImage;

public class Frag1 extends Fragment implements OnClickListener {

	private Button data_frag1;
	private Calendar cal;
	private SimpleDateFormat df;
	private Button keep_frag1;
	private EditText name_frag1;
	private RadioGroup group_frag1;
	private EditText nation_frag1;
	private EditText number_frag1;
	private EditText address_frag1;
	private RadioButton radbtn_frag1_man;
	private ImageView head_main;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.frag1, null);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		cal = Calendar.getInstance();
		initUI();
		initdata();
		setListener();
	}

	private void initUI() {
		head_main = (ImageView) getView().findViewById(R.id.head_main);
		data_frag1 = (Button) getView().findViewById(R.id.data_frag1);
		keep_frag1 = (Button) getView().findViewById(R.id.keep_frag1);
		name_frag1 = (EditText) getView().findViewById(R.id.name_frag1);
		group_frag1 = (RadioGroup) getView().findViewById(R.id.group_frag1);
		nation_frag1 = (EditText) getView().findViewById(R.id.nation_frag1);
		number_frag1 = (EditText) getView().findViewById(R.id.id_number_frag1);
		address_frag1 = (EditText) getView().findViewById(R.id.address_frag1);
		radbtn_frag1_man = (RadioButton) getView().findViewById(
				R.id.radbtn_frag1_man);

	}

	private void initdata() {

	}

	private String result = "男性";

	private void setListener() {
		head_main.setOnClickListener(this);
		data_frag1.setOnClickListener(this);
		keep_frag1.setOnClickListener(this);
		group_frag1.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup arg0, int checked_id) {
				checked_id = group_frag1.getCheckedRadioButtonId();
				RadioButton radioButton = (RadioButton) group_frag1
						.findViewById(checked_id);
				result = radioButton.getText().toString();
			}
		});

	}

	private DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() { //
		@Override
		public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {
			cal.set(Calendar.YEAR, arg1);
			cal.set(Calendar.MONTH, arg2);
			cal.set(Calendar.DAY_OF_MONTH, arg3);
			updateDate();
		}
	};

	@SuppressLint("SimpleDateFormat")
	private void updateDate() {
		df = new SimpleDateFormat("yyyy-MM-dd");
		data_frag1.setText(df.format(cal.getTime()));
	}

	@Override
	public void onClick(View view) {
		int key = view.getId();
		switch (key) {
		case R.id.data_frag1:
			new DatePickerDialog(getActivity(), listener,
					cal.get(Calendar.YEAR), cal.get(Calendar.MONTH),
					cal.get(Calendar.DAY_OF_MONTH)).show();
			break;
		case R.id.keep_frag1:
			Judge();
			break;
		case R.id.head_main:
			Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
			startActivityForResult(intent, 1);
			break;
		}
	}

	private String imagename;

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
			MyImage image = new MyImage(getActivity());
			image.saveCachFile(name, bitmap);
			head_main.setImageBitmap(bitmap);
			head_main.invalidate();
			imagename = name;
		}
	}

	@SuppressLint("NewApi")
	private void Judge() {
		String name = name_frag1.getText().toString();
		String nation = nation_frag1.getText().toString();
		String data = data_frag1.getText().toString();
		String number = number_frag1.getText().toString();
		String address = address_frag1.getText().toString();

		if (false == Frag2.Judge(name, Frag2.ID_NUMBER)) {
			if (!nation.isEmpty() && nation != null) {
				if (!result.isEmpty() && result != null) {
					if (!data.isEmpty() && data != null && !data.equals("出生日期")) {
						if (!number.isEmpty() && number != null) {
							if (!address.isEmpty() && address != null) {
								if (head_main.getDrawable() != null) {
									CardInfo cardInfo = new CardInfo(name,
											imagename, result, nation, data,
											address, number);
									DBManager dbManager = DBManager
											.getIntance(getActivity());
									dbManager.insertCard(cardInfo);

									Empty();
									MyToast("保存成功！");
									Intent intent = new Intent(Frag2.RENOVATE);
									getActivity().sendBroadcast(intent);

								} else {
									MyToast("请选择照片！");
								}
							} else {
								MyToast("请输入地址！");
							}
						} else {
							MyToast("身份证号不能为空！");
						}
					} else {
						MyToast("请选择出生日期！");
					}

				} else {
					MyToast("性别不能为空！");
				}
			} else {
				MyToast("民族不能为空！");
			}

		} else {
			MyToast("请输入正确的名字！");
		}

	}

	private void Empty() {
		name_frag1.setText("");
		nation_frag1.setText("");
		number_frag1.setText("");
		data_frag1.setText(getString(R.string.date_of_birth));
		address_frag1.setText("");
		head_main.setImageBitmap(null);
		radbtn_frag1_man.setChecked(true);
	}

	public void MyToast(String result) {
		Toast.makeText(getActivity(), result, Toast.LENGTH_SHORT).show();
	}

}
