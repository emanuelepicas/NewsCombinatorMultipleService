package com.sourcesense.emanuelepicariello.demo.datafetcher;


import com.sourcesense.emanuelepicariello.demo.dto.NewsDto;
import com.sourcesense.emanuelepicariello.demo.service.NewsService;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Component
public class AllNewsDataFetcher implements DataFetcher<List<NewsDto>> {
    Logger logger = LoggerFactory.getLogger(AllNewsDataFetcher.class);

    @Autowired
    NewsService newsService;

    @Override
    public List<NewsDto> get(DataFetchingEnvironment dataFetchingEnvironment) {

        try {
            return newsService.allArticles();
        } catch (IOException | InterruptedException | ExecutionException e) {
            logger.info("Exception");
            Thread.currentThread().interrupt();
        }

        return new ArrayList<>();
    }
}
