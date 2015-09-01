package org.wpattern.mutrack.test.service.utils;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.wpattern.mutrack.service.utils.ServiceContext;

@Configuration
@Import(value = { ServiceContext.class })
@ComponentScan(basePackages = { ServiceTestContext.SERVICE_TEST_CONTEXT })
public class ServiceTestContext {

	static final String SERVICE_TEST_CONTEXT = "org.wpattern.mutrack.test.service";

}
