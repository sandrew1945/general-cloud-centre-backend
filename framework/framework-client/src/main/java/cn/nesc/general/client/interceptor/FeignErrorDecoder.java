
package cn.nesc.general.client.interceptor;

import cn.nesc.general.core.exception.FeignException;
import cn.nesc.toolkit.common.json.JsonUtil;
import com.fasterxml.jackson.databind.JsonNode;
import com.google.common.io.CharStreams;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @ClassName FeignExceptionDecoder
 * @Description
 * @Author summer
 * @Date 2023/3/22 14:32
 **/
@Configuration
@Slf4j
public class FeignErrorDecoder implements ErrorDecoder
{

    private ObjectFactory<HttpMessageConverters> messageConverters;

    public FeignErrorDecoder(ObjectFactory<HttpMessageConverters> messageConverters)
    {
        this.messageConverters = messageConverters;
    }

    @Override
    public Exception decode(String methodKey, Response response)
    {
        try
        {
            String responseData = CharStreams.toString(new InputStreamReader(response.body().asInputStream(), "UTF-8"));
            log.debug("responseData ==========>" + responseData);
            JsonNode responseNode = JsonUtil.string2JsonObject(responseData);
            if (null != responseNode && responseNode.get("status").asInt(0) != HttpStatus.OK.value())
            {
                return new FeignException("Invoke " + methodKey + " error, reason :" + responseNode.get("error").asText("Internal Server Error"));
            }
            return new FeignException("Remote service invoke failure");
        }
        catch (IOException e)
        {
            log.error(e.getMessage(), e);
            return new FeignException(e.getMessage(), e);
        }

    }
}
