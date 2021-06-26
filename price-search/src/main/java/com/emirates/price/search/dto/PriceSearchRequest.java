package com.emirates.price.search.dto;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@ToString
public class PriceSearchRequest {

	@NotNull(message = "From Date cant'be empty")
	private LocalDate date;

	@NotEmpty(message = "Flight Number Cannot be empty")
	private String flightNumber;

}
