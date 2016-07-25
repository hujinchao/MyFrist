package com.example.util;

public class CardInfo {
	// 名字
	private String name;
	// 头像
	private String icon;
	// 性别
	private String sex;
	// 民族
	private String nation;
	// 出生日期
	private String birth;
	// 地址
	private String address;
	// 身份证号
	private String cardId;

	public CardInfo(String name, String icon, String sex, String nation,
			String birth, String address, String cardId) {
		super();
		this.name = name;
		this.icon = icon;
		this.sex = sex;
		this.nation = nation;
		this.birth = birth;
		this.address = address;
		this.cardId = cardId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCardId() {
		return cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

}
