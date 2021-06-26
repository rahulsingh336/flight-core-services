package com.emirates.price.search.handler;

import com.emirates.price.search.dto.PriceSearchRequest;
import com.emirates.price.search.dto.PriceSearchResponse;
import com.emirates.price.search.service.PricingRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class PriceSearchHandler {

	private PricingRuleService pricingRuleService;

	@Autowired
	public PriceSearchHandler(PricingRuleService pricingRuleService) {
		this.pricingRuleService = pricingRuleService;
	}

	public Mono<ServerResponse> getPrice(ServerRequest request) {
		return request.bodyToMono(PriceSearchRequest.class)
				.flatMap(sf -> pricingRuleService.getFlightPrice(sf))
				.flatMap(resp ->
						ServerResponse.ok()
						.contentType(MediaType.APPLICATION_JSON)
						.body(BodyInserters.fromValue(new PriceSearchResponse(resp))))
						.switchIfEmpty(ServerResponse.notFound().build());
	}
}
