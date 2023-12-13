package cn.nesc.general.authcenter.login;

import cn.nesc.general.authcenter.InitForUnitTest;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName LoginControllerUnitTest
 * @Description  webEnvironment：MOCK, 启动一个模拟的 Servlet 环境，这是默认值
 *                              RANDOM_PORT，启动一个 Tomcat 容器，并监听一个随机的端口号
 *                              DEFINED_PORT，启动一个 Tomcat 容器，并监听配置文件中定义的端口（未定义则默认监听8080）
 *                              NONE，不启动 Tomcat 容器
 * @Author summer
 * @Date 2023/11/1 10:54
 **/

public class LoginControllerUnitTest extends InitForUnitTest
{

    @Test
    @DisplayName("登录Controller测试")
    public void loginControllerTestProcess() throws Exception
    {

//        mockMvc.perform(MockMvcRequestBuilders.post("/login").param("userCode", "admin").param("password", "123456")
//                .accept(MediaType.APPLICATION_JSON_VALUE))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
//                .andExpect(jsonPath("$.token").isNotEmpty());
        Map<String, String> params = new HashMap<>();
        params.put("userCode", "admin");
        params.put("password", "123456");

        restRequest("/login", HttpMethod.POST, null, params, statusOK(), contentTypeOK(MediaType.APPLICATION_JSON_VALUE), jsonContentNotEmpty("$.token"));
    }

    @Test
    @DisplayName("获取用户信息Controller测试")
    public void getUserInfoControllerTestProcess() throws Exception
    {
        restRequest("/userInfo", HttpMethod.GET, heads, null, statusOK(), jsonContentOK("$.userCode", "admin"));
    }

    @Test
    @DisplayName("获取用户菜单Controller测试")
    public void getMenuByRoleControllerTestProcess() throws Exception
    {

        Map<String, String> params = new HashMap<>();
        params.put("roleId", "1");
        restRequest("/getMenuByRole", HttpMethod.GET, heads, params, statusOK(), jsonContentHasArray("$"));
    }



}
