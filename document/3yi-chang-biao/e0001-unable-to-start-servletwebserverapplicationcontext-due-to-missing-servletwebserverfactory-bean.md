## Unable to start ServletWebServerApplicationContext due to missing ServletWebServerFactory bean.

### 1.异常

```bash
org.springframework.context.ApplicationContextException: Unable to start web server; nested exception is org.springframework.context.ApplicationContextException: Unable to start ServletWebServerApplicationContext due to missing ServletWebServerFactory bean.
    at org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext.onRefresh(ServletWebServerApplicationContext.java:155) ~[spring-boot-2.0.1.RELEASE.jar:2.0.1.RELEASE]
    at org.springframework.context.support.AbstractApplicationContext.refresh(AbstractApplicationContext.java:544) ~[spring-context-5.0.5.RELEASE.jar:5.0.5.RELEASE]
    at org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext.refresh(ServletWebServerApplicationContext.java:140) ~[spring-boot-2.0.1.RELEASE.jar:2.0.1.RELEASE]
    at org.springframework.boot.SpringApplication.refresh(SpringApplication.java:759) ~[spring-boot-2.0.1.RELEASE.jar:2.0.1.RELEASE]
    at org.springframework.boot.SpringApplication.refreshContext(SpringApplication.java:395) ~[spring-boot-2.0.1.RELEASE.jar:2.0.1.RELEASE]
    at org.springframework.boot.SpringApplication.run(SpringApplication.java:327) ~[spring-boot-2.0.1.RELEASE.jar:2.0.1.RELEASE]
    at org.springframework.boot.builder.SpringApplicationBuilder.run(SpringApplicationBuilder.java:137) [spring-boot-2.0.1.RELEASE.jar:2.0.1.RELEASE]
    at com.mbcloud.slot.EurekaClientApp.main(EurekaClientApp.java:18) [classes/:na]
Caused by: org.springframework.context.ApplicationContextException: Unable to start ServletWebServerApplicationContext due to missing ServletWebServerFactory bean.
    at org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext.getWebServerFactory(ServletWebServerApplicationContext.java:204) ~[spring-boot-2.0.1.RELEASE.jar:2.0.1.RELEASE]
    at org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext.createWebServer(ServletWebServerApplicationContext.java:178) ~[spring-boot-2.0.1.RELEASE.jar:2.0.1.RELEASE]
    at org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext.onRefresh(ServletWebServerApplicationContext.java:152) ~[spring-boot-2.0.1.RELEASE.jar:2.0.1.RELEASE]
    ... 7 common frames omitted
```

### 2.分析/解决

对于很多Spring Cloud的初学者，不太熟悉Spring Boot，会经常遇到该异常，该异常意味着拥有了Web项目的启动组件，但Spring找不到Web容器，通常使用的容器有三种：`Jetty、Tomcat、Undertow`。可以在Maven中配置下边内容（随便玩玩，提供参考）

#### 默认spring-boot（Tomcat）

```xml
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
```

#### Tomcat容器

> 由于Spring Cloud中已经提供了Web项目的启动Starter组件，所以可直接配置Tomcat容器。

```xml
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-tomcat</artifactId>
        </dependency>
```

#### Jetty容器

```xml
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-jetty</artifactId>
        </dependency>
```

#### Undertow容器（推荐）

```xml
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-undertow</artifactId>
        </dependency>
```



