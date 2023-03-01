package cn.nesc.general.core.annotation;

import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ParaDesc
{
    String value() default "";
}
