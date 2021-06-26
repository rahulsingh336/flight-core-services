package com.emirates.flight.search.service.external.calls;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.reactive.function.client.WebClient;

public abstract class AbstractInvoker {

	private WebClient webClient;
	private ThreadPoolTaskExecutor taskExecutor;

	public AbstractInvoker(WebClient webClient, ThreadPoolTaskExecutor taskExecutor) {
		this.webClient = webClient;
		this.taskExecutor = taskExecutor;
	}

	WebClient getWebClient() {
		return webClient;
	}

	ThreadPoolTaskExecutor getTaskExecutor(){
		return taskExecutor;
	}
}
