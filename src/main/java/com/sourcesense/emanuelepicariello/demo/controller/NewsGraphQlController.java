package com.sourcesense.emanuelepicariello.demo.controller;


import com.sourcesense.emanuelepicariello.demo.service.AggregatorNewsService;
import com.sourcesense.emanuelepicariello.demo.service.GraphQLService;
import com.sourcesense.emanuelepicariello.demo.service.HackerNewsService;
import com.sourcesense.emanuelepicariello.demo.service.NyTimesService;
import graphql.ExecutionResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/news")
@Controller
public class NewsGraphQlController {

    @Autowired
    AggregatorNewsService newsService;

    @Autowired
    HackerNewsService hackNewsService;

    @Autowired
    NyTimesService nyTimesService;

    private static Logger logger = LoggerFactory.getLogger(NewsGraphQlController.class);

    private GraphQLService graphQLService;

    @Autowired
    public NewsGraphQlController(GraphQLService graphQLService) {
        this.graphQLService = graphQLService;
    }

    @PostMapping
    public ResponseEntity<Object> getAllNews(@RequestBody String query) {
        logger.info("Entering getAllNews@NewsController");
        ExecutionResult execute = graphQLService.getGraphQL().execute(query);
        return new ResponseEntity<>(execute, HttpStatus.OK);
    }


}
