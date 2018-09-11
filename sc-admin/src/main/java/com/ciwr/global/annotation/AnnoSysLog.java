package com.ciwr.global.annotation;

import java.lang.annotation.*;

@Target(value={ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AnnoSysLog {
    String value() default "";
}
