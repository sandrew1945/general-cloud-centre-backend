package cn.nesc.general.core.bean;

import java.util.HashMap;
import java.util.Map;

public class SystemParasMap
{
    private static volatile SystemParasMap instance;

    private Map<Integer, String> parasMap = new HashMap<>();

    private SystemParasMap()
    {
    }

    public static SystemParasMap getInstance()
    {
        if (null == instance)
        {
            synchronized (SystemParasMap.class)
            {
                if (null == instance)
                {
                    instance = new SystemParasMap();
                }
            }
        }
        return instance;
    }

    public Map<Integer, String> getParasMap()
    {
        return parasMap;
    }

    public void setParasMap(Map<Integer, String> parasMap)
    {
        this.parasMap = parasMap;
    }
}
