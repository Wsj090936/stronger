package com.wsj.stronger.annotions;

import java.lang.annotation.*;

/**
 * TODO
 *
 * @Author jiahao
 * @Date 2020/12/1 20:14
 */
@Target({ElementType.FIELD})
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface AutoWired {
    String value() default "";
}
