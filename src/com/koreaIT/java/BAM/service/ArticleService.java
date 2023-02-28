package com.koreaIT.java.BAM.service;

import java.util.List;

import com.koreaIT.java.BAM.container.Container;
import com.koreaIT.java.BAM.dto.Article;

public class ArticleService {

	public List<Article> getPrintArticles(String searchKeyword) {
		return Container.articleDao.getPrintArticles(searchKeyword);
	}

	public void remove(Article foundArticle) {
		Container.articleDao.remove(foundArticle);
		
	}

	public void modify(String title, String body) {
		Container.articleDao.modify(title,body);
		
	}

	public Article CongetArticleId(int id) {
		return Container.articleDao.CongetArticleId(id);
	}

}
