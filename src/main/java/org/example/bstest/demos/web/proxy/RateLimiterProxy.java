package org.example.bstest.demos.web.proxy;

import com.google.common.util.concurrent.RateLimiter;

import java.util.Map;

public class RateLimiterProxy {

    Map<String, RateLimiter> rateLimiterMap;

    public RateLimiter getRateLimiter(int numberPerSec, String limiterName) {
        if(rateLimiterMap.containsKey(limiterName)) {
            throw new IllegalArgumentException();
        }
        RateLimiter rateLimiter = RateLimiter.create(numberPerSec);
        rateLimiterMap.put(limiterName, rateLimiter);
        return rateLimiter;
    }

}
