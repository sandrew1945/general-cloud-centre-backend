package cn.nesc.general.stub.authcenter;

import cn.nesc.general.authcenter.model.TmUserVO;
import cn.nesc.general.core.bean.AclUserBean;
import cn.nesc.general.core.exception.JsonException;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @GetMapping("getUserInfoById")
    TmUserVO getUserInfoById(Integer userId) throws JsonException;
}
