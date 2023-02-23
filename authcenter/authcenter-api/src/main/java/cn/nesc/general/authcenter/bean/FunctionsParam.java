package cn.nesc.general.authcenter.bean;

import lombok.Data;

import java.util.List;

/**
 * Created by summer on 2020/2/1.
 */
@Data
public class FunctionsParam
{
    public Integer roleId;

    public List<Integer> functionIds;
}
