/**
 * Copyright (C), 2015-2023, 东北证券股份有限公司
 * FileName: ClientBaseController
 * Author:   summer
 * Date:     2023/3/13 16:04
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 **/

package cn.nesc.general.common.controller;

import cn.nesc.general.common.bean.AclUserBean;
import cn.nesc.general.core.common.ContextHolder;
import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName ClientBaseController
 * @Description 微服务客户端BaseController
 *              获取当前用户信息为从认证中心获取后存入ThreadLocal
 * @Author summer
 * @Date 2023/3/13 16:04
 **/
@Slf4j
public class ClientBaseController extends BaseController
{
    protected AclUserBean getLoginUser()
    {
        String token = request.getHeader("sid");
        AclUserBean loginUser = ContextHolder.get(token, AclUserBean.class);
        return loginUser;
    }
}
