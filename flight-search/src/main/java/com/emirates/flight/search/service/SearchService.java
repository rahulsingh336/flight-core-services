package com.emirates.flight.search.service;

import com.emirates.flight.search.dto.GetAllFlightsResponse;
import com.emirates.flight.search.dto.SearchRequest;
import com.emirates.flight.search.dto.SearchResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple5;

@Service
@RequiredArgsConstructor
@Slf4j
public class SearchService {

	private PrepareZipCallsForDownstream prepareZipCallsForDownstream;

	@Autowired
	public SearchService(PrepareZipCallsForDownstream prepareZipCallsForDownstream) {
		this.prepareZipCallsForDownstream = prepareZipCallsForDownstream;
	}

	/**
	 * Call External Services and build response
	 *
	 * @param searchRequest the {@link SearchRequest}
	 */
	//@NewSpan
	public Mono<SearchResponse> getFlightNumber(SearchRequest searchRequest) {
		log.info("Entered send with paramters: {}", searchRequest);

		Mono<Tuple5<SearchResponse, SearchResponse, GetAllFlightsResponse, Void, Void>> zippedCalls = prepareZipCallsForDownstream.zipCalls(searchRequest);

		return zippedCalls.flatMap(results -> {
            // Here in real time some processing can be done from the response of down stream systems
			// When this operation is complete, the external notification service will be invoked, to send the OTP though the default channel.
			// The results is in a single Mono:
			SearchResponse searchResponse = new SearchResponse();
			searchResponse.setFlightNumber(results.getT1().getFlightNumber());
			return Mono.just(searchResponse);
		});
	}
}
