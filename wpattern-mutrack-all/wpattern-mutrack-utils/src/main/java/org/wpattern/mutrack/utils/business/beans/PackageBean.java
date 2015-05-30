package org.wpattern.mutrack.utils.business.beans;

import org.wpattern.mutrack.utils.BaseBean;
import org.wpattern.mutrack.utils.entities.PackageEntity;

public class PackageBean extends BaseBean {

	private static final long serialVersionUID = 201505301309L;

	private PackageEventBean events;

	private PackageEntity packagee;

	public PackageBean() {
	}

	public PackageEventBean getEvents() {
		return this.events;
	}

	public void setEvents(PackageEventBean events) {
		this.events = events;
	}

	public PackageEntity getPackagee() {
		return this.packagee;
	}

	public void setPackagee(PackageEntity packagee) {
		this.packagee = packagee;
	}

}
