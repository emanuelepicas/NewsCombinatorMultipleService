package com.sourcesense.emanuelepicariello.demo.dataFetcher;


import com.sourcesense.emanuelepicariello.demo.dto.NewsDto;
import com.sourcesense.emanuelepicariello.demo.service.NyTimesService;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class NyTimesDataFetcher implements DataFetcher<List<NewsDto>> {
    @Autowired
    private NyTimesService nyTimesService;

    @Override
    public List<NewsDto> get(DataFetchingEnvironment dataFetchingEnvironment) {
        String source = dataFetchingEnvironment.getArgument("source");

        try {
            return nyTimesService.allArticles();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new ArrayList<>();
    }

}
