package com.koreaIT.java.BAM;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		System.out.println("==프로그램 시작==");

		Scanner sc = new Scanner(System.in);

		int lastArticleId = 0;

		List<Article> articles = new ArrayList<>();
		// 입력받은 정보(책)를 배열로 저장하는 ArrayList(책장), DB(데이터베이스)의 역할
		
		String qwe = Util.getNowDateStr();
		
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

			if (cmd.equals("article write")) {

				int id = lastArticleId + 1; // 회원이 가지고 있는 고유번호
				lastArticleId = id;

				System.out.printf("제목 : ");
				String title = sc.nextLine(); // 제목의 변수 : title
				System.out.printf("내용 : ");
				String body = sc.nextLine(); // 내용의 변수 : body

				Article article = new Article(id, title, body);// 입력받은 정보(종이)를 Article(노트)에 넘겨준다
				articles.add(article);
				// 정보를 저장한 article(노트)를 articles(책장)에 넣어서(.add) 정보를 저장하겠다
				// 함수가 끝나면 정보가 사라지는 전역변수이므로 함수가 끝나기 전에 저장

				System.out.printf("%d번게시글이 생성되었습니다\n", id);

			} else if (cmd.equals("article list")) {

				if (articles.size() == 0) {
					System.out.println("게시글이 없습니다.");
					continue;
				}

				System.out.println("  번호 ㅣ   제목  ");
				for (int i = articles.size() - 1; i >= 0; i--) {
					Article article = articles.get(i);
					System.out.printf("   %d   ㅣ    %s  \n", article.id, article.title);

				}

			} else if (cmd.startsWith("article detail ")) {
				// startsWith : 입력받은 명령어가 "article detail "로 시작하는지 검사해주는 함수

				String[] cmdBits = cmd.split(" ");// split :(" ")을 기준으로 문장을 나눈다.
				int id = Integer.parseInt(cmdBits[2]);

				Article foundArticle = null;

				for (int i = 0; i < articles.size(); i++) {
					Article article = articles.get(i);

					if (id == article.id) {
						foundArticle = article;
						break;
					}
				}

				if (foundArticle == null) {
					System.out.printf("%d번 게시물이 없습니다.\n", id);
					continue;
				}

				System.out.printf("번호 : %d\n", foundArticle.id);
				System.out.printf("날짜 : %s\n", "123123123");
				System.out.printf("제목 : %s\n", foundArticle.title);
				System.out.printf("내용 : %s\n", foundArticle.body);

			} else if (cmd.startsWith("article delete ")) {

				String[] cmdBits = cmd.split(" ");
				int id = Integer.parseInt(cmdBits[2]);

				int foundArticle = -1;

				for (int i = 0; i < articles.size(); i++) {
					Article article = articles.get(i);

					if (id == article.id) {
						foundArticle = i;
						break;
					}
				}

				if (foundArticle == -1) {
					System.out.printf("%d번 게시물이 없습니다.\n", id);
					continue;
				}
				
				articles.remove(foundArticle); 
				//articles에 입력받은 게시글(id)이 있는방(foundArticle)을 삭제한다
				
				System.out.printf("%d번 게시물이 삭제되었습니다.", id);

			} else {
				System.out.println("존재하지 않는 명령어 입니다.");
			}

		}

		System.out.println("==프로그램  종류==");

		sc.close();
	}

}

class Article {
	int id; // 고유번호
	String title; // 게시글 제목
	String body; // 게시글 내용

	Article(int id, String title, String body) {
		// 외부에서 받아온 내용을 저장
		this.id = id;
		this.title = title;
		this.body = body;

	}

}
