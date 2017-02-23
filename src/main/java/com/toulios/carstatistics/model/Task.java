package com.toulios.carstatistics.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Helper class to holds an instance of Search class.
 * It is used for JSon de-serialization.
 */
public class Task implements Serializable {
	private static final long serialVersionUID = -4506046044343649228L;

	@JsonProperty("Search")
	private SearchValue search;

	public Task(){
		
	}

	public Task(SearchValue search) {
		this.search = search;
	}

	public SearchValue getSearch() {
		return search;
	}


	public void setSearch(SearchValue search) {
		this.search = search;
	}
}
