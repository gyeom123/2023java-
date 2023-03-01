package com.koreaIT.java.BAM.dao;

import java.util.ArrayList;
import java.util.List;

import com.koreaIT.java.BAM.dto.Article;

public class ArticleDao extends Dao {

	public List<Article> articles;
	// public int lastId = 1;

	public ArticleDao() {
		this.articles = new ArrayList<>();
	}

	//게시글 추가
	public void add(Article article) {
		articles.add(article);
		this.lastId++;
	}

	//게시글 상세보기
	public List<Article> getPrintArticles(String searchKeyword) {

		if (searchKeyword != null) {

			List<Article> printArticles = new ArrayList<>();

			for (Article article : articles) {
				if (article.title.contains(searchKeyword)) {
					printArticles.add(article);
				}
			}
			return printArticles;
		}
		return articles;
	}
	
	//사용자가 원하는 게시글 삭제
	public void ArticleRemove(Article foundArticle) {
		articles.remove(foundArticle);
	}


	// 사용자가 원하는 게시글이 있는 객체를 알려주는 함수
	public Article getArticleById(int id) {
		for (Article article : articles) {
			if (id == article.id) {
				return article;
			}
		}
		return null;
	}

}
