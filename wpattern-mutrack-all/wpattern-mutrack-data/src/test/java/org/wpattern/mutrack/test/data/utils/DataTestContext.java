package org.wpattern.mutrack.test.data.utils;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.wpattern.mutrack.data.utils.DataContext;

@Configuration
@Import(value = { DataContext.class })
@ComponentScan(basePackages = { DataTestContext.BASE_TEST_PACKAGE })
public class DataTestContext {

	static final String BASE_TEST_PACKAGE = "org.wpattern.mutrack.test.data";

}
