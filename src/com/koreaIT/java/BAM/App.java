package com.koreaIT.java.BAM;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.koreaIT.java.BAM.controller.ArticleController;
import com.koreaIT.java.BAM.controller.MemberController;
import com.koreaIT.java.BAM.dto.Article;
import com.koreaIT.java.BAM.dto.Member;
import com.koreaIT.java.BAM.util.Util;

public class App {
	private List<Article> articles;
	private List<Member> members;

	App() {
		articles = new ArrayList<>();
		members = new ArrayList<>();
	}

	public void run() {
		System.out.println("==프로그램 시작==");

		Scanner sc = new Scanner(System.in);

		makeTestData();

		MemberController memberController = new MemberController(members, sc);
		ArticleController articleController = new ArticleController(articles, sc);

		while (true) {

			System.out.printf("명령어 ) "); // 줄바꿈을 하지않고 한줄에 입력을 받기 위해 printf를 쓴다
			String cmd = sc.nextLine().trim(); // trim() : 맨앞과 맨뒤에 있는 공백을 제거해주는 함수

			if (cmd.length() == 0) {
				System.out.println("명령어를 입력해주세요.");
				continue;
			}
			if (cmd.equals("exit")) {
				break; // 'cmd'변수안에 있는 문장이 "exit"경우 가장 가까운 반복문을 탈출한다.
			}

			if (cmd.equals("member join")) {
				memberController.dojoin();
			}

			else if (cmd.equals("article write")) {
				articleController.doWrite();
			} else if (cmd.startsWith("article list")) {
				articleController.showList(cmd);
			} else if (cmd.startsWith("article detail ")) {
				articleController.showDetail(cmd);
			} else if (cmd.startsWith("article delete ")) {
				articleController.doDelete(cmd);
			} else if (cmd.startsWith("article modify ")) {
				articleController.doModify(cmd);
			} else {
				System.out.println("존재하지 않는 명령어 입니다.");
			}
		}

		System.out.println("==프로그램  종류==");

		sc.close();
	}

	private void makeTestData() {
		System.out.println("게시물 테스트 데이터를 생성합니다.");

//		String get_current_date_time = Util.gettine();
		
//		Article article1 = new Article(1, get_current_date_time, " title1", "body1", 10);
//		Article article2 = new Article(2, get_current_date_time, " title2", "body2", 20);
//		Article article3 = new Article(3, get_current_date_time, " title3", "body3", 30);
		articles.add(new Article(1, Util.gettine(), " 제목1", "내용1", 10));
		articles.add(new Article(2, Util.gettine(), " 제목2", "내용2", 20));
		articles.add(new Article(3, Util.gettine(), " 제목3", "내용3", 30));
	}

}
