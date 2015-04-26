package com.doctor.ebook.getting_started_with_storm;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang.RandomStringUtils;

import backtype.storm.spout.SpoutOutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Values;

import com.doctor.common.ContextBaseRichSpout;

/**
 * @author doctor
 *
 * @time 2015年4月25日 下午10:39:21
 */
public class WordProducerSpout extends ContextBaseRichSpout {
	private static final long serialVersionUID = -930888930597360858L;
	private String content = "A spout emits a list of defined fields. This architecture allows you to have" +
			"different kinds of bolts reading the same spout stream, which can then" +
			"define fields for other bolts to consume and so on";

	/**
	 * open is the first method called in any spout.
	 * 
	 * The parameters it receives are the TopologyContext, which contains all our topology data; the conf object, which is created
	 * in the topology definition; and the SpoutOutputCollector, which enables us to emit the data that will be processed by the
	 * bolts.
	 */
	@Override
	public void open(Map conf, TopologyContext context, SpoutOutputCollector collector) {
		super.open(conf, context, collector);
	}

	/**
	 * from this method, we’ll emit values to be processed by the bolts.
	 */
	@Override
	public void nextTuple() {
		String random = RandomStringUtils.random(6, content);
		try {
			TimeUnit.SECONDS.sleep(1);
			collector.emit(Chapter2CommonConstant.wordProducer_streamId, new Values(random));
			log.info("WordProducerSpout:" + random);
		} catch (InterruptedException e) {
			log.error("TimeUnit.SECONDS.sleep.error", e);
		}

	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		declarer.declareStream(Chapter2CommonConstant.wordProducer_streamId, new Fields(Chapter2CommonConstant.wordProducer_fields));

	}

}
