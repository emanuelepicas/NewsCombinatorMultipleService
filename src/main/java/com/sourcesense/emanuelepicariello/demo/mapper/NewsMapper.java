package com.sourcesense.emanuelepicariello.demo.mapper;


import com.sourcesense.emanuelepicariello.demo.dto.NewsDto;
import com.sourcesense.emanuelepicariello.demo.model.HackerNews;
import com.sourcesense.emanuelepicariello.demo.model.NyTimesArticle;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface NewsMapper {

    NewsMapper INSTANCE = Mappers.getMapper(NewsMapper.class);

    @Mappings({
            @Mapping(source = "title", target = "title"),
            @Mapping(source = "time", target = "time",
                    dateFormat = "dd-MM-yyyy HH:mm:ss"),
            @Mapping(source = "source", target = "source"),
            @Mapping(source = "url", target = "url"),
            @Mapping(source = "id", target = "id")})
    NewsDto hackerNewsEntityToNews(HackerNews hackerNews);


    @Mappings({
            @Mapping(source = "title", target = "title"),
            @Mapping(source = "data", target = "time",
                    dateFormat = "dd-MM-yyyy HH:mm:ss"),
            @Mapping(source = "source", target = "source"),
            @Mapping(source = "url", target = "url"),
            @Mapping(source = "data", target = "id"),})
    NewsDto nyTimesEntityToNews(NyTimesArticle nyTimesArticle);


}