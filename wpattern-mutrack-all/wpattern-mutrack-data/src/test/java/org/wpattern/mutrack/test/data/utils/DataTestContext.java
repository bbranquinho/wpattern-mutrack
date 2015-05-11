package org.wpattern.mutrack.test.data.utils;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.wpattern.mutrack.data.utils.DataContext;

@Configuration
@Import(value = { DataContext.class })
@ComponentScan(basePackages = { "org.wpattern.mutrack.test.data" })
public class DataTestContext {

}
