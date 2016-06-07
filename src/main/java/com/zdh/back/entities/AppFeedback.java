package com.zdh.back.entities;

import java.util.Date;

public class AppFeedback {

	private String id;
	private String advice;
	private String contact;// 用户联系方式
	private Date time;
	private String phone;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAdvice() {
		return advice;
	}

	public void setAdvice(String advice) {
		this.advice = advice;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public AppFeedback() {
		// TODO Auto-generated constructor stub
	}

	public AppFeedback(String id, String advice, String contact, Date time, String phone) {
		super();
		this.id = id;
		this.advice = advice;
		this.contact = contact;
		this.time = time;
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "AppFeedback [id=" + id + ", advice=" + advice + ", contact=" + contact + ", time=" + time + ", phone="
				+ phone + "]";
	}

}
