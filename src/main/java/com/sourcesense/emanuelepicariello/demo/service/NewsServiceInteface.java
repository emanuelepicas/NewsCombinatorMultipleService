package com.sourcesense.emanuelepicariello.demo.service;

import com.sourcesense.emanuelepicariello.demo.dto.NewsDto;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

public interface NewsServiceInteface {
	
	public List<NewsDto> allArticles() throws IOException, InterruptedException, ExecutionException;

}
