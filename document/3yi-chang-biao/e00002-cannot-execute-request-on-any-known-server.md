## Cannot execute request on any known server

### 1.异常

```bash
2018-08-03 20:25:27.742 ERROR 3661 --- [           main] c.n.d.s.t.d.RedirectingEurekaHttpClient  : Request execution error
.....
com.netflix.discovery.shared.transport.TransportException: Cannot execute request on any known server
    at com.netflix.discovery.shared.transport.decorator.RetryableEurekaHttpClient.execute(RetryableEurekaHttpClient.java:112) ~[eureka-client-1.9.3.jar:1.9.3]
    at com.netflix.discovery.shared.transport.decorator.EurekaHttpClientDecorator.register(EurekaHttpClientDecorator.java:56) ~[eureka-client-1.9.3.jar:1.9.3]
    at com.netflix.discovery.shared.transport.decorator.EurekaHttpClientDecorator$1.execute(EurekaHttpClientDecorator.java:59) ~[eureka-client-1.9.3.jar:1.9.3]
    at com.netflix.discovery.shared.transport.decorator.SessionedEurekaHttpClient.execute(SessionedEurekaHttpClient.java:77) ~[eureka-client-1.9.3.jar:1.9.3]
    at com.netflix.discovery.shared.transport.decorator.EurekaHttpClientDecorator.register(EurekaHttpClientDecorator.java:56) ~[eureka-client-1.9.3.jar:1.9.3]
    at com.netflix.discovery.DiscoveryClient.register(DiscoveryClient.java:829) ~[eureka-client-1.9.3.jar:1.9.3]
    at com.netflix.discovery.InstanceInfoReplicator.run(InstanceInfoReplicator.java:121) ~[eureka-client-1.9.3.jar:1.9.3]
    at com.netflix.discovery.InstanceInfoReplicator$1.run(InstanceInfoReplicator.java:101) [eureka-client-1.9.3.jar:1.9.3]
    at java.util.concurrent.Executors$RunnableAdapter.call(Executors.java:511) [na:1.8.0_163]
    at java.util.concurrent.FutureTask.run(FutureTask.java:266) [na:1.8.0_163]
    at java.util.concurrent.ScheduledThreadPoolExecutor$ScheduledFutureTask.access$201(ScheduledThreadPoolExecutor.java:180) [na:1.8.0_163]
    at java.util.concurrent.ScheduledThreadPoolExecutor$ScheduledFutureTask.run(ScheduledThreadPoolExecutor.java:293) [na:1.8.0_163]
    at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1149) [na:1.8.0_163]
    at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:624) [na:1.8.0_163]
    at java.lang.Thread.run(Thread.java:748) [na:1.8.0_163]
```

## 2.分析

由于您直接使用了`Eureka Client`的应用，它在启动时会去访问`Eureka Server`，该配置是在`application.yml`中配置好的。

