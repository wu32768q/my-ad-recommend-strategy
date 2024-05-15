package org.example.bstest.demos.web.proxy;

import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixCommandMetrics;
import com.netflix.hystrix.strategy.metrics.HystrixMetricsPollerFactory;


public class HystrixProxy {



    public doProcess() {

        // if有一个Hystrix命令的key
        HystrixCommandKey commandKey = HystrixCommandKey.Factory.asKey("MyCommand");

        // 获取HystrixCommandMetrics实例
        HystrixCommandMetrics metrics = HystrixCommandMetrics.getInstance(commandKey,
                HystrixMetricsPollerFactory.getInstance().getDefaultPoller());

        // 获取并解析metrics
        HystrixCommandMetrics.HealthCounts healthCounts = metrics.getHealthCounts();

        System.out.println("Requests: " + healthCounts.getTotalRequests());
        System.out.println("Successes: " + healthCounts.getSuccesses());
        System.out.println("Failures: " + healthCounts.getFailures());




    }



}




