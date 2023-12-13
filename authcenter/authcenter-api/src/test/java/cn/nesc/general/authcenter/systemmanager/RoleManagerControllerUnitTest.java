package cn.nesc.general.authcenter.systemmanager;

import cn.nesc.general.authcenter.InitForUnitTest;
import cn.nesc.general.authcenter.bean.FunctionsParam;
import cn.nesc.general.common.dictionary.Constants;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;

import java.util.*;

/**
 * @ClassName UserManagerControllerUnitTest
 * @Description  webEnvironment：MOCK, 启动一个模拟的 Servlet 环境，这是默认值
 *                              RANDOM_PORT，启动一个 Tomcat 容器，并监听一个随机的端口号
 *                              DEFINED_PORT，启动一个 Tomcat 容器，并监听配置文件中定义的端口（未定义则默认监听8080）
 *                              NONE，不启动 Tomcat 容器
 * @Author summer
 * @Date 2023/11/1 10:54
 **/

public class RoleManagerControllerUnitTest extends InitForUnitTest
{

    @Test
    @DisplayName("角色分页查询Controller测试")
    public void roleManagerPageQueryControllerTestProcess() throws Exception
    {
        Map<String, String> params = new HashMap<>();
        params.put("roleCode", null);
        params.put("roleName", null);
        params.put("roleStatus", null);
        params.put("limit", "20");
        params.put("curPage", "1");

        restRequest("/rolemanager/roleManagerPageQuery", HttpMethod.POST, heads, params, statusOK(), contentTypeOK(MediaType.APPLICATION_JSON_VALUE), jsonContentHasArray("$.records"));
    }

    @Test
    @DisplayName("获取角色信息")
    public void getRoleInfoByIdControllerTestProcess() throws Exception
    {
        Map<String, String> params = new HashMap<>();
        params.put("roleId", "1");
        restRequest("/rolemanager/getRoleInfoById", HttpMethod.GET, heads, params, statusOK(), jsonContentOK("$.roleCode", "r_admin"));
    }


    @Test
    @DisplayName("创建角色")
    public void createRoleControllerTestProcess() throws Exception
    {
        Map<String, String> params = new HashMap<>();

        params.put("roleCode", "testRole");
        params.put("roleName", "ceshijuese");
        params.put("roleStatus", "" + Constants.STATUS_ENABLE);
        params.put("isDelete", "" + Constants.IF_TYPE_NO);
        restRequest("/rolemanager/createRole", HttpMethod.POST, heads, params, statusOK(), primitiveContentOK("true"));
    }

    @Test
    @DisplayName("更新角色信息")
    public void updateRoleControllerTestProcess() throws Exception
    {
        Map<String, String> params = new HashMap<>();
        params.put("roleId", "3");
        params.put("roleCode", "testRole2");
        params.put("roleName", "ceshijuese2");
        params.put("roleStatus", "" + Constants.STATUS_ENABLE);
        params.put("isDelete", "" + Constants.IF_TYPE_NO);
        restRequest("/rolemanager/updateRole", HttpMethod.POST, heads, params, statusOK(), primitiveContentOK("true"));
    }

    @Test
    @DisplayName("删除角色")
    public void deleteRoleControllerTestProcess() throws Exception
    {
        Map<String, String> params = new HashMap<>();
        params.put("roleId", "3");

        restRequest("/rolemanager/deleteRole", HttpMethod.POST, heads, params, statusOK(), primitiveContentOK("true"));
    }


    @Test
    @DisplayName("保存角色权限")
    public void saveSelectedFuncControllerTestProcess() throws Exception
    {
        FunctionsParam param = new FunctionsParam();
        param.setRoleId(3);
        param.setFunctionIds(Arrays.asList(new Integer[] {3, 4}));

        restRequest("/rolemanager/saveSelectedFunc", HttpMethod.POST, heads, param, statusOK(), primitiveContentOK("true"));
    }

    @Test
    @DisplayName("查询角色已选菜单")
    public void getCheckedPremissionControllerTestProcess() throws Exception
    {
        Map<String, String> params = new HashMap<>();
        params.put("roleId", "1");
        List<Integer> expectedVal = new ArrayList<>();
        expectedVal.add(1);
        expectedVal.add(2);
        expectedVal.add(3);
        expectedVal.add(4);
        expectedVal.add(5);
        restRequest("/rolemanager/getCheckedPremission", HttpMethod.GET, heads, params, statusOK(), jsonContentHasArray("$"), jsonContentOK("$", expectedVal));
    }

    @Test
    @DisplayName("验证角色代码重复")
    public void roleValidateControllerTestProcess() throws Exception
    {
        Map<String, String> params = new HashMap<>();
        params.put("roleCode", "r_admin");
        restRequest("/rolemanager/roleValidate", HttpMethod.GET, heads, params, primitiveContentOK("false"));
    }
}
