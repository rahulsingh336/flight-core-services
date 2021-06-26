package com.emirates.flight.search.service;

import com.emirates.flight.search.cache.CachingService;
import com.emirates.flight.search.dto.SearchRequest;
import com.emirates.flight.search.dto.SearchResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.cache.CacheMono;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Signal;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class SearchService {

	private DownstreamInvoker callsForDownstreamFlightNumberService;
	private CachingService cachingService;

	@Autowired
	public SearchService(DownstreamInvoker callsForDownstreamFlightNumberService, CachingService cachingService) {
		this.callsForDownstreamFlightNumberService = callsForDownstreamFlightNumberService;
		this.cachingService = cachingService;
	}

	/**
	 * Call External Services and build response
	 *
	 * @param searchRequest the {@link SearchRequest}
	 */
	//@NewSpan
	public Mono<SearchResponse> getFlightNumber(SearchRequest searchRequest) {
		log.info("Entered send with parameters: {}", searchRequest);

		return
				CacheMono.lookup(key -> Mono.justOrEmpty(cachingService.SEARCH_RESPONSE_CACHE.getIfPresent(key))
						.map(Signal::next), searchRequest)
						.onCacheMissResume(() -> callsForDownstreamFlightNumberService.getFlightNumber(searchRequest))
						.andWriteWith((key, signal) -> Mono.fromRunnable(() ->
								Optional.ofNullable(signal.get())
										.ifPresent(value -> cachingService.SEARCH_RESPONSE_CACHE.put(key, value))));

	}
}
