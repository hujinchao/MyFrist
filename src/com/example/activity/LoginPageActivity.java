package com.example.activity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.db.DBHelper;
import com.example.idcard.R;
import com.example.util.Skip;

public class LoginPageActivity extends Activity implements OnClickListener {

	private TextView center_text;
	private ImageView left_image;
	private ImageView right_image;
	private EditText manager_loagin;
	private EditText password_login;
	private String manager;
	private String password;
	private SharedPreferences preferences;
	private Button entry_login;;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login_page);
		preferences = getSharedPreferences("yuandi", MODE_PRIVATE);
		manager = preferences.getString("manager", "");
		password = preferences.getString("password", "");
		initUI();
		initdate();
		setListener();
	}

	private void initUI() {
		right_image = (ImageView) findViewById(R.id.right_image);
		left_image = (ImageView) findViewById(R.id.left_image);
		center_text = (TextView) findViewById(R.id.center_text);
		manager_loagin = (EditText) findViewById(R.id.manager_loagin);
		password_login = (EditText) findViewById(R.id.password_login);
		entry_login = (Button) findViewById(R.id.entry_login);
	}

	private void initdate() {
		center_text.setText(getString(R.string.entry));
		left_image.setImageResource(R.drawable.house);
		right_image.setImageResource(R.drawable.setup);
	}

	private void setListener() {
		entry_login.setOnClickListener(this);
	}

	/** 检查密码是否正确 */
	private void entry() {
		if (manager.equals(manager_loagin.getText().toString())) {
			if (password.equals(password_login.getText().toString())) {
				Skip.NoParaSkip(LoginPageActivity.this, MainActivity.class);
				DBHelper dbHelper = new DBHelper(LoginPageActivity.this);
				SQLiteDatabase helper = dbHelper.getReadableDatabase();
				helper.close();
				finish();
			} else {
				MyToast("密码错误，请重新输入!");
				password_login.setText("");
				password_login.invalidate();
			}
		} else {
			MyToast("账号错误，请重新输入!");
			manager_loagin.setText("");
			manager_loagin.invalidate();
		}

	}

	private void MyToast(String result) {
		Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onClick(View view) {
		int key = view.getId();
		switch (key) {
		case R.id.entry_login:
			entry();
			break;

		default:
			break;
		}
	}

}
