package cn.nesc.general.passage.filter;

import cn.nesc.general.core.nosql.RedisUtil;
import org.apache.http.HttpHeaders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * @ClassName AuthFilter
 * @Description
 * @Author summer
 * @Date 2023/12/27 15:59
 **/
@Component
public class AuthFilter implements WebFilter, Ordered
{

    private Logger logger = LoggerFactory.getLogger(AuthFilter.class);

    @Value("${gateway.request.whitelist}")
    private Set<String> whiteList;

    @Resource
    private RedisUtil redisUtil;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain)
    {
        logger.debug("[AuthFilter] Request URI: " + exchange.getRequest().getPath());
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();
        // 白名单不进行验证
        if (whiteList.contains(request.getPath().value()))
        {
            return chain.filter(exchange);
        }
        else if(request.getMethod().equals(HttpMethod.OPTIONS))
        {
            logger.debug("Request method is options, through the filter.");
            return chain.filter(exchange);
        }
        else
        {
            List<String> sidHeaders = request.getHeaders().getOrEmpty("sid");
            String token = sidHeaders.stream().findFirst().isPresent() ? sidHeaders.get(0) : null;
            if (token == null || token.isEmpty())
            {
                List<String> tokenQuerys = request.getQueryParams().get("token");
                token = tokenQuerys.stream().findFirst().isPresent() ? tokenQuerys.get(0) : null;
            }
            if (token == null || token.isEmpty())
            {
                unAuthResponse(exchange, response, "您无权限访问系统, Token不能为空");
                return chain.filter(exchange);
            }
            boolean isValidate = redisUtil.exists(token);
            logger.debug("isValidate  ------->" + isValidate);
            if (!isValidate)
            {
                unAuthResponse(exchange, response, "您无权限访问系统, Token无效或已过期");
                return chain.filter(exchange);
            }
            return chain.filter(exchange);
        }

    }

    @Override
    public int getOrder()
    {
        return 0;
    }

    /**
     * @return void
     * @Author summer
     * @Description 返回未认证response
     * @Date 16:24 2022/2/11
     * @Param [ctx]
     **/
    private void unAuthResponse(ServerWebExchange exchange, ServerHttpResponse response, String msg)
    {
        response.getHeaders().put(HttpHeaders.CONTENT_TYPE, Collections.singletonList(MediaType.APPLICATION_JSON_UTF8_VALUE));
        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        String responseBody = "{\"code\":\"401\",\"msg\":\"" + msg + "\",\"data\":null}";
        DataBuffer dataBuffer = response.bufferFactory().wrap(responseBody.getBytes(StandardCharsets.UTF_8));
        response.writeWith(Mono.just(dataBuffer)).subscribe();
        exchange.mutate().response(response).build();
    }
}
