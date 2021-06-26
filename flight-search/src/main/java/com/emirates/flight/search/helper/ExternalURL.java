package com.emirates.flight.search.helper;

public enum ExternalURL {

	GET_ALL_FLIGHTS_URL("http://discount-service/flights/all"),
	AGGREGATOR_SEARCH_URL("http://aggregator-service/aggregator/search"),
	NOTIFICATIONS_CHAT_URL("http://notification-service/notifications/chat"),
	SALES_JOURNEY_TRACKER_URL("http://sales-service/sales/journey/tracker"),
	EMIRATES_SEARCH_URL("http://emirates-search-service/emirates/search");

	ExternalURL(String value) {
		this.value = value;
	}
	private String value;

	public String getValue(){
		return value;
	}
}
