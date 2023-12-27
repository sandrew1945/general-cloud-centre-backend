package cn.nesc.general.core.util;

import cn.nesc.general.core.enums.BaseEnum;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Optional;

public class EnumUtil
{
    public static String getEnumMessage(String code, Class<? extends BaseEnum> clz)
    {
        String message = null;
        try
        {
            Method method = clz.getMethod("values", null);
            BaseEnum[] baseEnums = (BaseEnum[])method.invoke(null, null);
            Optional<BaseEnum> optional = Arrays.stream(baseEnums).filter(baseEnum -> baseEnum.getCode().toString().equals(code)).findAny();
            if (optional.isPresent())
            {
                message = optional.get().getDesc();
            }
        }
        catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e)
        {
            e.printStackTrace();
        }
        return message;
    }
}
