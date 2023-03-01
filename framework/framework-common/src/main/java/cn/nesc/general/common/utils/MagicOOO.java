package cn.nesc.general.common.utils;

import cn.nesc.general.core.annotation.EnumHandler;
import cn.nesc.general.core.annotation.ParaDesc;
import cn.nesc.general.core.annotation.RequestHandler;
import cn.nesc.general.core.bean.BO;
import cn.nesc.general.core.bean.SystemParasMap;
import cn.nesc.general.core.common.Reflections;
import cn.nesc.general.core.enums.BaseEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class MagicOOO
{
    /**
     *  自动回写bo中数据字典的描述信息
     * @param bo
     */
    public static void getDescByCode(BO bo)
    {
        Field[] fields = bo.getClass().getDeclaredFields();
        Arrays.stream(fields).forEach(field -> {
            ParaDesc annotation = field.getAnnotation(ParaDesc.class);
            if (null != annotation)
            {
                String codeField = annotation.value();
//                log.debug("codeField ----->" + codeField);
                Object codeValue = Reflections.invokeGetter(bo, codeField);
                if (codeField instanceof String)
                {
//                    log.debug("codeValue ----->" + codeValue);
                    SystemParasMap systemParasMap = SystemParasMap.getInstance();
                    String desc = systemParasMap.getParasMap().get(codeValue);
                    if (StringUtils.isNotEmpty(desc))
                    {
                        Reflections.invokeSetter(bo, field.getName() ,desc);
                    }
                }
            }

        });
    }

    /**
     *  自动回写bo中数据字典的描述信息(枚举)
     * @param bo
     */
    public static void enumHandle(BO bo)
    {
        Field[] fields = bo.getClass().getDeclaredFields();
        Arrays.stream(fields).forEach(field -> {
            EnumHandler annotation = field.getAnnotation(EnumHandler.class);
            if (null != annotation)
            {
                Class<? extends BaseEnum> baseEnum = annotation.value();
                Object codeValue = Reflections.invokeGetter(bo, field.getName());
                if (null != codeValue)
                {
                    String desc = EnumUtil.getEnumMessage("" + codeValue, baseEnum);
                    if (StringUtils.isNotEmpty(desc))
                    {
                        Reflections.invokeSetter(bo, field.getName() ,desc);
                    }
                }
            }

        });
    }


    /**
     * @Description: 调用数据中台前，码表转化
     * @Author: jinjin
     * @Date: 2021/11/12 10:04
     * @Param: [bo]
     * @retrun: void
     **/
    public static void enumRequestHandle(BO bo)
    {
        Field[] fields = bo.getClass().getDeclaredFields();
        Arrays.stream(fields).forEach(field -> {
            RequestHandler annotation = field.getAnnotation(RequestHandler.class);
            if (null != annotation)
            {
                Class<? extends BaseEnum> baseEnum = annotation.value();
                Object codeValue = Reflections.invokeGetter(bo, field.getName());
                if (null != codeValue)
                {
                    String desc = EnumUtil.getEnumMessage("" + codeValue, baseEnum);
                    if (StringUtils.isNotEmpty(desc))
                    {
                        Reflections.invokeSetter(bo, field.getName() ,desc);
                    }
                }
            }

        });
    }

    public static void enumRequestHandle(List<? extends BO> boList)
    {
        boList.stream().forEach(bo -> enumHandle(bo));
    }

    /**
     * 批量自动回写bo中数据字典的描述信息
     * @param boList
     */
    public static void getDescByCode(List<? extends BO> boList)
    {
        boList.stream().forEach(bo -> getDescByCode(bo));
    }

    /**
     *  批量自动回写bo中数据字典的描述信息(枚举)
     * @param boList
     */
    public static void enumHandle(List<? extends BO> boList)
    {
        boList.stream().forEach(bo -> enumHandle(bo));
    }

    /**
     *  bean复制
     * @param target
     * @param origin
     */
    public static void copyProperties(Object target, Object origin)
    {
        ObjectUtils.copyProperties(target, origin);
    }

    public static List copyProperties(List origin, Class<? extends BO> clz)
    {
        List<? extends BO> boList = new ArrayList<>();

        boList = (List<? extends BO>) origin.stream().map(t -> {
            BO r = null;
            try
            {
                r = clz.newInstance();
                ObjectUtils.copyProperties(r, t);
            }
            catch (InstantiationException e)
            {
                e.printStackTrace();
            }
            catch (IllegalAccessException e)
            {
                e.printStackTrace();
            }
            return r;
        }).collect(Collectors.toList());
        return boList;
    }
}
