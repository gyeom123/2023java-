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

	public void add(Article article) {
		articles.add(article);
		this.lastId++;
	}

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

	public void remove(Article foundArticle) {
		articles.remove(foundArticle);
	}

	public void modify(Article foundArticle, String title, String body) {
		// TODO Auto-generated method stub

	}

	// 사용자가 원하는 게시글이 있는 객체를 알려주는 함수
	public Article CongetArticleId(int id) {
		for (Article article : articles) {
			if (id == article.id) {
				return article;
			}
		}
		return null;
	}

}
