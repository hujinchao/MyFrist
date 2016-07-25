package com.example.util;

import android.content.Context;
import android.content.Intent;

public class Skip {

	public static void NoParaSkip(Context context,
			@SuppressWarnings("rawtypes") Class cla) {
		Intent intent = new Intent(context, cla);
		context.startActivity(intent);
	}

}