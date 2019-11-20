package com.sourcesense.emanuelepicariello.demo.service;


import com.sourcesense.emanuelepicariello.demo.dto.NewsDto;
import com.sourcesense.emanuelepicariello.demo.mapper.NewsMapper;
import com.sourcesense.emanuelepicariello.demo.model.NyTimesArticle;
import com.sourcesense.emanuelepicariello.demo.model.NyTimesArticleContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NyTimesService implements NewsServiceInteface {
	private static final Logger logger = LoggerFactory.getLogger(HackerNewsService.class);

	@Value("${nyTimesNews}")
	String nyTimesNews;



	

	@Override
	public List<NewsDto> allArticles() throws IOException {
		logger.info("creation of NyTimes list");
		return printNews();

	}

	public List<NewsDto> printNews() {
		List<NyTimesArticle> allNyTimesArticles = new ArrayList<>();
		NyTimesArticle[] allArticles = allTheArticlesOFNyTimes().getResults();
		Collections.addAll(allNyTimesArticles, allArticles);

		List<NewsDto> allNews = allNyTimesArticles.parallelStream().map(p ->
				(NewsMapper.INSTANCE.nyTimesEntityToNews(p))).collect(Collectors.toList());

		Collections.sort(allNews);





		return  allNews;
	}

	public NyTimesArticleContainer allTheArticlesOFNyTimes() {
		NyTimesArticleContainer allNyTimesArticlesContainers;
		RestTemplate restTemplate = new RestTemplate();
		allNyTimesArticlesContainers = restTemplate.getForObject(nyTimesNews, NyTimesArticleContainer.class);

		return allNyTimesArticlesContainers;

	}

}
