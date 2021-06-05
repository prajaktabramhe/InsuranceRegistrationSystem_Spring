package com.bridgelabz.imp.exception;

import java.util.Locale;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.bridgelabz.imp.util.ErrorResponse;
import com.bridgelabz.imp.util.Response;
import lombok.Data;

@ResponseStatus
@Data
public class UserException extends RuntimeException 
{
	public UserException(int statusCode, String statusmessage)
	{
		super(statusmessage);
		StatusCode = statusCode;
		Statusmessage = statusmessage;
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int StatusCode;
	private String Statusmessage;
	
	/**
	 * 
	 * @return : error response
	 */
	public Response getErrorResponse() 
	{
		return getErrorResponse(Locale.getDefault());
	}
	
	/**
	 * 
	 * @param locale
	 * @return : err
	 */
	public Response getErrorResponse(Locale locale) 
	{
		ErrorResponse err = new ErrorResponse(StatusCode, Statusmessage, getStackTrace());
		err.setStatusCode(getStatusCode());
		err.setStatusmessage(getStatusmessage());

		return err;
    }
}
