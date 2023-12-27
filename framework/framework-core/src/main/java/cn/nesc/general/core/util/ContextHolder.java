package cn.nesc.general.core.util;

import org.apache.commons.lang3.StringUtils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName ContextHolder
 * @Description
 * @Author summer
 * @Date 2023/3/13 10:57
 **/
public class ContextHolder
{
    private static final ThreadLocal<Map<String, Object>> LOCAL = new ThreadLocal<Map<String, Object>>();

    public static void set(String key, Object value)
    {
        Map<String, Object> localData = getLocalData();
        localData.put(key, value);
    }

    public static Object get(String key)
    {
        if (StringUtils.isEmpty(key))
        {
            return null;
        }
        Map<String, Object> localData = getLocalData();
        return localData.get(key);
    }

    public static <T> T get(String key, Class<T> clz)
    {
        T value = null;
        if (StringUtils.isEmpty(key))
        {
            return null;
        }
        Map<String, Object> localData = getLocalData();
        if (null != localData.get(key) && localData.get(key).getClass().toString().equals(clz.toString()))
        {
            value =  (T)localData.get(key);
        }
        return value;
    }

    public static void remove()
    {
        LOCAL.remove();
    }

    private synchronized static Map<String, Object> getLocalData()
    {
        Map<String, Object> localMap = LOCAL.get();
        if (null == localMap)
        {
            localMap = new ConcurrentHashMap<>();
            LOCAL.set(localMap);
        }
        return localMap;
    }
}
