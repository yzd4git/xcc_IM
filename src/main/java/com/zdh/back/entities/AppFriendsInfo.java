package com.zdh.back.entities;

import java.io.Serializable;

public class AppFriendsInfo implements Serializable{

	private static final long serialVersionUID = 1L;
	private String id;
	private String nickname;
	private String imUsername;
	private String imPasswd;

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

	public AppFriendsInfo() {
		// TODO Auto-generated constructor stub
	}

	public AppFriendsInfo(String id, String nickname, String imUsername,
			String imPasswd) {
		super();
		this.id = id;
		this.nickname = nickname;
		this.imUsername = imUsername;
		this.imPasswd = imPasswd;
	}

	@Override
	public String toString() {
		return "AppFriendsInfo [id=" + id + ", nickname=" + nickname
				+ ", imUsername=" + imUsername + ", imPasswd=" + imPasswd + "]";
	}

}
