package cn.nesc.general.authcenter.systemmanager;

import cn.nesc.general.authcenter.InitForUnitTest;
import cn.nesc.general.core.exception.JsonException;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.http.HttpMethod;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName UserManagerControllerUnitTest
 * @Description  webEnvironment：MOCK, 启动一个模拟的 Servlet 环境，这是默认值
 *                              RANDOM_PORT，启动一个 Tomcat 容器，并监听一个随机的端口号
 *                              DEFINED_PORT，启动一个 Tomcat 容器，并监听配置文件中定义的端口（未定义则默认监听8080）
 *                              NONE，不启动 Tomcat 容器
 * @Author summer
 * @Date 2023/11/1 10:54
 **/

public class MenuManagerControllerUnitTest extends InitForUnitTest
{
    @Test
    @DisplayName("查询菜单")
    public void getMenuTreeControllerTestProcess() throws Exception
    {
        restRequest("/menumanager/getMenuTree", HttpMethod.GET, heads, null, statusOK(), jsonContentOK("$.[0].name", "首页"));
    }

    @Test
    @DisplayName("创建菜单")
    public void createMenuControllerTestProcess() throws Exception
    {
        Map<String, String> params = new HashMap<>();
        params.put("path", "/test");
        params.put("name", "测试紫菜蛋");
        params.put("icon", "dashboard");
        params.put("funcOrder", "0001");
        params.put("fatherId", "5");
        restRequest("/menumanager/createMenu", HttpMethod.POST, heads, params, statusOK(), primitiveContentOK("true"));
        params.put("path", "/test");
        params.put("name", "测试紫菜蛋");
        params.put("icon", "dashboard");
        params.put("funcOrder", "0001");
        params.put("fatherId", "8");
        restRequest("/menumanager/createMenu", HttpMethod.POST, heads, params, statusOK(), throwException(JsonException.class));
    }


    @Test
    @DisplayName("更新菜单")
    public void updateMenuControllerTestProcess() throws Exception
    {
        Map<String, String> params = new HashMap<>();
        params.put("path", "/test");
        params.put("name", "测试紫菜蛋");
        params.put("icon", "dashboard");
        params.put("funcOrder", "0001");
        params.put("functionId", "1");
        restRequest("/menumanager/updateMenu", HttpMethod.POST, heads, params, statusOK(), primitiveContentOK("true"));
    }

    @Test
    @DisplayName("删除菜单")
    public void deleteMenuControllerTestProcess() throws Exception
    {
        Map<String, String> params = new HashMap<>();
        params.put("functionId", "1");
        restRequest("/menumanager/deleteMenu", HttpMethod.POST, heads, params, statusOK(), primitiveContentOK("true"));
    }
}
