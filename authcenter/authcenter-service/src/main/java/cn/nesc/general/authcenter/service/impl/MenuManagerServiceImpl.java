package cn.nesc.general.authcenter.service.impl;

import cn.nesc.general.authcenter.mapper.TmMenuPOMapper;
import cn.nesc.general.authcenter.mapper.custom.MenuManagerMapper;
import cn.nesc.general.authcenter.model.TmMenuPO;
import cn.nesc.general.authcenter.model.TmMenuPOExample;
import cn.nesc.general.authcenter.service.MenuManagerService;
import cn.nesc.general.authcenter.service.util.TreeNode;
import cn.nesc.general.common.dictionary.Fixcode;
import cn.nesc.general.core.bean.AclUserBean;
import cn.nesc.general.core.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
public class MenuManagerServiceImpl implements MenuManagerService
{
    @Resource
    private MenuManagerMapper menuManagerMapper;

    @Resource
    private TmMenuPOMapper tmMenuPOMapper;


    @Override
    public List<TreeNode> getMenuTree() throws ServiceException
    {
        try
        {
            List<TmMenuPO> list = menuManagerMapper.getMenuList();
            // 遍历全部菜单，将节点转换为MenuNode,并将子节点放入父节点的children中
            Map<Integer, TreeNode> cache = new HashMap<>();
            List<TreeNode> menuNodeList = list.stream().map(function -> {
                TreeNode node = new TreeNode();
                Map<String, String> meta = new HashMap<>();
                node.setFunctionId(function.getMenuId());
                node.setPath(function.getPath());
                node.setName(function.getTitle());
                node.setIcon(function.getIcon());
                node.setFuncOrder(function.getFuncOrder());
                cache.put(function.getMenuId(), node);
                if (cache.containsKey(function.getFatherId()))
                {
                    cache.get(function.getFatherId()).addChildren(node);
                    return null;
                }
                return node;
            }).filter(menuNode -> null != menuNode).collect(Collectors.toList());
            return menuNodeList;
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
            throw new ServiceException("获取系统菜单失败", e);
        }
    }

    @Override
    public int updateMenu(TreeNode treeNode, AclUserBean loginUser) throws ServiceException
    {
        try
        {
            TmMenuPO updateMenu = new TmMenuPO();
            updateMenu.setMenuId(treeNode.getFunctionId());

            updateMenu.setPath(treeNode.getPath());
            updateMenu.setTitle(treeNode.getName());
            updateMenu.setIcon(treeNode.getIcon());
            updateMenu.setFuncOrder(treeNode.getFuncOrder());
            updateMenu.setUpdateBy(loginUser.getUserCode());
            updateMenu.setUpdateDate(new Date());
            return tmMenuPOMapper.updateByPrimaryKeySelective(updateMenu);
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
            throw new ServiceException("更新系统菜单失败", e);
        }
    }

    @Override
    public int deleteMenuById(Integer functionId, AclUserBean loginUser) throws ServiceException
    {
        try
        {
            TmMenuPO deleteMenu = new TmMenuPO();
            deleteMenu.setMenuId(functionId);
            deleteMenu.setIsDelete(Fixcode.IF_TYPE_YES.getCode());
            deleteMenu.setUpdateBy(loginUser.getUserCode());
            deleteMenu.setUpdateDate(new Date());
            int count = tmMenuPOMapper.updateByPrimaryKeySelective(deleteMenu);
            if (count > 0)
            {
                // 删除该菜单下的子菜单
                TmMenuPOExample example = new TmMenuPOExample();
                TmMenuPOExample.Criteria criteria = example.createCriteria();
                criteria.andFatherIdEqualTo(functionId);
                criteria.andIsDeleteEqualTo(Fixcode.IF_TYPE_NO.getCode());
                List<TmMenuPO> children = tmMenuPOMapper.selectByExample(example);

                children.stream().forEach(child -> {
                    try
                    {
                        deleteMenuById(child.getMenuId(), loginUser);
                    }
                    catch (ServiceException e)
                    {
                        log.error(e.getMessage(), e);
                    }
                });
            }
            return count;
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
            throw new ServiceException("删除系统菜单失败", e);
        }
    }

    @Override
    public int createMenu(TreeNode treeNode, Integer fatherId, AclUserBean loginUser) throws ServiceException
    {
        try
        {
            TmMenuPO menu = new TmMenuPO();
            menu.setPath(treeNode.getPath());
            menu.setTitle(treeNode.getName());
            menu.setIcon(treeNode.getIcon());
            menu.setFuncOrder(treeNode.getFuncOrder());
            menu.setFatherId(fatherId);
            menu.setIsDelete(Fixcode.IF_TYPE_NO.getCode());
            menu.setCreateBy(loginUser.getUserCode());
            menu.setCreateDate(new Date());
            return tmMenuPOMapper.insertSelective(menu);
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
            throw new ServiceException("创建系统菜单失败", e);
        }
    }
}
