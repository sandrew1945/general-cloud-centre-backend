package cn.nesc.general.stub.authcenter;

import cn.nesc.general.authcenter.model.TmUserVO;
import cn.nesc.general.core.bean.AclUserBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

//@Component
@FeignClient(value = "AUTH")
public interface AuthCenterStub
{

    /**
     * @Author summer
     * @Description token验证接口
     * @Date 16:38 2022/2/11
     * @Param []
     * @return java.lang.String
     **/
    @RequestMapping(value = "/validateToken")
    boolean validateToken(@RequestParam("token") String token);


    @RequestMapping(value = "/security/getLoginUser")
    AclUserBean getLoginUser();

    @RequestMapping(value = "/usermanager/getUserInfoById", method = RequestMethod.GET)
    TmUserVO getUserInfoById(@RequestParam("userId") Integer userId);
}
