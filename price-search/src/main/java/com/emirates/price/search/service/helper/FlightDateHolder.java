package com.emirates.price.search.service.helper;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.Objects;

@Data
@AllArgsConstructor
public class FlightDateHolder {

	private String flightNumber;

	private LocalDate date;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		FlightDateHolder that = (FlightDateHolder) o;
		return Objects.equals(getFlightNumber(), that.getFlightNumber()) &&
				Objects.equals(getDate(), that.getDate());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getFlightNumber(), getDate());
	}
}
