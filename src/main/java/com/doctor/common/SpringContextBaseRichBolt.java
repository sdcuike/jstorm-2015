package com.doctor.common;

import java.util.Map;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;

/**
 * spring+jstorm BaseRichBolt
 * 
 * @author doctor
 *
 * @time 2015年5月8日 下午5:21:22
 */
public abstract class SpringContextBaseRichBolt extends ContextBaseRichBolt {
	private static final long serialVersionUID = -6394494576691105802L;

	protected AnnotationConfigApplicationContext context;

	@Override
	public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {
		super.prepare(stormConf, context, collector);

		Class<?> annotatedClasses = getClass().getAnnotation(SpringConfig.class).value();
		this.context = new AnnotationConfigApplicationContext(annotatedClasses);
		this.context.getAutowireCapableBeanFactory().autowireBean(this);
	}

	@Override
	public void cleanup() {
		this.context.close();
		super.cleanup();
	}

}
