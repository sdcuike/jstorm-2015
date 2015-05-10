package com.doctor.starter.common;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(basePackages = "com.doctor.starter.manager",
		includeFilters = {
				@Filter(type = FilterType.REGEX, pattern = ".RandomSentenceManager") })
public class RandomSentenceConfig {

}
