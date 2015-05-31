package org.wpattern.mutrack.business.beans;

import java.util.List;

import org.wpattern.mutrack.utils.BaseBean;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

@XStreamAlias("objeto")
public class RequestObjectBean extends BaseBean {

	private static final long serialVersionUID = 201505301546L;

	@XStreamAlias("numero")
	private String packageCode;

	@XStreamImplicit
	private List<RequestEventBean> events;

	public RequestObjectBean() {
	}

	public String getPackageCode() {
		return this.packageCode;
	}

	public void setPackageCode(String packageCode) {
		this.packageCode = packageCode;
	}

	public List<RequestEventBean> getEvents() {
		return this.events;
	}

	public void setEvents(List<RequestEventBean> events) {
		this.events = events;
	}

}
