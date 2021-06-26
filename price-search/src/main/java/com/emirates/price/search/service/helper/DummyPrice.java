package com.emirates.price.search.service.helper;

import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class DummyPrice {

	/**
	 * Map is used to Replace the Database/actual call
	 * */

	static public Map<FlightDateHolder, Integer> flightNumberDateMap = new ConcurrentHashMap();

	static int count=10004;

	static
	{
		LocalDate localDate = LocalDate.now();
		flightNumberDateMap.put(new FlightDateHolder("AB-1", localDate), 336);
		flightNumberDateMap.put(new FlightDateHolder("AB-2", localDate.plusDays(2)), 12345);
		flightNumberDateMap.put(new FlightDateHolder("AB-3", localDate.plusDays(5)), 34567);
		flightNumberDateMap.put(new FlightDateHolder("AB-14", localDate.plusDays(10)), 6789);
	}

	public Mono<Integer> getFlightPrice(FlightDateHolder flightDateHolder){

		Integer price = flightNumberDateMap.get(flightDateHolder);
		if(price != null){
		    return Mono.just(price);
		} else {
			return Mono.empty();
		}
	}
}
