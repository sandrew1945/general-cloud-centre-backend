package cn.nesc.general.authcenter.interceptor;


import cn.nesc.general.common.bean.PageResult;
import cn.nesc.general.common.bean.VO;
import cn.nesc.general.common.utils.MagicOOO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * Created by summer on 2020/2/20.
 */
@Slf4j
@Aspect
@Component
public class FixcodeInterpretAdvice
{

    //表示匹配带有自定义注解的方法
    @Pointcut("execution(* cn.nesc.general.authcenter.controller..*.*(..))")
    public void pointcut()
    {
    }


    @AfterReturning(value = "pointcut()", returning = "retVal")
    public void handleReturn(JoinPoint point, Object retVal)
    {
        try
        {
            if (retVal instanceof VO)
            {
                MagicOOO.enumHandle((VO) retVal);
            }
            else if (retVal instanceof List)
            {
                List list = (List) retVal;
                if (CollectionUtils.isNotEmpty(list))
                {
                    Object ele = list.get(0);
                    if (ele instanceof VO)
                    {
                        MagicOOO.enumHandle(list);
                    }
                }
            }
            else if (retVal instanceof PageResult)
            {
                PageResult result = (PageResult) retVal;
                List list = result.getRecords();
                if (CollectionUtils.isNotEmpty(list))
                {
                    Object ele = list.get(0);
                    if (ele instanceof VO)
                    {
                        MagicOOO.enumHandle(list);
                    }
                }
            }
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
        }
    }


//    @Before(value = "pointcut()")
//    public void handle(JoinPoint point)
//    {
//        Object[] args = point.getArgs();
//        if (args != null && args.length > 0)
//        {
//            try
//            {
//                for (int i = 0; i < args.length; i++)
//                {
//                    if (args[i] instanceof VO)
//                    {
//                        MagicOOO.enumRequestHandle((VO) args[i]);
//                    }
//                    else if (args[i] instanceof List)
//                    {
//                        List list = (List) args[i];
//                        if (CollectionUtils.isNotEmpty(list))
//                        {
//                            Object ele = list.get(0);
//                            if (ele instanceof VO)
//                            {
//                                MagicOOO.enumRequestHandle(list);
//                            }
//                        }
//                    }
//                    else if (args[i] instanceof PageResult)
//                    {
//                        PageResult result = (PageResult) args[i];
//                        List list = result.getRecords();
//                        if (CollectionUtils.isNotEmpty(list))
//                        {
//                            Object ele = list.get(0);
//                            if (ele instanceof BO)
//                            {
//                                MagicOOO.enumRequestHandle(list);
//                            }
//                        }
//                    }
//                }
//            }
//            catch (Exception e)
//            {
//                log.error(e.getMessage(), e);
//            }
//        }
//    }

}
