package com.emirates.price.search.router;

import com.emirates.price.search.handler.PriceSearchHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class PriceRouter {

	@Bean
	public RouterFunction<ServerResponse> route(PriceSearchHandler priceSearchHandler) {

		return RouterFunctions
				.route(RequestPredicates.POST("/price").and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), priceSearchHandler::getPrice);
	}
}
