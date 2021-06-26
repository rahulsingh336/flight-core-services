package com.emirates.flight.search.configuration;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class ApplicationConfiguration {

	@Bean
	public ThreadPoolTaskExecutor taskExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(5);
		executor.setMaxPoolSize(100);
		executor.setQueueCapacity(100);
		executor.setThreadNamePrefix("slow-");
		executor.initialize();
		return executor;
	}

	@Bean
	@LoadBalanced
	WebClient.Builder loadBalanced() {
		return WebClient.builder()
				;
	}
}
