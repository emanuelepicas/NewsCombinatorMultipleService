package com.sourcesense.emanuelepicariello.demo.service;

import com.sourcesense.emanuelepicariello.demo.datafetcher.AllNewsDataFetcher;
import com.sourcesense.emanuelepicariello.demo.datafetcher.HackerNewsDataFetcher;
import com.sourcesense.emanuelepicariello.demo.datafetcher.NyTimesDataFetcher;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;

@Service
public class GraphQLService {
    private static Logger logger = LoggerFactory.getLogger(GraphQLService.class);


    private AllNewsDataFetcher allNewsDataFetcher;

    private HackerNewsDataFetcher hackerNewsDataFetcher;

    private NyTimesDataFetcher nyTimesDataFetcher;
    @Value("classpath:newsDto.graphqls")
    Resource resource;

    private GraphQL graphQL;

    @Autowired
    public GraphQLService(AllNewsDataFetcher allNewsDataFetcher,
                          HackerNewsDataFetcher hackerNewsDataFetcher, NyTimesDataFetcher nyTimesDataFetcher) {
        this.allNewsDataFetcher = allNewsDataFetcher;
        this.hackerNewsDataFetcher = hackerNewsDataFetcher;
        this.nyTimesDataFetcher = nyTimesDataFetcher;
    }

    @PostConstruct
    private void loadSchema() throws IOException {
        logger.info("Entering loadSchema@GraphQLService");
        //Getting the graphql file
        File file = resource.getFile();
        //Parse SchemaF
        TypeDefinitionRegistry typeDefinitionRegistry = new SchemaParser().parse(file);
        RuntimeWiring runtimeWiring = buildRuntimeWiring();
        GraphQLSchema graphQLSchema = new SchemaGenerator().makeExecutableSchema(typeDefinitionRegistry, runtimeWiring);
        graphQL = GraphQL.newGraphQL(graphQLSchema).build();
    }


    private RuntimeWiring buildRuntimeWiring() {
        return RuntimeWiring.newRuntimeWiring()
                .type("Query", typeWiring -> typeWiring
                        .dataFetcher("getAllNews", allNewsDataFetcher)
                        .dataFetcher("getHackerNews", hackerNewsDataFetcher)
                        .dataFetcher("getNyTimesNews", nyTimesDataFetcher))

                .build();

    }

    public GraphQL getGraphQL() {
        return graphQL;
    }


}
