package org.wpattern.mutrack.test.data.utils;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DataTestContext.class)
public abstract class AbstractDataTest {

	protected void logAndFail(String errorMsg, Logger log){
		if(log != null){
			log.fatal(errorMsg);
		}

		Assert.fail(errorMsg);
	}

	protected void logAndFail(String errorMsg, Exception e, Logger log){
		if(log != null){
			log.fatal(errorMsg, e);
		}

		Assert.fail(errorMsg);
	}

}
