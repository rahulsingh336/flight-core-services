package com.emirates.flight.search;

import com.emirates.flight.search.dto.SearchRequest;
import com.emirates.flight.search.dto.SearchResponse;
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
@AutoConfigureStubRunner(ids = {"com.devskiller.micro:emirates-search-service:+:stubs"
								,"com.devskiller.micro:aggregator-service:+:stubs"
								,"com.devskiller.micro:discount-service:+:stubs"
								,"com.devskiller.micro:notification-service:+:stubs"
								,"com.devskiller.micro:sales-service:+:stubs"})
public class FlightSearchAPITest {

	@Autowired
	private Environment environment;

	@Autowired
	private WebTestClient client;

	@Test
	public void shouldSearchFlightNumber() throws JsonProcessingException, wiremock.com.fasterxml.jackson.core.JsonProcessingException {

		SearchRequest searchRequest = new SearchRequest();

		EntityExchangeResult<SearchResponse> response = client.post().uri("/v1/flight").body(BodyInserters.fromValue(searchRequest))
				.exchange()
				.expectStatus()
				.is2xxSuccessful()
				.expectBody(SearchResponse.class)
		        .returnResult();

		//Assertions.assertEquals("DUMMY_FLIGHT_NUMBER", response.getResponseBody().getFlightNumber());
	}


}
