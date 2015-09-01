package org.wpattern.mutrack.factory.utils;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.wpattern.mutrack.business.utils.BusinessContext;
import org.wpattern.mutrack.data.utils.DataContext;
import org.wpattern.mutrack.service.utils.ServiceContext;
import org.wpattern.mutrack.service.utils.ServiceSecurityContext;

@Configuration
@Import(value = { BusinessContext.class, DataContext.class, ServiceContext.class,
		ServiceSecurityContext.class })
@ComponentScan(basePackages = { FactoryContext.BASE_PACKAGE })
public class FactoryContext {

	static final String BASE_PACKAGE = "org.wpattern.mutrack.factory";

}
