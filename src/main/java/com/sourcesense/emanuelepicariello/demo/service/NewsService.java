package com.sourcesense.emanuelepicariello.demo.service;

import com.sourcesense.emanuelepicariello.demo.dto.NewsDto;
import com.sourcesense.emanuelepicariello.demo.mapper.NewsMapperSoap;
import com.soursesense.emanuelepicariello.newscombinatorsoap.news.GetNewsResponse;
import com.soursesense.emanuelepicariello.newscombinatorsoap.news.News;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Service
public class NewsService implements NewsServiceInteface {

    @Autowired
    NyTimesService nyTimesService;

    @Autowired
    HackerNewsService hackerNewsService;


    @Override
    public List<NewsDto> allArticles() throws IOException, InterruptedException, ExecutionException {

        return printNews();
    }

    public List<NewsDto> printNews() throws InterruptedException, ExecutionException {
        List<NewsDto> allHackerNews = hackerNewsService.printNews();
        List<NewsDto> allNews = nyTimesService.printNews();
        allHackerNews.addAll(allNews);
        Collections.sort(allHackerNews);
        return allHackerNews;

    }

    public List<News> mapAllTheArticle() throws ExecutionException, InterruptedException {
        List<News> newsList = new ArrayList<>();
        List<News> aList;
        aList = printNews().parallelStream().map(p -> (
                NewsMapperSoap.INSTANCE.hackerNewsorNyTimesArticleToNews(p))).collect(Collectors.toList());
        return aList;

    }

    //Soap Response
    public GetNewsResponse getAllNewsResponse() throws ExecutionException, InterruptedException, IOException {
        GetNewsResponse response = new GetNewsResponse();
        response.getNews().addAll(mapAllTheArticle());
        return response;

    }

}