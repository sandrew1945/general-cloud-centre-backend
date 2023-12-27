package cn.nesc.general.core.util;

import cn.nesc.general.core.bean.SystemParasMap;
import cn.nesc.general.core.bean.VO;
import cn.nesc.general.core.annotation.EnumHandler;
import cn.nesc.general.core.annotation.ParaDesc;
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
     * @param vo
     */
    public static void getDescByCode(VO vo)
    {
        Field[] fields = vo.getClass().getDeclaredFields();
        Arrays.stream(fields).forEach(field -> {
            ParaDesc annotation = field.getAnnotation(ParaDesc.class);
            if (null != annotation)
            {
                String codeField = annotation.value();
//                log.debug("codeField ----->" + codeField);
                Object codeValue = Reflections.invokeGetter(vo, codeField);
                if (codeField instanceof String)
                {
//                    log.debug("codeValue ----->" + codeValue);
                    SystemParasMap systemParasMap = SystemParasMap.getInstance();
                    String desc = systemParasMap.getParasMap().get(codeValue);
                    if (StringUtils.isNotEmpty(desc))
                    {
                        Reflections.invokeSetter(vo, field.getName() ,desc);
                    }
                }
            }

        });
    }

    /**
     *  自动回写bo中数据字典的描述信息(枚举)
     * @param vo
     */
    public static void enumHandle(VO vo)
    {
        Field[] fields = vo.getClass().getDeclaredFields();
        Arrays.stream(fields).forEach(field -> {
            EnumHandler annotation = field.getAnnotation(EnumHandler.class);
            if (null != annotation)
            {
                Class<? extends BaseEnum> baseEnum = annotation.value();
                Object codeValue = Reflections.invokeGetter(vo, field.getName());
                if (null != codeValue)
                {
                    String desc = EnumUtil.getEnumMessage("" + codeValue, baseEnum);
                    if (StringUtils.isNotEmpty(desc))
                    {
                        Reflections.invokeSetter(vo, field.getName() ,desc);
                    }
                }
            }

        });
    }

    /**
     * 批量自动回写bo中数据字典的描述信息
     * @param voList
     */
    public static void getDescByCode(List<? extends VO> voList)
    {
        voList.stream().forEach(vo -> getDescByCode(vo));
    }

    /**
     *  批量自动回写bo中数据字典的描述信息(枚举)
     * @param voList
     */
    public static void enumHandle(List<? extends VO> voList)
    {
        voList.stream().forEach(vo -> enumHandle(vo));
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

    public static List copyProperties(List origin, Class<? extends VO> clz)
    {
        List<? extends VO> voList = new ArrayList<>();

        voList = (List<? extends VO>) origin.stream().map(t -> {
            VO r = null;
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
        return voList;
    }
}
