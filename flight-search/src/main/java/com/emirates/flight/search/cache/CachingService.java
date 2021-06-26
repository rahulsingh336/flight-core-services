package com.emirates.flight.search.cache;

import com.emirates.flight.search.dto.SearchRequest;
import com.emirates.flight.search.dto.SearchResponse;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.stereotype.Service;
import java.time.Duration;

@Service
public class CachingService {

	public final Cache<SearchRequest, SearchResponse>
			SEARCH_RESPONSE_CACHE = Caffeine.newBuilder()
			.expireAfterWrite(Duration.ofHours(24))
			.maximumSize(1_000)
			.build();

}
