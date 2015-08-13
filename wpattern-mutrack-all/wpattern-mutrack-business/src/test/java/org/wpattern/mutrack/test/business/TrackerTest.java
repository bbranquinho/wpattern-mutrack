package org.wpattern.mutrack.test.business;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.wpattern.mutrack.test.business.utils.AbstractBusinessTest;
import org.wpattern.mutrack.utils.business.ITracker;
import org.wpattern.mutrack.utils.business.beans.PackageBean;

public class TrackerTest extends AbstractBusinessTest {

	private final Logger LOGGER = Logger.getLogger(this.getClass());

	@Autowired
	private ITracker tracker;

	@Test
	public void testTrackListPackage() {
		List<String> codes = new ArrayList<String>();

		codes.add("RE736868622SE");
		codes.add("DM567434841BR");
		codes.add("RJ367374538CN");

		List<PackageBean> packages = this.tracker.track(codes);

		if (this.LOGGER.isDebugEnabled()) {
			this.LOGGER.debug(packages);
		}
	}

}
