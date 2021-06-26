package com.emirates.flight.search.handler;

import com.emirates.flight.search.dto.SearchRequest;
import com.emirates.flight.search.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class SearchHandler {

	private SearchService searchService;

	@Autowired
	public SearchHandler(SearchService searchService) {
		this.searchService = searchService;
	}

	public Mono<ServerResponse> send(ServerRequest request) {
		return request.bodyToMono(SearchRequest.class)
				.flatMap(sf -> searchService.getFlightNumber(sf))
				.flatMap(resp -> ServerResponse.ok()
						.contentType(MediaType.APPLICATION_JSON)
						.body(BodyInserters.fromValue(resp)));
	}
}
