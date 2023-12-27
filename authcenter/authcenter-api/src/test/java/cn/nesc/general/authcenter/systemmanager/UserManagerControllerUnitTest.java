package cn.nesc.general.authcenter.systemmanager;

import cn.nesc.general.authcenter.InitForUnitTest;
import cn.nesc.general.authcenter.bean.usermanager.UserManagerDTO;
import cn.nesc.general.authcenter.dictionary.Constants;
import cn.nesc.general.core.exception.JsonException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;

import java.util.Date;
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

public class UserManagerControllerUnitTest extends InitForUnitTest
{

    @Test
    @DisplayName("用户分页查询Controller测试")
    public void userManagerPageQueryControllerTestProcess() throws Exception
    {
        Map<String, String> params = new HashMap<>();
        params.put("userCode", null);
        params.put("userName", null);
        params.put("userStatus", null);
        params.put("limit", "20");
        params.put("curPage", "1");

        restRequest("/usermanager/userManagerPageQuery", HttpMethod.POST, heads, params, statusOK(), contentTypeOK(MediaType.APPLICATION_JSON_VALUE), jsonContentHasArray("$.records"));
    }


    @Test
    @DisplayName("查询用户详情")
    public void getUserInfoByIdControllerTestProcess() throws Exception
    {
        Map<String, String> params = new HashMap<>();
        params.put("userId", "1");
        restRequest("/usermanager/getUserInfoById", HttpMethod.GET, heads, params, statusOK(), contentTypeOK(MediaType.APPLICATION_JSON_VALUE), jsonContentOK("$.userCode", "admin"));

    }

    @Test
    @DisplayName("创建用户")
    public void createUserInfoControllerProcess() throws Exception
    {
        Map<String, String> params = new HashMap<>();
        params.put("userCode","test");
        params.put("userName","测试用户");
        params.put("password","123456");
        params.put("email","test@nesc.cn");
        restRequest("/usermanager/createUserInfo", HttpMethod.POST, heads, params, statusOK(), contentTypeOK(MediaType.APPLICATION_JSON_VALUE), primitiveContentOK("true"));

    }

    @Test
    @DisplayName("创建用户-json body")
    public void createUserInfoForJsonBodyControllerProcess() throws Exception
    {
        UserManagerDTO user = new UserManagerDTO();
        user.setUserCode("test2");
        user.setUserName("测试用户2");
        user.setPassword("123456");
        user.setSex(Constants.SEX_FEMALE);
        user.setEmail("test@nesc.cn");
        user.setBirthday(new Date());
        user.setAvatar("/avatar.jpg");
        user.setUserStatus(Constants.STATUS_ENABLE);
        restRequest("/usermanager/createUserInfoForJsonBody", HttpMethod.POST, heads, user, statusOK(), primitiveContentOK("true"));

    }

    @Test
    @DisplayName("更新用户")
    public void updateUserInfoControllerTestProcess() throws Exception
    {
        Map<String, String> params = new HashMap<>();
        params.put("userId","1");
        params.put("userName","admin123");
        params.put("password","654321");
        restRequest("/usermanager/updateUserInfo", HttpMethod.POST, heads, params, statusOK(), primitiveContentOK("true"));

    }

    @Test
    @DisplayName("删除用户")
    public void deleteUserInfoControllerTestProcess() throws Exception
    {
        Map<String, String> params = new HashMap<>();
        params.put("userId","3");
        restRequest("/usermanager/deleteUserInfo", HttpMethod.POST, heads, params, statusOK(), primitiveContentOK("true"));
    }

    @Test
    @DisplayName("删除用户-pathVariable")
    public void deleteUserInfoByPathVariableControllerTestProcess() throws Exception
    {
        UriWithPathVariable uriWithPathVariable = new UriWithPathVariable("/usermanager/deleteUserInfo/{userId}", "3");
        restRequest(uriWithPathVariable, HttpMethod.DELETE, heads, statusOK(), primitiveContentOK("true"));
    }

    @Test
    @DisplayName("查询用户关联角色")
    public void queryRelationRolesControllerTestProcess() throws Exception
    {
        Map<String, String> params = new HashMap<>();
        params.put("userId","1");
        restRequest("/usermanager/queryRelationRoles", HttpMethod.GET, heads, params, statusOK(), jsonContentHasArray("$"));
    }

    @Test
    @DisplayName("删除用户关联角色")
    public void deleteRoleRelationControllerProcess() throws Exception
    {
        Map<String, String> params = new HashMap<>();
        params.put("userId","1");
        params.put("roleId","1");
        restRequest("/usermanager/deleteRoleRelation", HttpMethod.POST, heads, params, statusOK(), primitiveContentOK("true"));

        params.put("roleId","10");
        restRequest("/usermanager/deleteRoleRelation", HttpMethod.POST, heads, params, statusOK(), primitiveContentOK("false"));
    }

    @Test
    @DisplayName("查询用户未关联角色")
    public void queryUnRelationRolesControllerTestProcess() throws Exception
    {
        Map<String, String> params = new HashMap<>();
        params.put("userId","1");
        params.put("roleName","");
        restRequest("/usermanager/queryUnRelationRoles", HttpMethod.POST, heads, params, statusOK(), jsonContentOK("$.[0].roleName", "系统管理员"));
    }

//    @Rule
//    public final ExpectedException exception = ExpectedException.none();

    @Test
    @DisplayName("给用户添加角色")
    public void createRelationControllerTestProcess() throws Exception
    {
        Map<String, String> params = new HashMap<>();
        params.put("userId","1");
        params.put("rolesStr","3,4");
//        exception.expect(JsonException.class);

//        restRequest("/usermanager/createRelation", HttpMethod.POST, heads, params, new ResultMatcher() {
//            @Override
//            public void match(MvcResult mvcResult) throws Exception
//            {
//                System.out.println("Exception type : " + mvcResult.getResolvedException().getClass().getName());
//                assertTrue(mvcResult.getResolvedException() instanceof JsonException);
//            }
//        });
        restRequest("/usermanager/createRelation", HttpMethod.POST, heads, params, throwException(JsonException.class));
    }

    @Test
    @DisplayName("查询用户列表")
    public void getUserListControllerTestProcess() throws Exception
    {
        restRequest("/usermanager/getUserList", HttpMethod.GET, heads, null, statusOK());
    }

    @Test
    @DisplayName("修改用户密码")
    public void updatePasswordControllerTestProcess() throws Exception
    {
        Map<String, String> params = new HashMap<>();
        params.put("userId","1");
        params.put("originPwd","1234565");
        params.put("newPwd","654321");
        restRequest("/usermanager/updatePassword", HttpMethod.POST, heads, params, throwException(JsonException.class));
    }
}
