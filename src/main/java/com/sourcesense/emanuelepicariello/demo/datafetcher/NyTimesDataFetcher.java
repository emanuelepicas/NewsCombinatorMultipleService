package com.sourcesense.emanuelepicariello.demo.datafetcher;


import com.sourcesense.emanuelepicariello.demo.dto.NewsDto;
import com.sourcesense.emanuelepicariello.demo.service.NyTimesService;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class NyTimesDataFetcher implements DataFetcher<List<NewsDto>> {
    @Autowired
    private NyTimesService nyTimesService;
    Logger logger = LoggerFactory.getLogger(NyTimesDataFetcher.class);

    @Override
    public List<NewsDto> get(DataFetchingEnvironment dataFetchingEnvironment) {
        try {
            return nyTimesService.allArticles();
        } catch (IOException e) {
            logger.info("IoExceptions");
        }

        return new ArrayList<>();
    }

}
