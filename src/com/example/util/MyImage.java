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
	 * ����ͼƬ���ֻ��ڴ���
	 */
	public void saveCachFile(String url, Bitmap bitmap) {
		/** ��ȡ�ļ����� */
		String name = url.substring(url.lastIndexOf("/") + 1);
		/** ���ص�·��Ŀ¼Ӧ�ó��򻺴��ļ� */
		File cacheDir = context.getCacheDir();
		if (!cacheDir.exists()) {
			cacheDir.mkdir();
		}
		/** ��������� */
		OutputStream outStream = null;
		try {
			outStream = new FileOutputStream(new File(cacheDir, name));
			/** ��ͼƬ���ļ� */
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
	 * ���ֻ��ڴ��ļ��л�ȡͼƬ
	 */
	public Bitmap getBitmapFromCache(String url) {
		String name = url.substring(url.lastIndexOf("/") + 1);
		/** ��ȡ��ǰ���µĻ����ļ�·�� */
		File cacheDir = context.getCacheDir();
		/** �õ����ļ����������ļ� */
		File[] files = cacheDir.listFiles();
		if (files == null) {
			return null;
		}
		/** ͼƬ�ļ� */
		File bitFile = null;
		/** �������ֺʹ�����ļ���һ�µ����ҵ�ͼƬ */
		for (File file : files) {
			if (file.getName().equals(name)) {
				bitFile = file;
				break;
			}
		}
		/** ���û���ҵ������ؿ� */
		if (bitFile == null) {
			return null;
		}
		/**
		 * ���ҵ��ļ� ת��Ϊbitmap
		 */
		Bitmap bitmap = BitmapFactory.decodeFile(bitFile.getAbsolutePath());
		return bitmap;
	}

}
