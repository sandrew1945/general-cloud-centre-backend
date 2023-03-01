package cn.nesc.general.core.enums;

public enum ErrorCode implements BaseEnum
{
	// 公共 errorcode
	EC400(400, "Bad Request"),
	EC401(401, "Unauthorized"),
	EC403(403, "Forbidden"),
	EC404(404, "Not Found"),
	EC405(405, "Method Not Allowed"),
	EC500(500, "Internal Server Error"),
	EC502(502, "Bad Gateway"),
	EC503(503, "Service Unavailable"),
	EC504(504, "Gateway Timeout");


	private Integer code;		// 错误码

	private String message;		// 错误信息

	ErrorCode(Integer code, String message)
	{
		this.code = code;
		this.message = message;
	}


	@Override
	public Integer getCode()
	{
		return code;
	}

	@Override
	public Integer getType()
	{
		return null;
	}

	@Override
	public String getDesc()
	{
		return message;
	}



}
