package cn.nesc.general.authcenter.service;


import cn.nesc.general.core.exception.ServiceException;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 *  公共Service
 * Created by summer on 2018/2/15.
 */
public interface CommonService
{
    /**
     *  文件上传
     * @param relativePath  相对路径
     * @param file          上传文件
     * @return
     * @throws ServiceException
     */
    String fileUploadLocal(String relativePath, MultipartFile file) throws ServiceException;

    /**
     *  文件上传
     * @param relativePath  相对路径
     * @param base64File          上传文件
     * @return
     * @throws ServiceException
     */
    String fileUploadLocal(String relativePath, String filename, String base64File) throws ServiceException;

    /**
     *  显示本地图片
     * @param relativePath  相对路径
     * @return
     * @throws ServiceException
     */
    byte[] downloadFile(String relativePath) throws ServiceException;

}
