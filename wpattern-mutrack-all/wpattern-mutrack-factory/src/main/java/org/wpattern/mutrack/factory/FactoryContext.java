package org.wpattern.mutrack.factory;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.wpattern.mutrack.data.utils.DataContext;
import org.wpattern.mutrack.service.utils.ServiceContext;

@Configuration
@Import(value = { DataContext.class, ServiceContext.class })
@ComponentScan(basePackages = { FactoryContext.BASE_PACKAGE })
public class FactoryContext {

	static final String BASE_PACKAGE = "org.wpattern.mutrack.factory";

}
