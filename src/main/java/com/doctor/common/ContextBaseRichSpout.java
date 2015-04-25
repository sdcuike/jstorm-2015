package com.doctor.common;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import backtype.storm.spout.SpoutOutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.base.BaseRichSpout;

/**
 * @author doctor
 *
 * @time 2015年4月25日 下午10:43:48
 */
public abstract class ContextBaseRichSpout extends BaseRichSpout {
	private static final long serialVersionUID = 6638065818569550905L;
	protected Logger log = LoggerFactory.getLogger(getClass());

	@SuppressWarnings("rawtypes")
	protected Map conf;
	protected TopologyContext context;
	protected SpoutOutputCollector collector;

	@Override
	public void open(@SuppressWarnings("rawtypes") Map conf, TopologyContext context, SpoutOutputCollector collector) {
		this.conf = conf;
		this.context = context;
		this.collector = collector;
	}

}
