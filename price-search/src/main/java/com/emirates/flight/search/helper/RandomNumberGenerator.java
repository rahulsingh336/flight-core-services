package com.emirates.flight.search.helper;

import org.springframework.stereotype.Component;

import java.util.concurrent.ThreadLocalRandom;

@Component
public class RandomNumberGenerator {

	public int getRandomNumber() {
		return ThreadLocalRandom.current().nextInt(600, 800);
	}
}
