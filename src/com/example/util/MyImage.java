package com.example.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;

public class MyImage {

	private Context context;

	public MyImage(Context context) {
		super();
		this.context = context;
	}

	/**
	 * 保存图片到手机内存中
	 */
	public void saveCachFile(String url, Bitmap bitmap) {
		/** 获取文件名字 */
		String name = url.substring(url.lastIndexOf("/") + 1);
		/** 返回的路径目录应用程序缓存文件 */
		File cacheDir = context.getCacheDir();
		if (!cacheDir.exists()) {
			cacheDir.mkdir();
		}
		/** 建立输出流 */
		OutputStream outStream = null;
		try {
			outStream = new FileOutputStream(new File(cacheDir, name));
			/** 存图片到文件 */
			bitmap.compress(CompressFormat.JPEG, 100, outStream);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (outStream != null) {
					outStream.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 * 从手机内存文件中获取图片
	 */
	public Bitmap getBitmapFromCache(String url) {
		String name = url.substring(url.lastIndexOf("/") + 1);
		/** 获取当前包下的缓存文件路径 */
		File cacheDir = context.getCacheDir();
		/** 得到该文件夹下所有文件 */
		File[] files = cacheDir.listFiles();
		if (files == null) {
			return null;
		}
		/** 图片文件 */
		File bitFile = null;
		/** 如有名字和传入的文件名一致的则找到图片 */
		for (File file : files) {
			if (file.getName().equals(name)) {
				bitFile = file;
				break;
			}
		}
		/** 如果没有找到，返回空 */
		if (bitFile == null) {
			return null;
		}
		/**
		 * 把找的文件 转换为bitmap
		 */
		Bitmap bitmap = BitmapFactory.decodeFile(bitFile.getAbsolutePath());
		return bitmap;
	}

}
