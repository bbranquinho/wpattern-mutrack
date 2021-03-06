package org.wpattern.mutrack.service.custom;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.wpattern.mutrack.utils.business.ITracker;
import org.wpattern.mutrack.utils.business.beans.PackageBean;
import org.wpattern.mutrack.utils.data.IPackageData;
import org.wpattern.mutrack.utils.entities.PackageEntity;
import org.wpattern.mutrack.utils.services.paths.ITrackService;

@Component
public class TrackService implements ITrackService {

	@Autowired
	private ITracker tracker;

	@Autowired
	private IPackageData packageData;

	@Override
	public List<PackageBean> tracker(List<Long> packagesId) {
		List<String> codes = new ArrayList<String>();
		PackageEntity packagee;

		for (Long packageId : packagesId) {
			packagee = this.packageData.findById(packageId);
			codes.add(packagee.getCode());
		}

		return this.tracker.track(codes);
	}

	@Override
	public PackageBean trackerFullEvent(String packageCode) {
		return this.tracker.trackFullEvent(packageCode);
	}

	@Override
	public PackageBean trackerLastEvent(String packageCode) {
		return this.tracker.trackLastEvent(packageCode);
	}

	@Override
	public List<PackageBean> trackerMultipleLastEvent(List<String> packageCode) {
		if ((packageCode != null) && (packageCode.size() > 0)) {
			return this.tracker.trackLastEvent(packageCode);
		} else {
			return null;
		}
	}

}
