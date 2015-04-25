package com.doctor.common;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.base.BaseRichBolt;

/**
 * @author doctor
 *
 * @time 2015年4月25日 下午11:09:01
 */
public abstract class ContextBaseRichBolt extends BaseRichBolt {
	private static final long serialVersionUID = -1461850435861165212L;
	protected Logger log = LoggerFactory.getLogger(getClass());

	@SuppressWarnings("rawtypes")
	protected Map conf;
	protected TopologyContext context;
	protected OutputCollector collector;

	@Override
	public void prepare(@SuppressWarnings("rawtypes") Map stormConf, TopologyContext context, OutputCollector collector) {
		this.conf = stormConf;
		this.context = context;
		this.collector = collector;
	}

}
