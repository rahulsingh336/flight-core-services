package com.emirates.flight.search.controller;

import com.emirates.flight.search.dto.SearchRequest;
import com.emirates.flight.search.dto.SearchResponse;
import com.emirates.flight.search.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/flight")
@RequiredArgsConstructor
public class SearchController {

	private SearchService searchService;

	@Autowired
	public SearchController(SearchService searchService) {
		this.searchService = searchService;
	}

	@PostMapping
	public Mono<SearchResponse> send(@Valid @RequestBody SearchRequest searchRequest) {
		return searchService.getFlightNumber(searchRequest);
	}
}
