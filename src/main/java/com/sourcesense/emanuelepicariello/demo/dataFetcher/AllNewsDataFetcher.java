package com.sourcesense.emanuelepicariello.demo.dataFetcher;


import com.sourcesense.emanuelepicariello.demo.dto.NewsDto;
import com.sourcesense.emanuelepicariello.demo.service.NewsService;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Component
public class AllNewsDataFetcher implements DataFetcher<List<NewsDto>> {

 @Autowired
 NewsService newsService;

        @Override
    public List<NewsDto> get(DataFetchingEnvironment dataFetchingEnvironment) {

            try {
                return newsService.allArticles();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }

            return new ArrayList<>();
        }
}
