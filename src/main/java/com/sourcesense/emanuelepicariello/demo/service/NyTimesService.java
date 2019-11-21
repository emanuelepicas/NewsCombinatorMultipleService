package com.sourcesense.emanuelepicariello.demo.service;


import com.sourcesense.emanuelepicariello.demo.dto.NewsDto;
import com.sourcesense.emanuelepicariello.demo.mapper.NewsMapper;
import com.sourcesense.emanuelepicariello.demo.mapper.NewsMapperSoap;
import com.sourcesense.emanuelepicariello.demo.model.NyTimesArticle;
import com.sourcesense.emanuelepicariello.demo.model.NyTimesArticleContainer;
import com.soursesense.emanuelepicariello.newscombinatorsoap.news.GetNewsResponse;
import com.soursesense.emanuelepicariello.newscombinatorsoap.news.News;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;
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


        return allNews;
    }

    public NyTimesArticleContainer allTheArticlesOFNyTimes() {
        NyTimesArticleContainer allNyTimesArticlesContainers;
        RestTemplate restTemplate = new RestTemplate();
        allNyTimesArticlesContainers = restTemplate.getForObject(nyTimesNews, NyTimesArticleContainer.class);

        return allNyTimesArticlesContainers;

    }

    public List<News> mapping() throws ExecutionException, InterruptedException {
        List<News> news;
        List<NewsDto> newsEntities = printNews();
        news = newsEntities.parallelStream().map(p ->
                (NewsMapperSoap.INSTANCE.hackerNewsorNyTimesArticleToNews(p)))
                .collect(Collectors.toList());
        return news;
    }


    public List<News> getAllArticleOfNyTimes() throws IOException, ExecutionException, InterruptedException {
        logger.info("creation of NyTimes list");


        return mapping();

    }

    //Soap Response
    public GetNewsResponse getNyTimesResponse() throws ExecutionException, InterruptedException, IOException {
        GetNewsResponse response = new GetNewsResponse();
        response.getNews().addAll(getAllArticleOfNyTimes());
        return response;

    }

}
