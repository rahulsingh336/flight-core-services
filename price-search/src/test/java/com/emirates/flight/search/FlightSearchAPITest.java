/*
package com.emirates.flight.search;

import com.emirates.flight.search.dto.PriceSearchRequest;
import com.emirates.flight.search.dto.PriceSearchResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.core.env.Environment;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.EntityExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureStubRunner(ids = {"com.emirates.micro:emirates-search-service:+:stubs"
								,"com.emirates.micro:aggregator-service:+:stubs"
								,"com.emirates.micro:discount-service:+:stubs"
								,"com.emirates.micro:notification-service:+:stubs"
								,"com.emirates.micro:sales-service:+:stubs"})
public class FlightSearchAPITest {

	@Autowired
	private Environment environment;

	@Autowired
	private WebTestClient client;

	@Test
	public void shouldSearchFlightNumber() throws JsonProcessingException, wiremock.com.fasterxml.jackson.core.JsonProcessingException {

		PriceSearchRequest priceSearchRequest = new PriceSearchRequest();

		EntityExchangeResult<PriceSearchResponse> response = client.post().uri("/flight").body(BodyInserters.fromValue(priceSearchRequest))
				.exchange()
				.expectStatus()
				.is2xxSuccessful()
				.expectBody(PriceSearchResponse.class)
		        .returnResult();

		Assertions.assertEquals("DUMMY_FLIGHT_NUMBER", response.getResponseBody().getFlightNumber());
	}


}
*/
