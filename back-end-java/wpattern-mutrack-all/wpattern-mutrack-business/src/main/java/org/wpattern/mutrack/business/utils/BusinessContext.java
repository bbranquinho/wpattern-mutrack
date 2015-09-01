package org.wpattern.mutrack.business.utils;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = { BusinessContext.BASE_PACKAGE })
public class BusinessContext {

	static final String BASE_PACKAGE = "org.wpattern.mutrack.business";

}
