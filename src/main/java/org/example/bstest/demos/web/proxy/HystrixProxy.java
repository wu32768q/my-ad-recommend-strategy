package org.example.bstest.demos.web.proxy;

import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixCommandMetrics;
import com.netflix.hystrix.strategy.metrics.HystrixMetricsPollerFactory;


public class HystrixProxy {

    // ...

    // 假设你有一个Hystrix命令的key
    HystrixCommandKey commandKey = HystrixCommandKey.Factory.asKey("MyCommand");

    // 获取HystrixCommandMetrics实例
    HystrixCommandMetrics metrics = HystrixCommandMetrics.getInstance(commandKey,
            HystrixMetricsPollerFactory.getInstance().getDefaultPoller());

    // 获取并解析metrics
    HealthCounts healthCounts = metrics.getHealthCounts();
System.out.println("Requests: " + healthCounts.getTotalRequests());
        System.out.println("Successes: " + healthCounts.getSuccesses());
        System.out.println("Failures: " + healthCounts.getFailures());

// 你也可以获取其他类型的metrics，如执行时间百分比、线程池metrics等


}




