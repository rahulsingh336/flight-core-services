package com.emirates.flight.search.service;

import com.emirates.flight.search.dto.GetAllFlightsResponse;
import com.emirates.flight.search.dto.SearchRequest;
import com.emirates.flight.search.dto.SearchResponse;
import com.emirates.flight.search.service.external.calls.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import reactor.util.function.Tuple5;

@Component
@Slf4j
public class PrepareZipCallsForDownstream {

	private InvokeGetAllFlightInformationService invokeGetAllFlightInformationService = null;
	private InvokeGetFlightInformationFromAggregatorService invokeGetFlightInformationFromAggregatorService = null;
	private InvokesendNotificationForChatService invokesendNotificationForChatService = null;
	private InvokepostToSalesServiceService invokepostToSalesServiceService = null;
	private InvokesearchFlightsInternalService invokesearchFlightsInternalService = null;

	public PrepareZipCallsForDownstream(InvokeGetAllFlightInformationService invokeGetAllFlightInformationService, InvokeGetFlightInformationFromAggregatorService invokeGetFlightInformationFromAggregatorService, InvokesendNotificationForChatService invokesendNotificationForChatService, InvokepostToSalesServiceService invokepostToSalesServiceService, InvokesearchFlightsInternalService invokesearchFlightsInternalService) {
		this.invokeGetAllFlightInformationService = invokeGetAllFlightInformationService;
		this.invokeGetFlightInformationFromAggregatorService = invokeGetFlightInformationFromAggregatorService;
		this.invokesendNotificationForChatService = invokesendNotificationForChatService;
		this.invokepostToSalesServiceService = invokepostToSalesServiceService;
		this.invokesearchFlightsInternalService = invokesearchFlightsInternalService;
	}

	Mono<Tuple5<SearchResponse, SearchResponse, GetAllFlightsResponse, Void, Void>> zipCalls(SearchRequest searchRequest){

		log.info(String.format("Calling zipCalls(%s)", searchRequest.toString()));

		Mono<GetAllFlightsResponse> getAllFlights = invokeGetAllFlightInformationService.getAllFlightInformation(searchRequest).subscribeOn(Schedulers.boundedElastic());

		Mono<SearchResponse> informationFromAggr  = invokeGetFlightInformationFromAggregatorService.getFlightInformationFromAggregator(searchRequest).subscribeOn(Schedulers.boundedElastic());

		Mono sendChatInvite = invokesendNotificationForChatService.sendNotificationForChat(searchRequest).subscribeOn(Schedulers.boundedElastic());

		Mono<SearchResponse> searchInternalFlights = invokesearchFlightsInternalService.searchFlightsInternal(searchRequest).subscribeOn(Schedulers.boundedElastic());

		Mono postToSalesData = invokepostToSalesServiceService.postToSalesService(searchRequest).subscribeOn(Schedulers.boundedElastic());

		return Mono.zip(informationFromAggr, searchInternalFlights, getAllFlights, sendChatInvite, postToSalesData);
	}
}
