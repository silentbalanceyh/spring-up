# Spring Up Scaffold

该脚手架为vertx-zero的衍生脚手架，主要用于简化spring-cloud的开发用，关于该脚手架的目的参考：[0.初赦 - 序章](/0chu-she-xu-zhang.md)

辅助工具相关参考：

* 前端：[http://www.vertxui.cn](http://www.vertxui.cn)
* 工具：[http://www.vertxai.cn](http://www.vertxai.cn)

## 1.开始

引入该脚手架的步骤很简单，直接修改两处即可：

在`pom.xml`中引入`spring-up`的工具包

```xml
    <dependency>
        <groupId>cn.spring-up</groupId>
        <artifactId>spring-co</artifactId>
        <version>0.2</version>
    </dependency>
```

> 如果需要借用vertx-zero中的Utility X工具包中的内容，可直接多引入一份（不用引入up的引擎包）

```xml
    <dependency>
        <groupId>cn.vertxup</groupId>
        <artifactId>vertx-co</artifactId>
        <version>0.4.8</version>
    </dependency>
```

在spring-boot的启动脚本中加入

```java
@ComponentScan({"io.spring.up.boot"})
public class MbOrgApp {
    public static void main(final String args[]){
        // ......
    }
}
```

> 本次发布的办法以JHipster为主，由于公司项目原因，所有测试在JHipster的微服务环境中。

## 2.开发文档

该脚手架的使用文档参考如下：

* [SP0001 - @JsonBody注解](/document/sp0001-jsonbodyzhu-jie.md)
* [SP0002 - @JsonEntity注解](/document/sp0002-jsonentityzhu-jie.md)
* [SP0003 - 服务通信之Rpc](/document/sp0003-fu-wu-tong-xin-zhi-rpc.md)
* [SP0004 - QueryDSL查询引擎语法](/document/sp0004-querydslcha-xun-yin-qing-yu-fa.md)
* [SP0005 - 基于Spring Security的身份信息读取](/document/sp0005-ji-yu-spring-security-de-shen-fen-xin-xi-du-qu.md)
* [SP0006 - 开发自定义异常](/document/sp0006-kai-fa-zi-ding-yi-yi-chang.md)
* [SP0007 - 数据接口规范（同Zero）](/document/sp0007-shu-ju-jiekou-gui-fan-ff08-tong-zero.md)
* [SP0008 - 基于JHipster的UAA的修正](/document/sp0008-ji-yu-jhipster-de-uaa-de-xiu-zheng.md)

## 3.Spring Cloud笔记

* [SC0001 - Netflix笔记：服务发现Eureka Client](/sc0001-netflixbi-ji.md)

## 4.异常表

* [E00001 - Unable to start ServletWebServerApplicationContext due to missing ServletWebServerFactory bean.](/3yi-chang-biao/e0001-unable-to-start-servletwebserverapplicationcontext-due-to-missing-servletwebserverfactory-bean.md)
* [E00002 - Cannot execute request on any known server](/3yi-chang-biao/e00002-cannot-execute-request-on-any-known-server.md)



