package com.koreaIT.java.BAM.dto;

public class Article extends Dto {

	public String title; // 게시글 제목
	public String body; // 게시글 내용
	public int views; // 게시글의 조회수

	public Article(int id, String get_current_date_time, String title, String body) {
		this(id, get_current_date_time, title, body, 0);
		// 오버로딩 : 생성자에서만 쓸 수 있고 생성자의 첫번째줄에 와야함 , 인자값이 맞는 다른 생성자에게 일을 맡기는 명령문
	}

	public Article(int id, String get_current_date_time, String title, String body, int views) {
		// 외부에서 받아온 내용을 저장
		this.id = id;
		this.title = title;
		this.body = body;
		this.get_current_date_time = get_current_date_time;
		this.views = 0;

	}

	public void getViewsCnt() {
		this.views++;
	}

}
