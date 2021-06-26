package com.emirates.flight.search.configuration;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.reactive.ReactorLoadBalancerExchangeFilterFunction;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@LoadBalancerClients({   @LoadBalancerClient(value = "discount-service")
					    ,@LoadBalancerClient(value = "notification-service")
						,@LoadBalancerClient(value = "sales-service")
						,@LoadBalancerClient(value = "aggregator-service")
						,@LoadBalancerClient(value = "emirates-search-service")
																	})
public class WebClientConfig {

	@LoadBalanced
	@Bean
	WebClient webClient(ReactorLoadBalancerExchangeFilterFunction lbFunction) {
		return WebClient.builder().filter(lbFunction).defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).build();
	}

}
