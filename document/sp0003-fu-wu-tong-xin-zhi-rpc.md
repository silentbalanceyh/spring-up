# SP0003 - 服务通信之Rpc

spring-up中的服务通信使用了gRpc，为了方便开发人员开发服务间的通信，简化了底层的通信细节，本教程讲解在spring-up中如何开发服务通信部分的内容。`spring-up`中使用的`grpc`的spring boot的starter信息（**由于spring-up中已经引入了，所以不需要再额外修改项目中的**`pom.xml`）：

```xml
<dependency>
    <groupId>net.devh</groupId>    
    <artifactId>grpc-client-spring-boot-starter</artifactId>
</dependency>
<dependency>
    <groupId>net.devh</groupId>
    <artifactId>grpc-server-spring-boot-starter</artifactId>
</dependency>
```

## 0.配置

在书写Rpc的相关信息之前，需要先修改当前项目的配置信息，实例如下：`application.yml`中：

```yaml
grpc:
    server:
        port: 8801
    client:
        mbuaa:
            port[0]: 8801
        mbenv:
            port[0]: 8802
        mborg:
            port[0]: 8803
```

* `grpc.server.port`表示当前服务作为`gRpc Server`开放的端口号；
* `grpc.client.<name>.port[x]`则表示当前服务需要访问的目标服务信息，`<name>`为服务名称，比如这里的`mbuaa`，`mbenv`都是服务名称，`x`为该目标服务开放的端口号索引，目前用0；

## 1.服务端

在`spring-up`中开发`grpc`服务端很简单，一旦配置了端口过后，则可以直接写下边代码来发布服务：

```java
package com.mbcloud.platform.ipc;

import io.spring.up.annotations.Ipc;
import io.vertx.core.json.JsonObject;
import org.springframework.stereotype.Component;

@Component
public class TestJet {

    @Ipc("IPC://TEST/CREATE")
    public JsonObject createTodo(final JsonObject envelop) {

        return new JsonObject();
    }
}
```

* 该类必须使用`@Component`进行注解（Spring中的注解）；
* 注意这里的字符串`IPC://TEST/CREATE`，该字符串为客户端定位方法专用；
* 方法签名只能是：`JsonObject xxx(JsonObject)`，在大多数场景下，已经足够用，所以不提供其他复杂的异构数据结构，而且`io.vertx.core.json.JsonObject`中除了字符串以外也支持字节流数据，所以完全可以满足大部分需求。

下边是一个完整的例子：（服务名称：**mbenv**）

```java
package com.mbcloud.platform.ipc;

import com.mbcloud.platform.domain.Menu;
import com.mbcloud.platform.domain.Todo;
import com.mbcloud.platform.domain.enumeration.TodoStatus;
import com.mbcloud.platform.service.MenuService;
import com.mbcloud.platform.service.TodoService;
import io.spring.up.annotations.Ipc;
import io.vertx.core.json.JsonObject;
import io.zero.epic.Ut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class TodoJet {
    private static final Logger log = LoggerFactory.getLogger(TodoJet.class);
    private final TodoService service;
    private final MenuService menuService;

    public TodoJet(final TodoService service, final MenuService menuService) {
        this.service = service;
        this.menuService = menuService;
    }

    @Ipc("IPC://TODO/CREATE")
    public JsonObject createTodo(final JsonObject envelop) {
        final Todo todo = Ut.deserialize(envelop, Todo.class);
        todo.setEtat(TodoStatus.PENDING);
        todo.setId(UUID.randomUUID().toString());
        // 读取Todo关联菜单
        final Menu menu = this.menuService.findByUri(Ut.netUri(todo.getUrl()));
        todo.setMenuId(null == menu ? null : menu.getId());
        return Ut.serializeJson(this.service.save(todo));
    }
}
```

## 2.客户端

上边开放了`mbenv`服务中的内容，那么接下来在`mborg`服务中调用该服务，调用的部分代码如下（**只有关键部分**）：

```java
// ....省略其他import
import io.grpc.Channel;
import io.reactivex.Single;
import io.spring.up.aiki.Ux;
import io.spring.up.annotations.JsonEntity;
import io.spring.up.model.Envelop;
import io.vertx.core.json.JsonObject;
import lombok.extern.slf4j.Slf4j;
import net.devh.springboot.autoconfigure.grpc.client.GrpcClient;

@RestController
@RequestMapping("/")
@Slf4j
public class RegistryResource {
    private final EnterpriseService enterpriseService;

    @GrpcClient("mbenv")
    private Channel channel;

    @PostMapping("/enterprise/registry")
    @Timed
    public Single<ResponseEntity<Enterprise>> createEnterprise(
        // 使用这个注解的前提是不使用@Valid和@RequestBody
        @JsonEntity("enterprise.registry") final Enterprise enterprise) {
        log.debug("REST request to save Enterprise : {}", enterprise);
        // 设置企业租户ID
        enterprise.setTenantId(UUID.randomUUID().toString());
        return Single.fromFuture(this.enterpriseService.save(enterprise))
            .map(item -> Ux.Rpc.getClient(this.channel).rxFlow("IPC://TODO/CREATE", this.toEnvelop(item)))
            .map(async -> ResponseEntity.ok(enterprise));
    }

    private Envelop toEnvelop(final Enterprise enterprise) {
        final JsonObject request = new JsonObject();
        request.put("createdBy", enterprise.getCreatedBy());
        request.put("createdDate", enterprise.getCreatedDate());
        request.put("tenantId", enterprise.getTenantId());
        // 构造Todo，type = ENTERPRISE-APPROVAL：审批企业专用ToDo模板
        final TodoDefine define = TodoDefine.get("ENTERPRISE-APPROVAL");
        final JsonObject params = new JsonObject();
        params.put("id", enterprise.getId());
        params.put("name", enterprise.getCnName());
        request.mergeIn(define.toData(params));
        return Envelop.success(request);
    }
}
```

* 注意`@GrpcClient`注解中的value，该值则直接引用远程的服务名称，这里是`mbenv`；
* 发送请求调用`Ux.Rpc.getClient(this.channel).rxFlow`方法，至于其他的API开发人员可以去看`spring-up`中的`RpcClient`类；
* 传入的参数为一个`io.spring.up.model.Envelop`，该对象可以使用`Ux`中的工具实现互转；

## 3. 启动日志

服务端启动时应该可以看到如下日志，证明该地址上的RPC服务启动正常了：

![](/document/image/SP0003-1.png)

