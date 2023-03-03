package cn.nesc.general.common.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

/**
 * Created by summer on 2020/4/15.
 */
@Data
public class PostResponse
{
    private int code;

    private String msg;

    @JsonIgnore
    private Object data;

    @Override
    public String toString()
    {
        return "PostResponse{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                '}';
    }
}
