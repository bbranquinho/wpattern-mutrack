package org.wpattern.mutrack.factory.exceptions;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.springframework.stereotype.Component;
import org.wpattern.mutrack.utils.services.beans.ExceptionResponseBean;
import org.wpattern.mutrack.utils.services.exceptions.BaseException;

// Http status codes: http://tools.ietf.org/html/rfc7231#section-6
// Http status codes: http://www.iana.org/assignments/http-status-codes/http-status-codes.xhtml
@Provider
@Component
public class ApplicationExceptionMapper implements ExceptionMapper<Exception> {

	private static final int SERVER_HTTP_STATUS = 500;

	@Override
	public Response toResponse(Exception exception) {
		if (exception instanceof BaseException) {
			BaseException baseException = (BaseException)exception;

			return Response
					.status(baseException.getHttpStatusCode())
					.entity(new ExceptionResponseBean(baseException.getCode(), baseException.getMessage()))
					.build();
		} else if (exception instanceof WebApplicationException) {
			WebApplicationException webApplicationException = (WebApplicationException) exception;

			return Response
					.status(webApplicationException.getResponse().getStatus())
					.entity(new ExceptionResponseBean(webApplicationException.getResponse().getStatus(), exception.getMessage()))
					.build();
		} else {
			return Response
					.status(SERVER_HTTP_STATUS)
					.entity(new ExceptionResponseBean(500, exception.getMessage()))
					.build();
		}
	}

}
