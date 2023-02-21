package com.koreaIT.java.BAM.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import com.koreaIT.java.BAM.dto.Article;
import com.koreaIT.java.BAM.util.Util;

public class ArticleController {

	List<Article> articles;
	Scanner sc;
	int lastArticleId;

	public ArticleController(List<Article> articles2, Scanner sc2) {
		this.articles = articles;
		this.sc = sc;
		this.lastArticleId = 0;
	}

	public void doWrite() {
		int id = lastArticleId + 1; // 회원이 가지고 있는 고유번호
		lastArticleId = id;

		System.out.printf("제목 : ");
		String title = sc.nextLine(); // 제목의 변수 : title
		System.out.printf("내용 : ");
		String body = sc.nextLine(); // 내용의 변수 : body

		String get_current_date_time = Util.gettine();
		// Util.gettine() : Util파일에 있는 gettine() ☆현재 날짜시간출력 함수를 실행

		Article article = new Article(id, get_current_date_time, title, body);
		// 입력받은 정보(종이)를 Article(노트)에 넘겨준다

		articles.add(article);
		// 정보를 저장한 article(노트)를 articles(책장)에 넣어서(.add) 정보를 저장하겠다
		// 함수가 끝나면 정보가 사라지는 전역변수이므로 함수가 끝나기 전에 저장

		System.out.printf("%d번게시글이 생성되었습니다\n", id);

	}

	public void showList(String cmd) {
		if (articles.size() == 0) {
			System.out.println("게시글이 없습니다");
			return; // 리턴으로 함수를 종료시키되 넘겨주는 값은 없다.
		}

		String searchKeyword = cmd.substring("article list".length()).trim();

		List<Article> printArticles = new ArrayList<>(articles);

		if (searchKeyword.length() > 0) {
			System.out.println("검색어 : " + searchKeyword);

			printArticles.clear();

			for (Article article : articles) {
				if (article.title.contains(searchKeyword)) {
					printArticles.add(article);
				}
			}
			if (printArticles.size() == 0) {
				System.out.println("검색결과가 없습니다");
				return;
			}
		}

		System.out.println("번호	|	제목	|		날짜		|	조회");

		Collections.reverse(printArticles);
		for (Article article : printArticles) {
			System.out.printf("%d	|	%s	|	%s	|	%d\n", article.id, article.title, article.get_current_date_time,
					article.views);
		}
	}

	public void showDetail(String cmd) {
		// startsWith : 입력받은 명령어가 "article detail "로 시작하는지 검사해주는 함수

		String[] cmdBits = cmd.split(" ");// split :(" ")을 기준으로 문장을 나눈다.
		int id = Integer.parseInt(cmdBits[2]);

		Article foundArticle = getArticleId(id);

		if (foundArticle == null) {
			System.out.printf("%d번 게시물이 없습니다.\n", id);
			return;
		}

		foundArticle.getViewsCnt();

		System.out.printf("번호 : %d\n", foundArticle.id);
		System.out.printf("날짜 : %s\n", foundArticle.get_current_date_time.substring(0, 10)); // substring : 출력을
																								// 짤라주는 함수
		System.out.printf("제목 : %s\n", foundArticle.title);
		System.out.printf("내용 : %s\n", foundArticle.body);
		System.out.printf("조회수 : %d\n", foundArticle.views);

	}

	public void doDelete(String cmd) {
		String[] cmdBits = cmd.split(" ");
		int id = Integer.parseInt(cmdBits[2]);

		Article foundArticle = getArticleId(id);

		if (foundArticle == null) {
			System.out.printf("%d번 게시물이 없습니다.\n", id);
			return;
		}

		articles.remove(articles.indexOf(foundArticle));
		// articles에 입력받은 게시글(id)이 있는방(foundArticle)을 삭제(remove)한다.

		System.out.printf("%d번 게시물이 삭제되었습니다.\n", id);

	}

	public void doModify(String cmd) {
		String[] cmdBits = cmd.split(" ");
		int id = Integer.parseInt(cmdBits[2]);

		Article foundArticle = getArticleId(id);

		if (foundArticle == null) {
			System.out.printf("%d번 게시물이 없습니다.\n", id);
			return;
		}

		System.out.printf("수정할 제목 : ");
		String title = sc.nextLine();
		System.out.printf("수정할 내용 : ");
		String body = sc.nextLine();

		foundArticle.title = title;
		foundArticle.body = body;

		System.out.printf("%d번 게시물이 수정되었습니다.\n", id);

	}

	private Article getArticleId(int id) {

		for (Article article : articles) {
			if (id == article.id) {
				return article;
			}
		}
		return null;
	}

}
