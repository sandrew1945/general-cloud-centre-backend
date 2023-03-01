/***************************************************************************************************
 * <pre>
* FILE : PageResult.java
* CLASS : Session
*
* AUTHOR : SuMMeR
*
* FUNCTION : TODO
*
*
*======================================================================
* CHANGE HISTORY LOG
*----------------------------------------------------------------------
* MOD. NO.| DATE | NAME | REASON | CHANGE REQ.
*----------------------------------------------------------------------
* 		  |2010-4-16| SuMMeR| Created |
* DESCRIPTION:
* </pre>
 **************************************************************************************************/
/**
 * $Id: PageResult.java,v 0.1 2010-4-16 ����02:18:12 SuMMeR Exp $
 */
package cn.nesc.general.core.bean;


import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.function.Function;


/**
 * 
 * Function    : 
 * @author     : SuMMeR
 * CreateDate  : 2010-5-21
 * @version    :
 * @param <T>
 */
@Slf4j
public class PageResult<T>
{
	/**
	 * @uml.property name="curPage"
	 */
	private int curPage = 1;

	/**
	 * @uml.property name="pageSize"
	 */
	private int pageSize = 10;

	/**
	 * @uml.property name="totalPages"
	 */
	private int totalPages = 0;

	/**
	 * @uml.property name="totalRecords"
	 */
	private int totalRecords = 0;

	/**
	 * @uml.property name="records"
	 */
	private List<T> records = null;

	/**
	 * @return
	 * @uml.property name="curPage"
	 */
	public int getCurPage()
	{
		return curPage;
	}

	/**
	 * @param curPage
	 * @uml.property name="curPage"
	 */
	public void setCurPage(int curPage)
	{
		this.curPage = curPage;
	}

	/**
	 * @return
	 * @uml.property name="pageSize"
	 */
	public int getPageSize()
	{
		return pageSize;
	}

	/**
	 * @param pageSize
	 * @uml.property name="pageSize"
	 */
	public void setPageSize(int pageSize)
	{
		this.pageSize = pageSize;
	}

	/**
	 * @return
	 * @uml.property name="totalPages"
	 */
	public int getTotalPages()
	{
		return totalPages;
	}

	/**
	 * @param totalPages
	 * @uml.property name="totalPages"
	 */
	public void setTotalPages(int totalPages)
	{
		this.totalPages = totalPages;
	}

	/**
	 * @return
	 * @uml.property name="totalRecords"
	 */
	public int getTotalRecords()
	{
		return totalRecords;
	}

	/**
	 * @param totalRecords
	 * @uml.property name="totalRecords"
	 */
	public void setTotalRecords(int totalRecords)
	{
		this.totalRecords = totalRecords;
	}

	/**
	 * @return
	 * @uml.property name="records"
	 */
	public List<T> getRecords()
	{
		return records;
	}

	/**
	 * @param records
	 * @uml.property name="records"
	 */
	public void setRecords(List<T> records)
	{
		this.records = records;
	}

	public PageResult convert(Function<List, List> convertor)
	{
		PageResult result = new PageResult();
		try
		{
			result.setCurPage(this.getCurPage());
			result.setPageSize(this.getPageSize());
			result.setTotalPages(this.getTotalPages());
			result.setTotalRecords(this.getTotalRecords());
			List newRecords = convertor.apply(this.getRecords());
			result.setRecords(newRecords);
			return result;
		}
		catch (Exception e)
		{
			log.error(e.getMessage(), e);
		}
		return result;
	}

	public String toString()
	{
		StringBuilder sbd = new StringBuilder();
		sbd.append("PageResult@");
		sbd.append(this.hashCode());
		sbd.append("[PSize=" + this.pageSize + "; ");
		sbd.append("TPSize=" + this.totalPages + "; ");
		sbd.append("TRSize=" + this.totalRecords + "; ");
		sbd.append("CPage=" + this.curPage + "; ");
		if (this.records.size() > 0)
		{
			sbd.append("Class=" + this.records.get(0).getClass());
		}
		sbd.append("]");
		return sbd.toString();
	}
}
