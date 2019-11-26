package com.sourcesense.emanuelepicariello.demo.service;


import com.sourcesense.emanuelepicariello.demo.dto.NewsDto;
import com.sourcesense.emanuelepicariello.demo.mapper.NewsMapper;
import com.sourcesense.emanuelepicariello.demo.mapper.NewsMapperSoap;
import com.sourcesense.emanuelepicariello.demo.model.HackerNews;
import com.soursesense.emanuelepicariello.newscombinatorsoap.news.GetNewsResponse;
import com.soursesense.emanuelepicariello.newscombinatorsoap.news.News;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Service
public class HackerNewsService implements NewsServiceInterface {
    private static final Logger logger = LoggerFactory.getLogger(HackerNewsService.class);

    @Value("${UrlofHakerNewsPart1}")
    private String hackerNewsUrlpart1;

    @Value("${UrlofHackerNewsPart2}")
    private String hackerNewsUrlpart2;

    @Value("${allIdUrlOfHackerNews}")
    private String hackerNews;

    RestTemplate restTemplate;


    @Autowired
    public HackerNewsService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;

    }

    @Override
    public List<NewsDto> allArticles() throws InterruptedException, ExecutionException {
        return printNews();
    }

    @SuppressWarnings("unchecked")
    public List<Integer> readAllId() {


        return restTemplate.getForObject(hackerNews, List.class);
    }

    public List<NewsDto> printNews() throws InterruptedException, ExecutionException {

        logger.info("creation of hacker News List");


        return allTheArticlesOfASource();
    }

    public List<NewsDto> allTheArticlesOfASource() {

        List<Integer> list = readAllId();

        return list.parallelStream()
                .map(this::getHackerNewsArticle)
                .sorted()
                .collect(Collectors.toList());
    }

    public NewsDto getHackerNewsArticle(Integer id){
        HackerNews hackerNewsArticle = restTemplate.getForObject(hackerNewsUrlpart1 + id + hackerNewsUrlpart2, HackerNews.class);
        return  NewsMapper.INSTANCE.hackerNewsEntityToNews(hackerNewsArticle);
    }

    public List<News> mappingList() throws ExecutionException, InterruptedException {

        return printNews()
                .parallelStream()
                .map(NewsMapperSoap.INSTANCE::hackerNewsorNyTimesArticleToNews)
                .collect(Collectors.toList());

    }


    public List<News> getHackerNews() throws ExecutionException, InterruptedException {
        List<News> mappingList = mappingList();
        if (mappingList != null)
            return mappingList;
        return new ArrayList<>();
    }

    //Soap Response
    public GetNewsResponse getHackerNewsResponse() throws ExecutionException, InterruptedException {
        GetNewsResponse response = new GetNewsResponse();
        response.getNews().addAll(getHackerNews());
        return response;

    }

}
