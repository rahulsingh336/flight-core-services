package com.emirates.price.search.service;

import com.emirates.price.search.dto.PriceSearchRequest;
import com.emirates.price.search.dto.PriceSearchResponse;
import com.emirates.price.search.service.helper.DummyPrice;
import com.emirates.price.search.service.helper.FlightDateHolder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@Slf4j
public class PricingRuleService {

	private DummyPrice dummyPrice;

	@Autowired
	public PricingRuleService(DummyPrice dummyPrice) {
		this.dummyPrice = dummyPrice;
	}

	/**
	 * Mock Rule Engine
	 *
	 * @param priceSearchRequest the {@link PriceSearchRequest}
	 * @return
	 */
	//@NewSpan
	public Mono<Integer> getFlightPrice(PriceSearchRequest priceSearchRequest) {
		log.info("Entered send with paramters: {}", priceSearchRequest.toString());
		FlightDateHolder flightDateHolder = new FlightDateHolder(priceSearchRequest.getFlightNumber(), priceSearchRequest.getDate());
		return dummyPrice.getFlightPrice(flightDateHolder);

	}
}
