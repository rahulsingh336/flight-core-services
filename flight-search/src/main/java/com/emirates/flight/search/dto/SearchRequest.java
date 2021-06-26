package com.emirates.flight.search.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class SearchRequest {

	@NotNull(message="From Date cant'be empty")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private Date fromDate;

	@NotNull(message="To Date cant'be empty")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private Date toDate;

	@NotEmpty(message="Departure cant'be empty")
	private String departure;

	@NotEmpty(message="Arrival cant'be empty")
	private String arrival;
}
