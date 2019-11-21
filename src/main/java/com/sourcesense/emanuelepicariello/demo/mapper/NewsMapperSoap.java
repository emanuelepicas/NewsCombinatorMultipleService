package com.sourcesense.emanuelepicariello.demo.mapper;

import com.sourcesense.emanuelepicariello.demo.dto.NewsDto;
import com.sourcesense.emanuelepicariello.demo.model.HackerNews;
import com.sourcesense.emanuelepicariello.demo.model.NyTimesArticle;
import com.soursesense.emanuelepicariello.newscombinatorsoap.news.News;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
@Mapper(componentModel = "spring")
public interface NewsMapperSoap {



        NewsMapperSoap INSTANCE = Mappers.getMapper(NewsMapperSoap.class);

        @Mappings({
                @Mapping(source = "title", target = "title"),
                @Mapping(source = "time", target = "time",
                        dateFormat = "dd-MM-yyyy HH:mm:ss")})
        News hackerNewsorNyTimesArticleToNews(NewsDto hackerNews);



}
