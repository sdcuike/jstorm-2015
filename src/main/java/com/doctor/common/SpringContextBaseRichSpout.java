package com.doctor.common;

import java.util.Map;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import backtype.storm.spout.SpoutOutputCollector;
import backtype.storm.task.TopologyContext;

/**
 * spring+jstorm BaseRichSpout
 * 
 * @author doctor
 *
 * @time 2015年5月8日
 * 
 */
public abstract class SpringContextBaseRichSpout extends ContextBaseRichSpout {

	private static final long serialVersionUID = -5229987691804863325L;

	protected AnnotationConfigApplicationContext context;

	@Override
	public void open(Map conf, TopologyContext context, SpoutOutputCollector collector) {
		super.open(conf, context, collector);

		Class<?> annotatedClasses = getClass().getAnnotation(SpringConfig.class).value();
		this.context = new AnnotationConfigApplicationContext(annotatedClasses);
		this.context.getAutowireCapableBeanFactory().autowireBean(this);
	}

}
