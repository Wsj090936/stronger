package com.wsj.stronger.annotions;

import java.lang.annotation.*;

/**
 * TODO
 *
 * @Author jiahao
 * @Date 2020/12/1 20:14
 */
@Target({ElementType.TYPE,ElementType.METHOD})
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface RequestMapping {
    String value() default "";
}
