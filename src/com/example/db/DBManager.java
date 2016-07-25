package com.example.db;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.util.CardInfo;

public class DBManager {

	@SuppressWarnings("unused")
	private Context context;
	public static DBManager dbManager;
	private DBHelper dbHelper;

	private DBManager(Context context) {
		this.context = context;
		dbHelper = new DBHelper(context);
	}

	public static DBManager getIntance(Context context) {
		if (dbManager == null) {
			dbManager = new DBManager(context);
		}
		return dbManager;
	}

	/**
	 * ������֤��Ϣ�����ݿ���
	 * 
	 * @param cardInfo
	 *            ���֤��Ϣ����
	 */
	public void insertCard(CardInfo cardInfo) {
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		Cursor cursor = db.rawQuery("select * from cardinfo where name = '"
				+ cardInfo.getName() + "'", null);
		if (cursor.moveToFirst()) {
			return;
		}
		cursor.close();
		ContentValues values = new ContentValues();
		values.put("icon", cardInfo.getIcon());
		values.put("name", cardInfo.getName());
		values.put("sex", cardInfo.getSex());
		values.put("nation", cardInfo.getNation());
		values.put("birth", cardInfo.getBirth());
		values.put("address", cardInfo.getAddress());
		values.put("cardid", cardInfo.getCardId());
		db.insert("cardinfo", null, values);
		db.close();

	}

	/**
	 * ͨ�����ֲ�ѯ���֤��Ϣ "SELECT * from cardinfo where  name LIKE '" + searchName+"'"
	 * 
	 * @param searchName
	 *            ����
	 * @return cardInfos ���֤��Ϣ���󼯺�
	 */
	public List<CardInfo> searchCardByName(String searchName) {
		List<CardInfo> cardInfos = new ArrayList<CardInfo>();
		SQLiteDatabase db = dbHelper.getWritableDatabase();

		Cursor cursor = db.rawQuery(
				"select * from cardinfo where name like '"+searchName+"' or cardid like '"+searchName+"'", null);
		if (cursor.moveToFirst()) {
			do {
				String icon = cursor.getString(cursor.getColumnIndex("icon"));
				String name = cursor.getString(cursor.getColumnIndex("name"));
				String sex = cursor.getString(cursor.getColumnIndex("sex"));
				String nation = cursor.getString(cursor
						.getColumnIndex("nation"));
				String birth = cursor.getString(cursor.getColumnIndex("birth"));
				String address = cursor.getString(cursor
						.getColumnIndex("address"));
				String cardId = cursor.getString(cursor
						.getColumnIndex("cardid"));
				CardInfo cardInfo = new CardInfo(icon, name, sex, nation,
						birth, address, cardId);
				cardInfos.add(cardInfo);
			} while (cursor.moveToNext());
		}
		cursor.close();
		db.close();
		return cardInfos;
	}

	/**
	 * ͨ�����ֲ�ѯ���֤��Ϣ
	 * 
	 * @param searchId
	 *            ��ݺ���
	 * @return cardInfos ���֤��Ϣ���󼯺�
	 */
	public List<CardInfo> searchCardById(String searchId) {
		List<CardInfo> cardInfos = new ArrayList<CardInfo>();
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		Cursor cursor = db.rawQuery(
				"SELECT * from cardinfo where cardid LIKE '" + searchId + "'",
				null);
		if (cursor.moveToFirst()) {
			do {
				String icon = cursor.getString(cursor.getColumnIndex("icon"));
				String name = cursor.getString(cursor.getColumnIndex("name"));
				String sex = cursor.getString(cursor.getColumnIndex("sex"));
				String nation = cursor.getString(cursor
						.getColumnIndex("nation"));
				String birth = cursor.getString(cursor.getColumnIndex("birth"));
				String address = cursor.getString(cursor
						.getColumnIndex("address"));
				String cardId = cursor.getString(cursor
						.getColumnIndex("cardid"));
				CardInfo cardInfo = new CardInfo(icon, name, sex, nation,
						birth, address, cardId);
				cardInfos.add(cardInfo);
			} while (cursor.moveToNext());
		}
		cursor.close();
		db.close();
		return cardInfos;
	}

	/**
	 * ��ѯ���������Ϣ
	 * 
	 * @return cardInfos ���֤��Ϣ���󼯺�
	 */
	public List<CardInfo> searchCardAll() {
		List<CardInfo> cardInfos = new ArrayList<CardInfo>();
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		Cursor cursor = db.rawQuery("select * from cardinfo", null);
		if (cursor.moveToFirst()) {
			do {
				String icon = cursor.getString(cursor.getColumnIndex("icon"));
				String name = cursor.getString(cursor.getColumnIndex("name"));
				String sex = cursor.getString(cursor.getColumnIndex("sex"));
				String nation = cursor.getString(cursor
						.getColumnIndex("nation"));
				String birth = cursor.getString(cursor.getColumnIndex("birth"));
				String address = cursor.getString(cursor
						.getColumnIndex("address"));
				String cardId = cursor.getString(cursor
						.getColumnIndex("cardid"));
				CardInfo cardInfo = new CardInfo(icon, name, sex, nation,
						birth, address, cardId);
				cardInfos.add(cardInfo);
			} while (cursor.moveToNext());
		}
		cursor.close();
		db.close();
		return cardInfos;
	}
}
