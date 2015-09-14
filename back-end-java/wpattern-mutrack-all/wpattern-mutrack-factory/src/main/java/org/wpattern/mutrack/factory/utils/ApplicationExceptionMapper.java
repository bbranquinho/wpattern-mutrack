package org.wpattern.mutrack.factory.utils;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.springframework.stereotype.Component;
import org.wpattern.mutrack.utils.services.beans.ExceptionResponseBean;
import org.wpattern.mutrack.utils.services.exceptions.BaseException;
import org.wpattern.mutrack.utils.services.exceptions.ValidationException;

// Http status codes: http://tools.ietf.org/html/rfc7231#section-6
// Http status codes: http://www.iana.org/assignments/http-status-codes/http-status-codes.xhtml
@Provider
@Component
public class ApplicationExceptionMapper implements ExceptionMapper<Exception> {

	@Override
	public Response toResponse(Exception exception) {
		if (exception instanceof WebApplicationException) {
			javax.ws.rs.WebApplicationException e = (javax.ws.rs.WebApplicationException) exception;

			return Response
					.status(e.getResponse().getStatus())
					.entity(new ExceptionResponseBean(e.getResponse().getStatus(), exception.getMessage()))
					.build();
		}

		if (!(exception instanceof BaseException)) {
			return Response.status(500).entity(new ExceptionResponseBean(500, "something bad happened")).build();
		}

		BaseException baseException = (BaseException)exception;
		ResponseBuilder responseBuilder = Response.status(baseException.getHttpStatusCode());

		if (exception instanceof ValidationException) {
			return responseBuilder
					.entity(new ExceptionResponseBean(baseException.getCode(), exception.getMessage()))
					.build();
		} else {
			return responseBuilder
					.entity(new ExceptionResponseBean(500, "something bad happened"))
					.build();
		}
	}

}
