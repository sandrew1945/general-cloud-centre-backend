package cn.nesc.general.authcenter.commmon;

import com.sandrew.bury.util.POGenerator;

/**
 * Created by summer on 2019/7/26.
 */
public class TableGenUtil
{
    public static void main(String[] args)
    {
        try
        {
            POGenerator poGenerator = new POGenerator();
            poGenerator.genForPack("POConf.xml");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
