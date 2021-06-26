package com.emirates.flight.search.service.external.calls;

import com.emirates.flight.search.dto.SearchRequest;
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

import static com.emirates.flight.search.helper.ExternalURL.SALES_JOURNEY_TRACKER_URL;

@Component
@Slf4j
public class InvokepostToSalesServiceService extends AbstractInvoker{

	@Autowired
	private RandomNumberGenerator randomNumberGenerator;

	public InvokepostToSalesServiceService(WebClient webClient, ThreadPoolTaskExecutor taskExecutor) {
		super(webClient, taskExecutor);
	}

	public Mono postToSalesService(SearchRequest searchRequest) {
		log.info(String.format("Calling postToSalesService(%s)", searchRequest.toString()));
		return getWebClient().post()
				.uri(SALES_JOURNEY_TRACKER_URL.getValue())
				.accept(MediaType.APPLICATION_JSON)
				.body(BodyInserters.fromValue(searchRequest))
				.retrieve()
				.toBodilessEntity()
				.publishOn(Schedulers.fromExecutor(getTaskExecutor()))
				.delaySubscription(Duration.ofMillis( randomNumberGenerator.getRandomNumber()))
				.subscribeOn(Schedulers.boundedElastic());
	}
}