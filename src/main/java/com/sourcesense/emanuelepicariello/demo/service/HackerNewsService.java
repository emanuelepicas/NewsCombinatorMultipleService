package com.sourcesense.emanuelepicariello.demo.service;


import com.sourcesense.emanuelepicariello.demo.dto.NewsDto;
import com.sourcesense.emanuelepicariello.demo.mapper.NewsMapper;
import com.sourcesense.emanuelepicariello.demo.mapper.NewsMapperSoap;
import com.sourcesense.emanuelepicariello.demo.model.HackerNews;
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
public class HackerNewsService implements NewsServiceInteface {
    private static final Logger logger = LoggerFactory.getLogger(HackerNewsService.class);

    @Value("${UrlofHakerNewsPart1}")
    private String hackerNewsUrlpart1;

    @Value("${UrlofHackerNewsPart2}")
    private String hackerNewsUrlpart2;

    @Value("${allIdUrlOfHackerNews}")
    private String hackerNews;



    @Override
    public List<NewsDto> allArticles() throws IOException, InterruptedException, ExecutionException {
        return  printNews();
    }

    @SuppressWarnings("unchecked")
    public List<Integer> readAllId() {

        RestTemplate restTemplate = new RestTemplate();

        return restTemplate.getForObject(hackerNews, List.class);
    }

    public List<NewsDto> printNews() throws InterruptedException, ExecutionException {
        List<HackerNews> allHackerNews;

        logger.info("creation of hacker News List");
        allHackerNews = allTheArticlesOfASource();


        List<NewsDto> allNews = allHackerNews.parallelStream().map(p ->
                (NewsMapper.INSTANCE.hackerNewsEntityToNews(p))).collect(Collectors.toList());
        Collections.sort(allNews);

        return allNews;
    }

    public List<HackerNews> allTheArticlesOfASource() throws InterruptedException, ExecutionException {
        List<Integer> list = readAllId();
        List<HackerNews> allHackerNewsList;

        RestTemplate restTemplate = new RestTemplate();


        allHackerNewsList = list.parallelStream().map(p ->
                (restTemplate.getForObject(hackerNewsUrlpart1 + p + hackerNewsUrlpart2,
                        HackerNews.class))).collect(Collectors.toList());

        return allHackerNewsList;
    }

    public List<News> mappingList() throws ExecutionException, InterruptedException {
        List<News> hackerNewsList;
        List<NewsDto> hackerNewsEntityList = printNews();
        hackerNewsList = hackerNewsEntityList.parallelStream().map(p ->
                (NewsMapperSoap.INSTANCE.hackerNewsorNyTimesArticleToNews(p))).collect(Collectors.toList());


        return new ArrayList<>(hackerNewsList);
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
