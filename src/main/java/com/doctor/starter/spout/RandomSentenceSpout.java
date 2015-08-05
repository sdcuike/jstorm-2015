package com.doctor.starter.spout;

import java.util.Map;

import javax.annotation.Resource;

import backtype.storm.spout.SpoutOutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Values;

import com.doctor.common.SpringConfig;
import com.doctor.common.SpringContextBaseRichSpout;
import com.doctor.starter.common.JstromComponentConstrant;
import com.doctor.starter.common.RandomSentenceConfig;
import com.doctor.starter.manager.RandomSentenceManager;

/**
 * @author doctor
 *
 * @time 2015年5月9日 下午11:40:38
 */
@SpringConfig(RandomSentenceConfig.class)
public class RandomSentenceSpout extends SpringContextBaseRichSpout {
	private static final long serialVersionUID = 7157829931032548648L;

	@Resource(name = "randomSentenceManager")
	private RandomSentenceManager randomSentenceManager;

	@Override
	public void open(Map conf, TopologyContext context, SpoutOutputCollector collector) {
		super.open(conf, context, collector);
	}

	@Override
	public void nextTuple() {
		this.collector.emit(new Values(randomSentenceManager.randomString()));

	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		declarer.declare(new Fields(JstromComponentConstrant.RandomSentenceSpout_Fields));

	}

	@Override
	public void close() {
		super.close();
	}

	@Override
	public void ack(Object msgId) {
		 
	}

	@Override
	public void fail(Object msgId) {
		
	}

}
