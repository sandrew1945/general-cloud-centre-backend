/**********************************************************************
* <pre>
* FILE : Principal.java
* CLASS : Principal
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
* 		  |2016年10月25日| SuMMeR| Created |
* DESCRIPTION:
* </pre>
***********************************************************************/
/**
* $Id: Principal.java,v 0.1 2016年10月25日 下午3:20:45 SuMMeR Exp $
*/

package cn.nesc.general.core.shiro;

import lombok.Data;

import java.io.Serializable;

/**
 * Function    : 
 * @author     : SuMMeR
 * CreateDate  : 2016年10月25日
 * @version    :
 */
@Data
public class Principal implements Serializable
{
	private String name;

	private Integer Type;
}
