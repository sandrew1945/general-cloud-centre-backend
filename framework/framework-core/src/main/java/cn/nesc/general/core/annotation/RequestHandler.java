package cn.nesc.general.core.annotation;


import cn.nesc.general.core.enums.BaseEnum;

import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequestHandler
{
    Class<? extends BaseEnum> value();
}
