/**********************************************************************
* <pre>
* FILE : EBException.java
* CLASS : EBException
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
* 		  |2009-11-2| SuMMeR| Created |
* DESCRIPTION:
* </pre>
***********************************************************************/
/**
* $Id: ServiceException.java,v 1.1 2013/07/31 08:32:47 xin.jin Exp $
*/
package cn.nesc.general.core.exception;


/**
 * Function    : 
 * @author     : SuMMeR
 * CreateDate  : 2009-11-2
 * @version    :
 */
public class DAOException extends BaseException
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4042594687623349469L;

	public DAOException()
	{
		super();
	}

	public DAOException(String message, Throwable cause)
	{
		super(message, cause);
	}

	public DAOException(String message)
	{
		super(message);
	}

	public DAOException(Throwable cause)
	{
		super(cause);
	}
}
