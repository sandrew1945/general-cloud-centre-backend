/**********************************************************************
* <pre>
* FILE : PageQueryBuilder.java
* CLASS : PageQueryBuilder
*
* AUTHOR : Administrator
*
* FUNCTION : TODO
*
*
*======================================================================
* CHANGE HISTORY LOG
*----------------------------------------------------------------------
* MOD. NO.| DATE | NAME | REASON | CHANGE REQ.
*----------------------------------------------------------------------
* 		  |2016年5月26日| Administrator| Created |
* DESCRIPTION:
* </pre>
***********************************************************************/
/**
* $Id: PageQueryBuilder.java,v 0.1 2016年5月26日 下午3:37:06 Administrator Exp $
*/

package cn.nesc.general.common.mybatis;


import cn.nesc.general.common.bean.PageResult;
import cn.nesc.general.core.exception.DAOException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

/**
 * Function    : 分页查询器
 * @author     : Administrator
 * CreateDate  : 2016年5月26日
 * @version    :
 */
public class PageQueryBuilder
{
	/**
	 * 
	 * Function    : 分页查询
	 * LastUpdate  : 2016年4月16日
	 * @param mapper	使用的Mapper
	 * @param methodName	调用的Mapper方法名
	 * @param parameters	SQL的参数
	 * @param curPage		当前页
	 * @return
	 * @throws DAOException
	 */
	public static <T> PageResult<T> pageQuery(Object mapper, String methodName, Object parameters, int curPage) throws DAOException
	{
		try
		{
			Pager pager = new Pager();
			pager.setPageNo(curPage);
			pager.setCondition(parameters);
			Class<?> cls = mapper.getClass();
			Method method = cls.getMethod(methodName, Pager.class);
			List<T> records = (List<T>)method.invoke(mapper, pager);
			PageResult pr = new PageResult<T>();
			pr.setCurPage(curPage);
			pr.setPageSize(pager.getPageSize());
			pr.setRecords(records);
			pr.setTotalRecords(pager.getTotalRows());
			pr.setTotalPages(pager.getPageCount());
			return pr;
		}
		catch (NoSuchMethodException | SecurityException e)
		{
			e.printStackTrace();
			throw new DAOException("没有找到所执行的方法名", e);
		}
		catch (IllegalAccessException e)
		{
			e.printStackTrace();
			throw new DAOException("分页查询失败", e);
		}
		catch (IllegalArgumentException e)
		{
			e.printStackTrace();
			throw new DAOException("错误的参数类型", e);
		}
		catch (InvocationTargetException e)
		{
			e.printStackTrace();
			throw new DAOException("分页查询失败", e);
		}
	}

	public static <T> PageResult<T> pageQuery(Object mapper, String methodName, Object parameters, int curPage, int pagesSize) throws DAOException
	{
		try
		{
			Pager pager = new Pager();
			pager.setPageNo(curPage);
			pager.setPageSize(pagesSize);
			pager.setCondition(parameters);
			Class<?> cls = mapper.getClass();
			Method method = cls.getMethod(methodName, Pager.class);
			List<T> records = (List<T>)method.invoke(mapper, pager);
			PageResult pr = new PageResult<T>();
			pr.setCurPage(curPage);
			pr.setPageSize(pager.getPageSize());
			pr.setRecords(records);
			pr.setTotalRecords(pager.getTotalRows());
			pr.setTotalPages(pager.getPageCount());
			return pr;
		}
		catch (NoSuchMethodException | SecurityException e)
		{
			e.printStackTrace();
			throw new DAOException("没有找到所执行的方法名", e);
		}
		catch (IllegalAccessException e)
		{
			e.printStackTrace();
			throw new DAOException("分页查询失败", e);
		}
		catch (IllegalArgumentException e)
		{
			e.printStackTrace();
			throw new DAOException("错误的参数类型", e);
		}
		catch (InvocationTargetException e)
		{
			e.printStackTrace();
			throw new DAOException("分页查询失败", e);
		}
	}
}
