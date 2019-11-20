package com.sourcesense.emanuelepicariello.demo.mapper;

import com.sourcesense.emanuelepicariello.demo.dto.NewsDto;
import com.sourcesense.emanuelepicariello.demo.model.HackerNews;
import com.sourcesense.emanuelepicariello.demo.model.NyTimesArticle;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
@Mapper(componentModel = "spring")
public interface NewsMapperSoap {



        NewsMapperSoap INSTANCE = Mappers.getMapper(NewsMapperSoap.class);

        @Mappings({
                @Mapping(source = "title", target = "title"),
                @Mapping(source = "data", target = "time",
                        dateFormat = "dd-MM-yyyy HH:mm:ss")})
        NewsDto hackerNewsEntityToNews(HackerNews hackerNews);

        @Mappings({
                @Mapping(target = "title", source = "title"),
                @Mapping(target = "time", source = "data",
                        dateFormat = "dd-MM-yyyy HH:mm:ss")})
        NewsDto nyTimesArticleEntityToNews(NyTimesArticle nyTimes);


}
