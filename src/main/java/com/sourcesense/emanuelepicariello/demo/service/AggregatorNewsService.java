package com.sourcesense.emanuelepicariello.demo.service;

import com.sourcesense.emanuelepicariello.demo.dto.NewsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class AggregatorNewsService implements NewsServiceInteface {

    @Autowired
    NyTimesService nyTimesService;

    @Autowired
    HackerNewsService hackerNewsService;




    @Override
    public List<NewsDto> allArticles() throws IOException, InterruptedException, ExecutionException {

        return printNews();
    }

    public List<NewsInterface> printNews() throws InterruptedException, ExecutionException {
        List<NewsInterface> allHackerNews = hackerNewsService.printNews();
        List<NewsInterface> allNews = nyTimesService.printNews();
        CompareDateNews cmp = new CompareDateNews();
        allHackerNews.addAll(allNews);
        Collections.sort(allHackerNews, cmp);
        return allHackerNews;

    }


}