package com.doctor.starter.bolt;

import java.util.Map;

import javax.annotation.Resource;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;
import backtype.storm.tuple.Values;

import com.doctor.common.SpringConfig;
import com.doctor.common.SpringContextBaseRichBolt;
import com.doctor.starter.common.JstromComponentConstrant;
import com.doctor.starter.common.RandomSentenceConfig;
import com.doctor.starter.manager.RandomSentenceManager;

@SpringConfig(RandomSentenceConfig.class)
public class SplitSentenceBolt extends SpringContextBaseRichBolt {
	private static final long serialVersionUID = -819638290235113606L;

	@Resource(name = "randomSentenceManager")
	private RandomSentenceManager randomSentenceManager;

	@Override
	public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {
		super.prepare(stormConf, context, collector);
	}

	@Override
	public void execute(Tuple input) {
		String sentence = input.getStringByField(JstromComponentConstrant.RandomSentenceSpout_Fields);
		String[] str = randomSentenceManager.split(sentence);
		for (String string : str) {
			this.collector.emit(new Values(string));
		}
	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		declarer.declare(new Fields(JstromComponentConstrant.SplitSentenceBolt_Fields));

	}

	@Override
	public void cleanup() {
		super.cleanup();
	}

}
