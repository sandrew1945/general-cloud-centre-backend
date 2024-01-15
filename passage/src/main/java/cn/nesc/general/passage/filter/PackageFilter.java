package cn.nesc.general.passage.filter;

import cn.nesc.general.core.result.JsonResult;
import cn.nesc.toolkit.common.json.JsonUtil;
import com.fasterxml.jackson.databind.JsonNode;
import org.reactivestreams.Publisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.core.io.buffer.DefaultDataBuffer;
import org.springframework.core.io.buffer.DefaultDataBufferFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.http.server.reactive.ServerHttpResponseDecorator;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

import static org.springframework.http.HttpStatus.*;

/**
 * @ClassName PackageFilter
 * @Description
 * @Author summer
 * @Date 2023/12/28 13:31
 **/
@Component
    public class PackageFilter implements WebFilter, Ordered
{
    private Logger logger = LoggerFactory.getLogger(PackageFilter.class);

    @Value("${gateway.response.package.switch}")
    private boolean bodyPackage;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain)
    {
        logger.debug("[AuthFilter] Request URI: " + exchange.getRequest().getPath());
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();
        Integer statusCode = response.getRawStatusCode();
        DataBufferFactory dataBufferFactory = response.bufferFactory();
        // 判断是否拦截

        if (302 == statusCode)
        {
            return chain.filter(exchange);
        }
        else if (response.getHeaders().getOrEmpty("Content-Type").contains("application/octet-stream"))
        {
            return chain.filter(exchange);
        }

        ServerHttpResponseDecorator decorator = getDecoratedResponse(exchange.getRequest().getPath().value(), response, request, dataBufferFactory);
        return chain.filter(exchange.mutate().response(decorator).build());
    }

    @Override
    public int getOrder()
    {
        return 1;
    }

//    @Override
//    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain)
//    {
//        logger.debug("[AuthFilter] Request URI: " + exchange.getRequest().getPath());
//        ServerHttpRequest request = exchange.getRequest();
//        ServerHttpResponse response = exchange.getResponse();
//        Integer statusCode = response.getRawStatusCode();
//        DataBufferFactory dataBufferFactory = response.bufferFactory();
//        String originalResponseContentType = exchange.getAttribute(ServerWebExchangeUtils.ORIGINAL_RESPONSE_CONTENT_TYPE_ATTR);
//        // 判断是否拦截
//
//        if (302 == statusCode)
//        {
//            return chain.filter(exchange);
//        }
//        else if (response.getHeaders().getOrEmpty("Content-Type").contains("application/octet-stream"))
//        {
//            return chain.filter(exchange);
//        }
//        ServerHttpResponseDecorator decorator = getDecoratedResponse(exchange.getRequest().getPath().value(), response, request, dataBufferFactory);
//        return chain.filter(exchange.mutate().response(decorator).build());
//    }
    private ServerHttpResponseDecorator getDecoratedResponse(String path, ServerHttpResponse response, ServerHttpRequest request, DataBufferFactory dataBufferFactory)
    {
        return new ServerHttpResponseDecorator(response)
        {
            @Override
            public Mono<Void> writeWith(final Publisher<? extends DataBuffer> body)
            {
                if (body instanceof Flux)
                {
                    Flux<? extends DataBuffer> fluxBody = (Flux<? extends DataBuffer>) body;
                    return super.writeWith(fluxBody.buffer().map(dataBuffers -> {
                        DefaultDataBuffer joinedBuffers = new DefaultDataBufferFactory().join(dataBuffers);
                        byte[] content = new byte[joinedBuffers.readableByteCount()];
                        joinedBuffers.read(content);
                        // 下载文件，直接返回body
                        if (response.getHeaders().getOrEmpty("Content-Type").stream().filter(contentType -> contentType.startsWith("application/octet-stream")).findAny().isPresent())
                        {
                            return dataBufferFactory.wrap(content);
                        }
                        String responseBody = new String(content, StandardCharsets.UTF_8);//MODIFY RESPONSE and Return the Modified response
                        logger.debug("requestId: {}, method: {}, url: {}, \nresponse body :{}", request.getId(), request.getMethodValue(), request.getURI(), responseBody);
                        Integer statusCode = response.getRawStatusCode();
                        // 分条件拦截
                        if (content == null || content.length == 0)
                        {
                            logger.debug("response body: {}", "null");
                            return dataBufferFactory.wrap(content);
                        }
                        else if (OK.value() == statusCode)
                        {
                            // 请求正常
                            if (!bodyPackage)
                            {
                                // 根据设置不需要对结果进行包装
                                return dataBufferFactory.wrap(content);
                            }
                            // 验证返回结果是否为json格式，如果controller返回为对象则会序列化为json格式，如果返回基本类型咋不会被序列化为json格式
                            Object obj = JsonUtil.validateJsonStr(responseBody) ? JsonUtil.string2JavaObject(responseBody, Object.class) : responseBody;
                            // 变成JsonResult格式
                            JsonResult result = new JsonResult();
                            result.requestSuccess(obj);
                            return dataBufferFactory.wrap(JsonUtil.javaObject2String(result).getBytes());
                        }
                        else if (NOT_FOUND.value() == statusCode)
                        {
                            // 请求异常
                            // 变成JsonResult格式
                            JsonResult result = new JsonResult();
                            result.requestFailure("Path " + request.getPath().value() + " Not Found");
                            response.setStatusCode(OK);
                            return dataBufferFactory.wrap(JsonUtil.javaObject2String(result).getBytes());
                        }
                        else if (FORBIDDEN.value() == statusCode)
                        {
                            // 请求异常
                            // 变成JsonResult格式
                            JsonResult result = new JsonResult();
                            result.requestFailure("Path " + request.getPath().value() + " is Forbidden");
                            response.setStatusCode(OK);
                            return dataBufferFactory.wrap(JsonUtil.javaObject2String(result).getBytes());
                        }
                        else if (BAD_REQUEST.value() == statusCode)
                        {
                            // 请求异常
                            // 变成JsonResult格式
                            JsonResult result = new JsonResult();
                            result.requestFailure("Path " + request.getPath().value() + " is Bad Request");
                            response.setStatusCode(OK);
                            return dataBufferFactory.wrap(JsonUtil.javaObject2String(result).getBytes());
                        }
                        else
                        {
                            // 请求异常
                            if (JsonUtil.validateJsonStr(responseBody))
                            {
                                // 变成JsonResult格式
                                JsonNode node = JsonUtil.string2JsonObject(responseBody);
                                JsonResult result = new JsonResult();
                                result.requestFailure(node.get("error").asText("Internal Server Error"));
                                response.setStatusCode(OK);
                                return dataBufferFactory.wrap(JsonUtil.javaObject2String(result).getBytes());
                            }
                            else
                            {
                                return dataBufferFactory.wrap(responseBody.getBytes());
                            }
                        }
                    })).onErrorResume(err -> {
                        logger.error("error while decorating Response: {}", err.getMessage());
                        return Mono.empty();
                    });

                }
                return super.writeWith(body);
            }
        };
    }
}
