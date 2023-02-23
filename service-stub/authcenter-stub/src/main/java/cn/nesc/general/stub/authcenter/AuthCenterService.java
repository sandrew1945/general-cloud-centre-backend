package cn.nesc.general.stub.authcenter;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@FeignClient(value = "auth")
public interface AuthCenterService
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
}
