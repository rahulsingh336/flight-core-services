package com.emirates.flight.search.service.external.calls;

import com.emirates.flight.search.dto.GetAllFlightsResponse;
import com.emirates.flight.search.dto.SearchRequest;
import com.emirates.flight.search.helper.ExternalURL;
import com.emirates.flight.search.helper.RandomNumberGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;

@Component
@Slf4j
public class InvokeGetAllFlightInformationService extends AbstractInvoker {

	@Autowired
	private RandomNumberGenerator randomNumberGenerator;

	public InvokeGetAllFlightInformationService(WebClient webClient, ThreadPoolTaskExecutor taskExecutor) {
		super(webClient, taskExecutor);
	}

	public Mono<GetAllFlightsResponse> getAllFlightInformation(SearchRequest searchRequest) {
		log.info(String.format("Calling getAllFlightInformation(%s)", searchRequest.toString()));

		return getWebClient().post()
				.uri(ExternalURL.GET_ALL_FLIGHTS_URL.getValue())
				.body(BodyInserters.fromValue(searchRequest))
				.accept(MediaType.APPLICATION_JSON)
				.retrieve()
				.bodyToMono(GetAllFlightsResponse.class)
				.publishOn(Schedulers.fromExecutor(getTaskExecutor()))
				.delaySubscription(Duration.ofMillis( randomNumberGenerator.getRandomNumber()))
				.subscribeOn(Schedulers.boundedElastic());
	}
}