package com.emirates.flight.search.service.external.calls;

import com.emirates.flight.search.dto.SearchRequest;
import com.emirates.flight.search.dto.SearchResponse;
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

import static com.emirates.flight.search.helper.ExternalURL.AGGREGATOR_SEARCH_URL;

@Component
@Slf4j
public class InvokeGetFlightInformationFromAggregatorService extends AbstractInvoker{

	@Autowired
	private RandomNumberGenerator randomNumberGenerator;

	public InvokeGetFlightInformationFromAggregatorService(WebClient webClient, ThreadPoolTaskExecutor taskExecutor) {
		super(webClient, taskExecutor);
	}

	public Mono<SearchResponse> getFlightInformationFromAggregator(SearchRequest searchRequest) {
		log.info(String.format("Calling getFlightInformationFromAggregator(%s)", searchRequest.toString()));
		return getWebClient().post()
				.uri(AGGREGATOR_SEARCH_URL.getValue())
				.accept(MediaType.APPLICATION_JSON)
				.body(BodyInserters.fromValue(searchRequest))
				.retrieve()
				.bodyToMono(SearchResponse.class)
				.publishOn(Schedulers.fromExecutor(getTaskExecutor()))
				.delaySubscription(Duration.ofMillis( randomNumberGenerator.getRandomNumber()))
				.subscribeOn(Schedulers.boundedElastic());
	}
}