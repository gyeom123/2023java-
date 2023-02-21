package com.koreaIT.java.BAM.dto;

public class Member extends Dto {

	public String LoginID; // 로그인 아이디
	public String LoginPassword;// 로그인 비밀번호
	public String name;// 로그인 비밀번호

	public Member(int id, String get_current_date_time, String LoginID, String LoginPassword, String name) {
		this.id = id;
		this.get_current_date_time = get_current_date_time;
		this.LoginID = LoginID;
		this.LoginPassword = LoginPassword;
		this.name = name;

	}

}
