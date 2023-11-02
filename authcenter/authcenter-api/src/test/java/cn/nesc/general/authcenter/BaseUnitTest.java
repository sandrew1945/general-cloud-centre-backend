/**
 * Copyright (C), 2015-2023, 东北证券股份有限公司
 * FileName: BaseUnitTest
 * Author:   summer
 * Date:     2023/11/1 15:18
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 **/

package cn.nesc.general.authcenter;

import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.MultiValueMap;

import javax.annotation.Resource;
import java.util.Map;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @ClassName BaseUnitTest
 * @Description 单元测试基础类，业务不相干
 *              其他全部测试都继承该类，提供了基本的测试及断言方法
 * @Author summer
 * @Date 2023/11/1 15:18
 **/
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class BaseUnitTest
{
    @Resource
    protected MockMvc mockMvc;


    /**
     * @Author summer
     * @Description 模拟发起请求
     * @Date 16:15 2023/11/1
     * @Param uri           请求uri
     * @Param httpMethod    请求方式
     * @Param heads         请求头信息
     * @Param params        请求参数
     * @Param matcher       断言比较器
     * @return void
     **/
    protected void restRequest(String uri, HttpMethod httpMethod, MultiValueMap heads, Map<String, String> params, ResultMatcher... matcher) throws Exception
    {
        RequestBuilder builder = null;
        switch (httpMethod)
        {
            case GET:
                MockHttpServletRequestBuilder getBulider = MockMvcRequestBuilders.get(uri);
                if (null != heads)
                {
                    getBulider.headers(new HttpHeaders(heads));
                }
                if (null != params)
                {
                    params.entrySet().stream().forEach(entry -> getBulider.param(entry.getKey(), entry.getValue()));
                }
                builder = getBulider;
                break;
            case POST:
                MockHttpServletRequestBuilder postBulider = MockMvcRequestBuilders.post(uri);
                if (null != heads)
                {
                    postBulider.headers(new HttpHeaders(heads));
                }
                if (null != params)
                {
                    params.entrySet().stream().forEach(entry -> postBulider.param(entry.getKey(), entry.getValue()));
                }
                builder = postBulider;
                break;
            case PUT:
                MockHttpServletRequestBuilder putBulider = MockMvcRequestBuilders.put(uri);
                if (null != heads)
                {
                    putBulider.headers(new HttpHeaders(heads));
                }
                if (null != params)
                {
                    params.entrySet().stream().forEach(entry -> putBulider.param(entry.getKey(), entry.getValue()));
                }
                builder = putBulider;
                break;
            case DELETE:
                MockHttpServletRequestBuilder delBulider = MockMvcRequestBuilders.delete(uri);
                if (null != heads)
                {
                    delBulider.headers(new HttpHeaders(heads));
                }
                if (null != params)
                {
                    params.entrySet().stream().forEach(entry -> delBulider.param(entry.getKey(), entry.getValue()));
                }
                builder = delBulider;
                break;
            default:
                throw new Exception("不支持的请求类型");
        }
        ResultActions actions = mockMvc.perform(builder).andDo(print());
        for (ResultMatcher resultMatcher : matcher)
        {
            actions.andExpect(resultMatcher);
        }
    }

    /**
     * @Author summer
     * @Description 返回状态正常Matcher
     * @Date 15:53 2023/11/1
     * @Param []
     * @return org.springframework.test.web.servlet.ResultMatcher
     **/
    protected ResultMatcher statusOK()
    {
        return status().isOk();
    }

    /**
     * @Author summer
     * @Description 返回contentType正确Matcher
     * @Date 15:54 2023/11/1
     * @Param [contentType]
     * @return org.springframework.test.web.servlet.ResultMatcher
     **/
    protected ResultMatcher contentTypeOK(String contentType)
    {
        return content().contentType(contentType);
    }

    /**
     * @Author summer
     * @Description 返回的json节点符合预期
     * @Date 15:57 2023/11/1
     * @Param [path, expectValue]
     * @return org.springframework.test.web.servlet.ResultMatcher
     **/
    protected ResultMatcher jsonContentOK(String path, String expectValue)
    {
        return jsonPath(path).value(expectValue);
    }

    /**
     * @Author summer
     * @Description 返回的json节点值不为空
     * @Date 15:59 2023/11/1
     * @Param [path]
     * @return org.springframework.test.web.servlet.ResultMatcher
     **/
    protected ResultMatcher jsonContentNotEmpty(String path)
    {
        return jsonPath(path).isNotEmpty();
    }

    /**
     * @Author summer
     * @Description 返回的json存在某节点
     * @Date 16:00 2023/11/1
     * @Param [path]
     * @return org.springframework.test.web.servlet.ResultMatcher
     **/
    protected ResultMatcher jsonContentExist(String path)
    {
        return jsonPath(path).exists();
    }

    /**
     * @Author summer
     * @Description 返回的json存在某节点为数组
     * @Date 16:02 2023/11/1
     * @Param [path]
     * @return org.springframework.test.web.servlet.ResultMatcher
     **/
    protected ResultMatcher jsonContentHasArray(String path)
    {
        return jsonPath(path).isArray();
    }




}
