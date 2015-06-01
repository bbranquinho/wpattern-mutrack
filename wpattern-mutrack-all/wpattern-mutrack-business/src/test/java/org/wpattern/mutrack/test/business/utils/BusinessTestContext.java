package org.wpattern.mutrack.test.business.utils;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.wpattern.mutrack.business.utils.BusinessContext;

@Configuration
@Import(value = { BusinessContext.class })
@ComponentScan(basePackages = { BusinessTestContext.BASE_TEST_PACKAGE })
public class BusinessTestContext {

	static final String BASE_TEST_PACKAGE = "org.wpattern.mutrack.test.business";

	@Bean(name = "placeHolderConfigurer")
	public static PropertySourcesPlaceholderConfigurer getPlaceHolderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}

}
