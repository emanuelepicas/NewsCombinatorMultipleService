package com.sourcesense.emanuelepicariello.demo.dataFetcher;

import com.sourcesense.emanuelepicariello.demo.dto.NewsDto;

import com.sourcesense.emanuelepicariello.demo.service.HackerNewsService;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Component
public class HackerNewsDataFetcher implements DataFetcher<List<NewsDto>> {

    @Autowired
    private HackerNewsService hackerNewsService;

    @Override
    public List<NewsDto> get(DataFetchingEnvironment dataFetchingEnvironment) {
        try {
            return hackerNewsService.allArticles();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}
