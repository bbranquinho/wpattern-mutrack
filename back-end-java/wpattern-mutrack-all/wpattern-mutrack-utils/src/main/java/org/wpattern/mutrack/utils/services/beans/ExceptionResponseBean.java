package org.wpattern.mutrack.utils.services.beans;


import javax.xml.bind.annotation.XmlTransient;

import org.wpattern.mutrack.utils.BaseBean;
import org.wpattern.mutrack.utils.services.constants.MessageConstants;

public class ExceptionResponseBean extends BaseBean {

	private static final long serialVersionUID = 201509140417L;

	private int code;

	private String type;

	private String message;

	public ExceptionResponseBean() {
	}

	public ExceptionResponseBean(int code, String message){
		this.code = code;

		switch(code){
		case MessageConstants.ERROR:
			this.setType("error");
			break;

		case MessageConstants.VALIDATION:
			this.setType("validation");
			break;

		case MessageConstants.WARNING:
			this.setType("warning");
			break;

		case MessageConstants.INFO:
			this.setType("info");
			break;

		default:
			this.setType("unknown");
			break;
		}

		this.message = message;
	}

	@XmlTransient
	public int getCode() {
		return this.code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
