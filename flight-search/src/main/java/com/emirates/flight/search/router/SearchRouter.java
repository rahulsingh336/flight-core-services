package com.emirates.flight.search.router;

import com.emirates.flight.search.handler.SearchHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class SearchRouter {

	@Bean
	public RouterFunction<ServerResponse> route(SearchHandler searchHandler) {

		return RouterFunctions
				.route(RequestPredicates.POST("/flight").and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), searchHandler::send);
	}
}
