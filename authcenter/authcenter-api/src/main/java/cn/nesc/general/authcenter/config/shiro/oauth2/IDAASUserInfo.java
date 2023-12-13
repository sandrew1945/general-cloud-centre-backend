package cn.nesc.general.authcenter.config.shiro.oauth2;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;

/**
 * @ClassName IDAASUserInfo
 * @Description
 * @Author summer
 * @Date 2023/11/24 14:34
 **/
@Data
public class IDAASUserInfo
{
    private String sub;
    private String ouid;
    private String nickname;
    @JsonAlias("phone_number")
    private String phoneNumber;
    @JsonAlias("ou_name")
    private String ouName;
    private String email;
    private String username;
}
