package com.emirates.flight.search.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class GetAllFlightsResponse {

	private List<String> allFlights = new ArrayList<>();

}
