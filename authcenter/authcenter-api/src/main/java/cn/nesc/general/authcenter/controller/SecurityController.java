package cn.nesc.general.authcenter.controller;


import cn.nesc.general.core.bean.AclUserBean;
import cn.nesc.general.core.exception.JsonException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Slf4j
@RequestMapping("/security")
public class SecurityController extends BaseController
{

    @GetMapping(value = "/getLoginUser")
    public AclUserBean getLoginUserInfo() throws JsonException
    {
        try
        {
            AclUserBean loginUser = getLoginUser();
//            System.out.println(1/0);
            return loginUser;
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
            throw new JsonException("获取用户信息失败", e);
        }
    }

}
