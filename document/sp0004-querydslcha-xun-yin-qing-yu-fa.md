# SP0004 - QueryDSL查询引擎语法

使用QueryDSL的目的是泛化所有的查询流程，由于VertxUI中设计了完整的查询参数数据，而且该参数和Zero中的参数一致，所以spring-up中提供查询引擎直接生成QueryDSL的查询语句读取相关数据，您可以使用spring-data，但在不使用该依赖包的时候，则使用spring-up中的QueryDSL执行各种动态查询是比较方便的一种做法。

## 0.请求格式

查询部分的请求格式参考下边的Json

```json
{
    "criteria": {
        "enterprise.id,=": "49640202-f767-4e46-b892-34b511d9f50f"
    },
    "pager": {
        "page": 1,
        "size": 10
    },
    "projection": [],
    "sorter": [
        "code,DESC"
    ]
}
```

* pager：该参数为分页参数，page代表页码（非索引），size则是每一页的条数；
* sorter：该参数为排序规则，为一个JsonArray，排序字段优先级按照数组中的索引来处理；
* projection：该参数为列过滤（目前未启用，如果使用了列过滤，包含在projection中的数据才会被返回）；
* criteria：专用查询条件基础语法，本文主要讲解该参数的语法；

## 1.字段和操作符

criteria参数的查询条件为`key = value`中的格式，其中`key`中包含了基本操作符，字段名和操作符之间使用逗号分开，如果不写则视为`=`操作符，如果一个字段包含多个操作符则直接可写成`field,<`和`field,>`这种格式，作为Json的键值也不会有冲突。所有的操作符如下：

| 操作符 | 写法 | 含义 |
| :--- | :--- | :--- |
| &lt; | field,&lt; | 该字段小于某个值 |
| &lt;= | field,&lt;= | 该字段小于等于某个值 |
| &gt; | field,&gt; | 该字段大于某个值 |
| &gt;= | field,&gt;= | 该字段大于等于某个值 |
| = | field,= 或直接写field | 该字段等于某个值 |
| &lt;&gt; | field,&lt;&gt; | 该字段不等于某个值 |
| !n | field,!n | 该字段不为NULL |
| n | field,n | 该字段为NULL |
| t | field,t | 该字段为TRUE |
| f | field,f | 该字段为FALSE |
| i | field,i | 使用IN操作符，此时的右值必须是一个JsonArray的类型 |
| !i | field,!i | 使用NOT IN操作符，此时的右值必须是一个JsonArray的类型 |
| s | field,s | Start With 以某个值开始，等价：field%的LIKE |
| e | field,e | End With 以某个值结束，等价：%field的LIKE |
| c | field,c | Contains 包含某个值，模糊匹配：等价：%field%的LIKE |

## 2.线性条件和树

criteria的格式支持线性条件和树两种结构，并且使用了特殊语法来处理，接下来讲该值的格式：

### 2.1.线性条件和树的判断

判断线性条件和树主要依赖于该Json格式的数据中是否包含了`JsonObject`的值，如：

```json
{
    "criteria":{
        "name":"Lang",
        "code":"Test"
    }
}
```

上述数据中name和code的值都是字符串，所以该条件会被分析成线性条件，又如：

```json
{
    "criteria": {
        "enterprise.id,=": "49640202-f767-4e46-b892-34b511d9f50f",
        "": true,
        "$1": {
            "code,=": "lang",
            "name,=": "Lang"
        }
    }
}
```

上述条件则会被解析成查询树，由于多了一个`$1`的节点，该节点为`JsonObject`的结构。

### 2.2.连接符

每一个`key=value`会生成Where子句，子句之间的连接符依靠同级的JsonObject数据中是否包含空字符串的键（该键对于业务系统而言为一个使用的盲区），然后同级的树和线性条件使用该连接字符串进行连接，基本规则如下：

* 如果不包含`""`的键，则默认使用`OR`连接符；
* 即使包含了`""`的键，也只有在为`true`的时候使用`AND`连接符；

如2.1中的第一个将生成条件：

```sql
name = 'Lang' OR code = 'Test'
```

查询的语句会在DEBUG日志中打印：

```shell
[ UP ] [QE] Criteria = department.code = Test || department.name = Lang
```

而2.1中的条件将生成：

```sql
enterprise_id = '49640202-f767-4e46-b892-34b511d9f50f' AND (name = 'Lang' OR code = 'lang')
```

查询的日志如：

```shell
[ UP ] [QE] Criteria = (department.code = lang || department.name = Lang) \
    && department.enterprise.id = 49640202-f767-4e46-b892-34b511d9f50f
```

## 3. 使用说明

### 3.1.JPA依赖

该查询引擎依赖于JPA的基本操作，所以日志中打印的语法为JPA中的专用语法，上边的例子为什么是`enterprise_id`，由于在项目中有两个对象：`Department`和`Enterprise`，而它的JPA定义如下：

```java
// Department.java
    @ManyToOne
    @JsonIgnoreProperties("departments")
    private Enterprise enterprise;

// Enterprise.java
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @JsonProperty(value = "key")
    private String id;
```

### 3.2.字段支持二级

从上边的第二个表示式可以看到，条件语句为：

```json
"enterprise.id,=": "49640202-f767-4e46-b892-34b511d9f50f"
```

> 实际上上边的查询在查询Department的部门实体的信息，Department通过JPA关联到Enterprise，拥有enterprise的字段，而enterprise中的主键名为id，这种情况可直接将字段写成：`enterprise.id`的格式作为字段名，并且考虑大多数情况仅支持二级，如果涉及到三级以上的，您可以考虑下您的实际需求和设计了，一切以简化为主。

### 3.3.关于QueryDSL

关于query dsl的文档在这里就不重复了，主要是保证您的Maven插件已经生成了对应的实体，一般是`Q<Name>`的实体名称。

### 3.4.代码应用

一旦生成好Query的实体后（这里Department对应生成的Q实体为：QDepartment），代码部分可写成：

```java
// import部分
import com.mbcloud.platform.domain.Department;
import com.mbcloud.platform.domain.QDepartment;
import com.querydsl.jpa.impl.JPAQueryFactory;
import io.spring.up.aiki.Ux;
import io.vertx.core.json.JsonObject;
import javax.persistence.EntityManager;

// 依赖注入
    private final EntityManager manager;

// 书写部分
    @Override
    @Async
    public Future<JsonObject> search(final JsonObject params) {
        return AsyncResult.forValue(Ux.<Department>dsl(params)
            .on(new JPAQueryFactory(this.manager))
            .on(QDepartment.department)
            .searchFull());
    }
```

## 4. 最终响应

最终响应格式如，`list`为当前页记录数，`count`为当前查询条件下的总的记录数：

```json
{
    "list":[
        {
            "id" : "824c2c86-5a45-47a0-96e6-0986a5e1adc3",
            "code" : "XQQYXI",
            "name" : "边正争部",
            "manager" : "梁超",
            "active" : false
        }
        ...
    ],
    "count":33
}
```

> 这里调用的是`searchFull()`的API，也可以只调用`searchAdvanced()`或`countAdvanced()`单独读取`List<T>`或记录数。



