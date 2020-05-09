package io.spring.up.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface JsonEntity {
    // 使用的验证文件地址
    String value();

    // 使用的验证目录地址，目录地址不提供时不可支持嵌套验证模式
    String folder() default "";

    // 该参数是否为必须，如果必须不填写时则报错
    boolean required() default true;
}
