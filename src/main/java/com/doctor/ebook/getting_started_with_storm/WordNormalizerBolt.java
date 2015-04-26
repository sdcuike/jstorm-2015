package com.doctor.ebook.getting_started_with_storm;

import java.util.Map;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;
import backtype.storm.tuple.Values;

import com.doctor.common.ContextBaseRichBolt;

/**
 * @author doctor
 *
 * @time 2015年4月25日 下午11:14:27
 */
public class WordNormalizerBolt extends ContextBaseRichBolt {
	private static final long serialVersionUID = -1244951787400604294L;

	@Override
	public void prepare(@SuppressWarnings("rawtypes") Map stormConf, TopologyContext context, OutputCollector collector) {
		super.prepare(stormConf, context, collector);
	}

	/**
	 * The bolt will receive the line from the words file and process it to Normalize this line
	 * 
	 * The normalize will be put the words in lower case
	 */
	@Override
	public void execute(Tuple input) {
		if (Chapter2CommonConstant.wordProducer_componentId.equals(input.getSourceComponent()) &&
				Chapter2CommonConstant.wordProducer_streamId.equals(input.getSourceStreamId())) {
			String field = input.getStringByField(Chapter2CommonConstant.wordProducer_fields);
			log.info("WordNormalizer.execute:" + field);
			field = field.toLowerCase();
			collector.emit(Chapter2CommonConstant.wordNormalizer_streamId, new Values(field));
		}

	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		declarer.declareStream(Chapter2CommonConstant.wordNormalizer_streamId, new Fields(Chapter2CommonConstant.wordNormalizer_fields));

	}

}
