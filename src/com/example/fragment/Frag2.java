package com.example.fragment;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.adpater.MyListAdpater;
import com.example.db.DBManager;
import com.example.idcard.R;
import com.example.util.CardInfo;
import com.example.util.MyImage;

@SuppressLint("HandlerLeak") public class Frag2 extends Fragment {

	private ListView list_frag2;
	private MyListAdpater adpater;
	private List<CardInfo> list;
	private DBManager dbManager;
	public static final String RENOVATE = "FUZHAOWEI";
	private MyBroadcast myBroadcast;
	private EditText name_id_card_frag2;
	private Button inquiy_btn_frag2;
	private TextView textview;
	private Button btn_frag2_null;
	public static final String ID_NUMBER = "[\u4e00-\u9fa5]";

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.frag2, null);
	}

	private int width;

	@SuppressWarnings("deprecation")
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		WindowManager manager = getActivity().getWindowManager();
		width = manager.getDefaultDisplay().getWidth();
		initUI();
		initdata();
		setListener();

	}

	/**
	 * 判断是否合法
	 * 
	 * @param centent
	 *            用户输入
	 * @return 是否正确
	 */
	public static boolean Judge(String centent, String canonical) {
		boolean jufge = false;
		Pattern pattern = Pattern.compile(canonical);
		Matcher matcher = pattern.matcher(centent);
		jufge = matcher.matches();
		return jufge;

	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		getActivity().unregisterReceiver(myBroadcast);
	}

	private void initUI() {
		inflater = LayoutInflater.from(getActivity());
		btn_frag2_null = (Button) getView().findViewById(R.id.btn_frag2_null);
		textview = (TextView) getView().findViewById(R.id.textview);
		inquiy_btn_frag2 = (Button) getView().findViewById(
				R.id.inquiy_btn_frag2);
		name_id_card_frag2 = (EditText) getView().findViewById(
				R.id.name_id_card_frag2);
		list_frag2 = (ListView) getView().findViewById(R.id.list_frag2);
		dbManager = DBManager.getIntance(getActivity());
		IntentFilter filter = new IntentFilter(RENOVATE);
		myBroadcast = new MyBroadcast();
		getActivity().registerReceiver(myBroadcast, filter);
		list_frag2.setVisibility(View.GONE);
		textview.setVisibility(View.VISIBLE);

	}

	private void initdata() {
		new Thread(new myrunn()).start();
	}

	class myrunn implements Runnable {
		@Override
		public void run() {
			list = dbManager.searchCardAll();
			if (list != null) {
				handler.obtainMessage(0, list).sendToTarget();
			}
		}
	}

	@SuppressLint("HandlerLeak")
	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			if (msg.what == 0) {
				adpater = new MyListAdpater(list, getActivity());
				list_frag2.setAdapter(adpater);
				adpater.notifyDataSetChanged();
				list_frag2.setVisibility(View.VISIBLE);
				textview.setVisibility(View.GONE);
			}

		};
	};

	class MyBroadcast extends BroadcastReceiver {
		@Override
		public void onReceive(Context arg0, Intent arg1) {
			new Thread(new myrunn()).start();
			adpater.notifyDataSetChanged();
		}
	}

	private String result;
	private LayoutInflater inflater;

	private void setListener() {
		btn_frag2_null.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				list = dbManager.searchCardAll();
				if (list != null) {
					handler.obtainMessage(0, list).sendToTarget();
				}
			}
		});
		inquiy_btn_frag2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				result = name_id_card_frag2.getText().toString();
				new Thread(new myrunn2()).start();
			}
		});

		list_frag2.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				postion = arg2;
				new Thread(new poprunn()).start();

			}
		});
	}

	private int postion;
	private View contxtview;
	private TextView card_address;
	private TextView card_date;
	private TextView card_name;
	private TextView card_nation;
	private TextView card_number;
	private TextView card_sex;
	private ImageView card_touxiang;
	private PopupWindow pop;

	class poprunn implements Runnable {

		@SuppressWarnings("deprecation")
		@Override
		public void run() {
			pop = new PopupWindow(getActivity());
			contxtview = inflater.inflate(R.layout.popu_item, null);
			// 设定弹窗主视图
			pop.setContentView(contxtview);
			// 设定弹窗宽度
			pop.setWidth(width - 40);
			// 设定弹窗高度
			pop.setHeight(LayoutParams.WRAP_CONTENT);
			// 设定弹窗外部是否可以点击
			pop.setOutsideTouchable(true);
			// 设定弹窗背景，设置null可以去掉系统默认弹窗背景,
			// 设定为new
			// BitmapDrawable()是为了弹窗外部点击让弹窗消失和实体back按钮点击，弹窗消失，注意要添加pop.setFocusable(true);
			pop.setBackgroundDrawable(new BitmapDrawable());
			// 设定弹窗默认获取焦点
			pop.setFocusable(true);
			// 设定动画
			pop.setAnimationStyle(R.style.PopAnimation);

			card_address = (TextView) contxtview
					.findViewById(R.id.card_address);
			card_date = (TextView) contxtview.findViewById(R.id.card_date);
			card_name = (TextView) contxtview.findViewById(R.id.card_name);
			card_nation = (TextView) contxtview.findViewById(R.id.card_nation);
			card_number = (TextView) contxtview.findViewById(R.id.card_number);
			card_sex = (TextView) contxtview.findViewById(R.id.card_sex);
			card_touxiang = (ImageView) contxtview
					.findViewById(R.id.card_touxiang);
			handler2.obtainMessage(0).sendToTarget();

		}

	}

	Handler handler2 = new Handler() {
		@SuppressLint("HandlerLeak")
		public void handleMessage(android.os.Message msg) {
			if (msg.what == 0) {
				card_name.setText("姓 名 " + list.get(postion).getIcon());
				MyImage myImage = new MyImage(getActivity());
				card_touxiang.setImageBitmap(myImage.getBitmapFromCache(list
						.get(postion).getName()));
				card_address.setText("住 址 " + list.get(postion).getAddress());
				card_date.setText("出 生 " + list.get(postion).getBirth());
				card_nation.setText("民 族" + list.get(postion).getNation());
				card_number.setText("身份证号码 " + list.get(postion).getCardId());
				card_sex.setText("性 别 " + list.get(postion).getSex());
				pop.showAsDropDown(name_id_card_frag2);
			}

		};
	};

	class myrunn2 implements Runnable {
		@SuppressLint("NewApi")
		@Override
		public void run() {
			list = dbManager.searchCardByName(result);
			if (list != null) {
				handler.obtainMessage(0, list).sendToTarget();
			}

		}
	}
}
