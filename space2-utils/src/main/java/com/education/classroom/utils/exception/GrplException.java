package com.education.classroom.utils.exception;

/**
 * @author 
 * @version $Revision: 1.1 $
 * 建立日期 2013-1-30 
 */
public class GrplException extends RuntimeException
{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;

	public GrplException()
	{
		super();
	}

	public GrplException(String message, Throwable cause)
	{
		super(message, cause);
	}

	public GrplException(String message)
	{
		super(message);
	}

	public GrplException(Throwable cause)
	{
		super(cause);
	}

}
