package com.zdh.back.entities;

import java.util.Date;

public class AppUserInfo {


	private String id;
	private String nickname;
	private String phone;
	private String phoneImei;
	private String passwdHash;
	private String headSculpture;
	private Date createTime;
	private Date lastLoginTime;
	private String loginPosition;
	private String sex;
	private Integer age;
	private String payInfo;
	private String email;
	private String privacyProblem;
	private String secretAnswer;
	private String imUsername;
	private String imPasswd;
	private String imNickname;
	private String birthday;
	private String salt;
	private String pinyin;
	private String numberPasswd; //键盘密码

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPhoneImei() {
		return phoneImei;
	}

	public void setPhoneImei(String phoneImei) {
		this.phoneImei = phoneImei;
	}

	public String getPasswdHash() {
		return passwdHash;
	}

	public void setPasswdHash(String passwdHash) {
		this.passwdHash = passwdHash;
	}

	public String getHeadSculpture() {
		return headSculpture;
	}

	public void setHeadSculpture(String headSculpture) {
		this.headSculpture = headSculpture;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public String getLoginPosition() {
		return loginPosition;
	}

	public void setLoginPosition(String loginPosition) {
		this.loginPosition = loginPosition;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getPayInfo() {
		return payInfo;
	}

	public void setPayInfo(String payInfo) {
		this.payInfo = payInfo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPrivacyProblem() {
		return privacyProblem;
	}

	public void setPrivacyProblem(String privacyProblem) {
		this.privacyProblem = privacyProblem;
	}

	public String getSecretAnswer() {
		return secretAnswer;
	}

	public void setSecretAnswer(String secretAnswer) {
		this.secretAnswer = secretAnswer;
	}

	public String getImUsername() {
		return imUsername;
	}

	public void setImUsername(String imUsername) {
		this.imUsername = imUsername;
	}

	public String getImPasswd() {
		return imPasswd;
	}

	public void setImPasswd(String imPasswd) {
		this.imPasswd = imPasswd;
	}

	public String getImNickname() {
		return imNickname;
	}

	public void setImNickname(String imNickname) {
		this.imNickname = imNickname;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getPinyin() {
		return pinyin;
	}

	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}

	public String getNumberPasswd() {
		return numberPasswd;
	}

	public void setNumberPasswd(String numberPasswd) {
		this.numberPasswd = numberPasswd;
	}

	public AppUserInfo(String id, String nickname, String phone, String phoneImei, String passwdHash,
			String headSculpture, Date createTime, Date lastLoginTime, String loginPosition, String sex, Integer age,
			String payInfo, String email, String privacyProblem, String secretAnswer, String imUsername,
			String imPasswd, String imNickname, String birthday, String salt, String pinyin, String numberPasswd) {
		super();
		this.id = id;
		this.nickname = nickname;
		this.phone = phone;
		this.phoneImei = phoneImei;
		this.passwdHash = passwdHash;
		this.headSculpture = headSculpture;
		this.createTime = createTime;
		this.lastLoginTime = lastLoginTime;
		this.loginPosition = loginPosition;
		this.sex = sex;
		this.age = age;
		this.payInfo = payInfo;
		this.email = email;
		this.privacyProblem = privacyProblem;
		this.secretAnswer = secretAnswer;
		this.imUsername = imUsername;
		this.imPasswd = imPasswd;
		this.imNickname = imNickname;
		this.birthday = birthday;
		this.salt = salt;
		this.pinyin = pinyin;
		this.numberPasswd = numberPasswd;
	}

	public AppUserInfo() {
	}

	@Override
	public String toString() {
		return "AppUserInfo [id=" + id + ", nickname=" + nickname + ", phone=" + phone + ", phoneImei=" + phoneImei
				+ ", passwdHash=" + passwdHash + ", headSculpture=" + headSculpture + ", createTime=" + createTime
				+ ", lastLoginTime=" + lastLoginTime + ", loginPosition=" + loginPosition + ", sex=" + sex + ", age="
				+ age + ", payInfo=" + payInfo + ", email=" + email + ", privacyProblem=" + privacyProblem
				+ ", secretAnswer=" + secretAnswer + ", imUsername=" + imUsername + ", imPasswd=" + imPasswd
				+ ", imNickname=" + imNickname + ", birthday=" + birthday + ", salt=" + salt + ", pinyin=" + pinyin
				+ ", numberPasswd=" + numberPasswd + "]";
	}

}
