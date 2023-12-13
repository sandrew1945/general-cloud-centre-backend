package cn.nesc.general.authcenter.bean.login;

import lombok.Data;

/**
 * @ClassName LoginBO
 * @Description
 * @Author summer
 * @Date 2023/3/1 13:52
 **/
@Data
public class LoginVO
{
    private Integer userId;

    private String token;
}
