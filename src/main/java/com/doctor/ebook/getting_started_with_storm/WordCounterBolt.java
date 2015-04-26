package com.doctor.ebook.getting_started_with_storm;

import java.util.HashMap;
import java.util.Map;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.tuple.Tuple;

import com.doctor.common.ContextBaseRichBolt;

/**
 * @author doctor
 *
 * @time 2015年4月25日 下午11:35:05
 */
public class WordCounterBolt extends ContextBaseRichBolt {
	private static final long serialVersionUID = 8157872805076023917L;
	private Map<String, Integer> counters;

	@Override
	public void prepare(@SuppressWarnings("rawtypes") Map stormConf, TopologyContext context, OutputCollector collector) {
		super.prepare(stormConf, context, collector);
		counters = new HashMap<>();
	}

	@Override
	public void execute(Tuple input) {
		if (Chapter2CommonConstant.wordNormalizer_componentId.equals(input.getSourceComponent()) &&
				Chapter2CommonConstant.wordNormalizer_streamId.equals(input.getSourceStreamId())) {

			String field = input.getStringByField(Chapter2CommonConstant.wordNormalizer_fields);
			if (counters.containsKey(field)) {
				Integer num = counters.get(field);
				counters.put(field, num + 1);
				log.info("WordCounterBolt.execute:" + field + ":" + num + 1);
			} else {
				counters.put(field, 1);
				log.info("WordCounterBolt.execute:" + field + ":" + 1);

			}
		}

	}

	@Override
	public void cleanup() {
		counters.clear();
		super.cleanup();
	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		// TODO Auto-generated method stub

	}

}
