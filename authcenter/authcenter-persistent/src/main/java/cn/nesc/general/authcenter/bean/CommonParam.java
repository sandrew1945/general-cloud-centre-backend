package cn.nesc.general.authcenter.bean;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by summer on 2020/3/26.
 */
@Data
public class CommonParam
{
    private List<String> headerParams = new ArrayList<>();

    private List<String> requestParams = new ArrayList<>();

    public void addHeaderParam(String param)
    {
        headerParams.add(param);
    }

    public void addReqParam(String param)
    {
        requestParams.add(param);
    }
}
