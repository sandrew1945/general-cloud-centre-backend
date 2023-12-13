package cn.nesc.general.authcenter;

import cn.nesc.general.core.exception.BaseException;
import cn.nesc.general.core.result.JsonResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName MyTestExceptionHandlers
 * @Description
 * @Author summer
 * @Date 2023/11/7 16:43
 **/
@RestControllerAdvice
public class MyTestExceptionHandlers
{
    @ExceptionHandler(value = BaseException.class)
    public JsonResult errorActionHandler(HttpServletResponse response, Exception ex)
    {
        JsonResult result = new JsonResult();
        while (null != ex)
        {
            Throwable throwable = ex.getCause();
            if (null == throwable)
            {
                break;
            }
            ex = (Exception) throwable;
        }
        result.requestFailure(ex.getMessage());
        return result;
    }
}
