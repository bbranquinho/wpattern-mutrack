package org.wpattern.mutrack.utils.business;

import java.util.List;

import org.wpattern.mutrack.utils.business.beans.PackageBean;

public interface ITracker {

	public List<PackageBean> track(List<String> codes);

	public PackageBean trackFullEvent(String code);

	public PackageBean trackLastEvent(String code);

	public List<PackageBean> trackLastEvent(List<String> packagesCode);

}
