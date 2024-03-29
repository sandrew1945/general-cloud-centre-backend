package cn.nesc.general.authcenter;

import cn.nesc.general.authcenter.bean.login.LoginBO;
import cn.nesc.general.authcenter.service.LoginService;
import cn.nesc.general.core.shiro.MyUsernamePasswordToken;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import javax.annotation.Resource;
import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;


/**
 * @ClassName InitForUnitTest
 * @Description     单元测试初始化，业务相干测试都继承该类以便进行初始化工作
 * @Author summer
 * @Date 2023/11/2 13:17
 **/
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = AuthcenterApplication.class)
@TestPropertySource(locations = "classpath:application.properties")
@ActiveProfiles("dev")
public class InitForUnitTest extends BaseUnitTest
{
    protected String token;

    protected MultiValueMap<String, String> heads;

    @Resource
    private LoginService loginService;

    @BeforeEach
    @Order(1)
    @DisplayName("初始化")
    public void initLoginTestProcess() throws Exception
    {
//        TmUserPO user = new TmUserPO();
//        user.setUserCode("admin");
//        user.setPassword("123456");
        MyUsernamePasswordToken usernamePasswordToken = new MyUsernamePasswordToken();
        usernamePasswordToken.setUsername("admin");
        usernamePasswordToken.setPassword("123456".toCharArray());
        LoginBO loginBO = loginService.login(usernamePasswordToken);
        System.out.println("init for token =========> " + loginBO.getToken());
        assertThat(loginBO.getToken(), notNullValue());
        token = loginBO.getToken();
        System.out.println("add token in header, token:" + token);
        heads = new LinkedMultiValueMap<>();
        heads.put("sid", Arrays.asList(new String[] {token}));

    }

}
