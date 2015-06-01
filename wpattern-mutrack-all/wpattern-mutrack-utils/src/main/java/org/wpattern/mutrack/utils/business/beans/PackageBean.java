package org.wpattern.mutrack.utils.business.beans;

import java.util.ArrayList;
import java.util.List;

import org.wpattern.mutrack.utils.BaseBean;

public class PackageBean extends BaseBean {

	private static final long serialVersionUID = 201505301309L;

	private List<PackageEventBean> events;

	private String packageCode;

	public PackageBean() {
		this.events = new ArrayList<PackageEventBean>();
	}

	public List<PackageEventBean> getEvents() {
		return this.events;
	}

	public void addEvent(PackageEventBean event) {
		this.events.add(event);
	}

	public String getPackageCode() {
		return this.packageCode;
	}

	public void setPackageCode(String packageCode) {
		this.packageCode = packageCode;
	}

}
