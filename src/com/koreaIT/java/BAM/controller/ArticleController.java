package com.koreaIT.java.BAM.controller;

import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import com.koreaIT.java.BAM.container.Container;
import com.koreaIT.java.BAM.dto.Article;
import com.koreaIT.java.BAM.dto.Member;
import com.koreaIT.java.BAM.util.Util;

public class ArticleController extends Controller {

	// private List<Article> articles; // 모든 게시글을 저장하고 있는 변수
	private Scanner sc;
	private String cmd;// 사용자가 원하는 명령을 저장하는 함수

	public ArticleController(Scanner sc) {
		// this.articles = Container.articleDao.articles;
		this.sc = sc;
	}

	@Override
	public void doAction(String cmd, String methodName) {
		this.cmd = cmd;

		switch (methodName) {
		case "write":
			doWrite();
			break;
		case "list":
			showList();
			break;
		case "detail":
			showDetail();
			break;
		case "modify":
			doModify();
			break;
		case "delete":
			doDelete();
			break;
		default:
			System.out.println("존재하지 않는 명령어 입니다");
			break;
		}
	}

	// 게시글 작성 함수
	private void doWrite() {

		int id = Container.articleDao.getLastId(); // 회원이 가지고 있는 고유번호

		System.out.printf("제목 : ");
		String title = sc.nextLine(); // 제목의 변수 : title
		System.out.printf("내용 : ");
		String body = sc.nextLine(); // 내용의 변수 : body

		String get_current_date_time = Util.gettine();
		// Util.gettine() : Util파일에 있는 gettine() ☆현재 날짜시간출력 함수를 실행

		Article article = new Article(id, get_current_date_time, loginedMember.id, title, body);
		// 입력받은 정보(종이)를 Article(노트)에 넘겨준다

		
		Container.articleService.add(article);
		//Container.articleDao.add(article);
		// articles.add(article);
		// 정보를 저장한 article(노트)를 articles(책장)에 넣어서(.add) 정보를 저장하겠다
		// 함수가 끝나면 정보가 사라지는 전역변수이므로 함수가 끝나기 전에 저장

		System.out.printf("%d번게시글이 생성되었습니다\n", id);
	}

	// 게시글 보기, 게시글 검색 기능 함수
	private void showList() {

		String searchKeyword = cmd.substring("article list".length()).trim();

		System.out.println("검색어 : " + searchKeyword);

		List<Article> printArticles = Container.articleService.getPrintArticles(searchKeyword);

		if (printArticles.size() == 0) {
			System.out.println("게시글이 없습니다");
			return;
		}

		System.out.println("번호	|	제목	|		날짜		|	작성자	|	조회");
		Collections.reverse(printArticles);
		for (Article article : printArticles) {

			String writerName = null;

			List<Member> members = Container.memberDao.members;

			for (Member member : members) {
				if (article.MembeId == member.id) {
					writerName = member.name;
					break;
				}
			}

			System.out.printf("%d	|	%s	|	%s	|	%s	|	%d\n", article.id, article.title, article.get_current_date_time,
					writerName, article.views);
		}
	}

	// 게시글 상세 보기 함수
	private void showDetail() {
		// startsWith : 입력받은 명령어가 "article detail "로 시작하는지 검사해주는 함수

		String[] cmdBits = cmd.split(" ");// split :(" ")을 기준으로 문장을 나눈다.

		if (cmdBits.length == 2) {
			System.out.println("명령어를 확인해주세요");
			return;
		}

		// Container.articleService.getPrintArticles(searchKeyword);

		int id = Integer.parseInt(cmdBits[2]);

		Article foundArticle = Container.articleService.getArticleById(id);

		if (foundArticle == null) {
			System.out.printf("%d번 게시물이 없습니다.\n", id);
			return;
		}

		String writerName = null;

		List<Member> members = Container.memberDao.members;

		for (Member member : members) {
			if (foundArticle.MembeId == member.id) {
				writerName = member.name;
				break;
			}
		}

		foundArticle.getViewsCnt();

		System.out.printf("번호 : %d\n", foundArticle.id);
		System.out.printf("날짜 : %s\n", foundArticle.get_current_date_time.substring(0, 10)); // substring : 출력을
																								// 짤라주는 함수
		System.out.printf("작성자 : %s\n", writerName);
		System.out.printf("제목 : %s\n", foundArticle.title);
		System.out.printf("내용 : %s\n", foundArticle.body);
		System.out.printf("조회수 : %d\n", foundArticle.views);

	}

	// 원하는 게시글 삭제 함수
	private void doDelete() {
		String[] cmdBits = cmd.split(" ");

		if (cmdBits.length == 2) {
			System.out.println("명령어를 확인해주세요");
			return;
		}

		int id = Integer.parseInt(cmdBits[2]);

		Article foundArticle = Container.articleService.getArticleById(id);

		if (isLoginMemberId(foundArticle)) {
			System.out.println("권한이 없습니다.");
			return;
		}

		Container.articleService.ArticleRemove(foundArticle);
		// articles.remove(foundArticle);
		// articles에 입력받은 게시글(id)이 있는방(foundArticle)을 삭제(remove)한다.

		System.out.printf("%d번 게시물이 삭제되었습니다.\n", id);

	}

	// 원하는 게시글 수정 함수
	public void doModify() {
		String[] cmdBits = cmd.split(" ");

		if (cmdBits.length == 2) {
			System.out.println("명령어를 확인해주세요");
			return;
		}

		int id = Integer.parseInt(cmdBits[2]);

		Article foundArticle = Container.articleService.getArticleById(id);

		if (foundArticle == null) {
			System.out.printf("%d번 게시물이 없습니다.\n", id);
			return;
		}
		if (isLoginMemberId(foundArticle)) {
			System.out.println("권한이 없습니다.");
			return;
		}

//		if (foundArticle.loginMembeId != loginedMember.id) {
//			System.out.println("권한이 없습니다.");
//			return;
//		}

		System.out.printf("수정할 제목 : ");
		String title = sc.nextLine();
		System.out.printf("수정할 내용 : ");
		String body = sc.nextLine();

		foundArticle.title = title;
		foundArticle.body = body;

		//Container.articleDao.add(foundArticle);

		System.out.printf("%d번 게시물이 수정되었습니다.\n", id);
	}

	// 게시글 작성자와 로그인을 한 작성자가 동일한지 검사하는 함수
	private boolean isLoginMemberId(Article foundArticle) {
		return foundArticle.MembeId != loginedMember.id;
	}

	// 아티클스 테스트 데이터
	public void makeTestData() {
		System.out.println("게시물 테스트 데이터를 생성합니다.");

		//Container.articleService.add(article);
		Container.articleService.add(new Article(Container.articleService.getLastId(), Util.gettine(), 1, " 제목1", "내용1", 10));
		Container.articleService.add(new Article(Container.articleService.getLastId(), Util.gettine(), 2, " 제목2", "내용2", 20));
		Container.articleService.add(new Article(Container.articleService.getLastId(), Util.gettine(), 3, " 제목3", "내용3", 30));
	}

}
