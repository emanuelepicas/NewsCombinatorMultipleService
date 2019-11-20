package com.sourcesense.emanuelepicariello.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class NyTimesArticleContainer {

	private NyTimesArticle[] results;

	public NyTimesArticle[] getResults() {
		return results;
	}

	public void setResults(NyTimesArticle[] results) {
		this.results = results;
	}

}
