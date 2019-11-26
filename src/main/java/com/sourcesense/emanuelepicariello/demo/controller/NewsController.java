package com.sourcesense.emanuelepicariello.demo.controller;

import com.sourcesense.emanuelepicariello.demo.dto.NewsDto;
import com.sourcesense.emanuelepicariello.demo.model.NewsType;
import com.sourcesense.emanuelepicariello.demo.service.NewsService;
import com.sourcesense.emanuelepicariello.demo.service.HackerNewsService;
import com.sourcesense.emanuelepicariello.demo.service.NyTimesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
public class NewsController {

    @Autowired
    NewsService newsService;
    @Autowired
    HackerNewsService hackerNewsService;
    @Autowired
    NyTimesService nyTimesService;


    @GetMapping(value = "/allNews")
    @ResponseBody
    public List<NewsDto> allNewsOrdered() throws IOException, InterruptedException, ExecutionException {

        return newsService.allArticles();

    }


    @GetMapping(value = "/{source}")
    @ResponseBody
    public List<NewsDto> printSourceNews(@PathVariable String source) throws IOException, InterruptedException, ExecutionException {
        if (source.equals(NewsType.HACKERNEWS.toString())) {


            return hackerNewsService.allArticles();
        }

        if (source.equals(NewsType.NYTIMESNEWS.toString())) {

            return nyTimesService.allArticles();


        }
        return Collections.emptyList();


    }

}
