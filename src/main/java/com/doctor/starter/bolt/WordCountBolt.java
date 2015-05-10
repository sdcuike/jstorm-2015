package com.doctor.starter.bolt;

import java.util.HashMap;
import java.util.Map;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.tuple.Tuple;

import com.doctor.common.ContextBaseRichBolt;
import com.doctor.starter.common.JstromComponentConstrant;

public class WordCountBolt extends ContextBaseRichBolt {
	private static final long serialVersionUID = -7912005846339375514L;

	private Map<String, Integer> map;

	@Override
	public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {
		super.prepare(stormConf, context, collector);
		map = new HashMap<>();
	}

	@Override
	public void execute(Tuple input) {
		String word = input.getStringByField(JstromComponentConstrant.SplitSentenceBolt_Fields);
		if (map.get(word) == null) {
			map.put(word, 1);
			log.info("WordCountBolt:" + word + "=" + 1);
		} else {
			Integer count = map.get(word);
			count++;
			map.put(word, count);
			log.info("WordCountBolt:" + word + "=" + count);
		}
	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {

	}

	@Override
	public void cleanup() {
		map.clear();
		super.cleanup();
	}
}
