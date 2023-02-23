package cn.nesc.general.common.httpclient;

import lombok.Data;

/**
 * Created by summer on 2020/3/27.
 */
@Data
public class HttpResponse
{
    private int returnCode;

    private String returnContent;
}
