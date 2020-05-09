# SC0001 - Netflix笔记：服务发现Eureka Client

官方引用：【[Reference](http://cloud.spring.io/spring-cloud-netflix/single/spring-cloud-netflix.html)】

## 1.配置

### 1.1. Maven

```xml
<parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-parent</artifactId>
    <version>2.0.1.RELEASE</version>
</parent>
<dependencyManagement>
    <dependencies>
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-dependencies</artifactId>
            <version>Finchley.SR1</version>
            <type>pom</type>
            <scope>import</scope>
        </dependency>
    </dependencies>
</dependencyManagement>
<dependencies>
    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-config</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
    </dependency>
</dependencies>
```

### 1.2. 注册客户端Java

```java
@SpringBootApplication
@RestController
public class Application {

    @RequestMapping("/")
    public String home() {
        return "Hello world";
    }

    public static void main(String[] args) {
        new SpringApplicationBuilder(Application.class).web(true).run(args);
    }
}
```

### 1.3. 配置文件

application.yml

```yaml
eureka:
  client:
    serviceUrl:
      defaultZone: http://localhost:8761/eureka/ 
      ## 注意http://localhost:8761/eureka就是Eureka Server启动后的地址，
      ## 在启动Client程序时需要保证该Server处于启动状态
```

如果只是上边部分的代码，对的，您要启动是不行的，Spring Cloud本来就需要您有Spring Boot的基础，如果您想顺利启动，可以参考：【[E00001](/3yi-chang-biao/e0001-unable-to-start-servletwebserverapplicationcontext-due-to-missing-servletwebserverfactory-bean.md)】，即使配置了容器，这个Client的应用还是不可启动的，因为它在配置中需要访问`Eureka Server`，配置中的地址为：[http://localhost:8761/eureka](http://localhost:8761/eureka)，所以默认的\`Eureka Client\`需要结合对应的Server才能启动，否则会遇到错误：【[E00002](/3yi-chang-biao/e00002-cannot-execute-request-on-any-known-server.md)】。

