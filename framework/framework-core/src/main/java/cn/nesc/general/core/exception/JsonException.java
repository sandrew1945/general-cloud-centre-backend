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
* $Id: JsonException.java,v 1.1 2013/07/31 08:32:47 xin.jin Exp $
*/
package cn.nesc.general.core.exception;

/**
 * Function    : 
 * @author     : SuMMeR
 * CreateDate  : 2009-11-2
 * @version    :
 */
public class JsonException extends BaseException
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4042594687623349469L;

	public JsonException()
	{
		super();
	}

	public JsonException(String message, Throwable cause)
	{
		super(message, cause);
	}

	public JsonException(String message)
	{
		super(message);
	}

	public JsonException(Throwable cause)
	{
		super(cause);
	}

}
