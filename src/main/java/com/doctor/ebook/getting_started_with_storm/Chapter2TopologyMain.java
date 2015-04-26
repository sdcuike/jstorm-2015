package com.doctor.ebook.getting_started_with_storm;

import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.topology.TopologyBuilder;
import backtype.storm.tuple.Fields;

/**
 * ebook.getting_started_with_storm chapter2 code
 * 
 * @author doctor
 *
 * @time 2015年4月25日 下午10:34:14
 */
public class Chapter2TopologyMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TopologyBuilder topologyBuilder = new TopologyBuilder();
		topologyBuilder.setSpout(Chapter2CommonConstant.wordProducer_componentId, new WordProducerSpout(), 1);

		// 1.error java.lang.IllegalArgumentException: No output fields defined for component:stream
		// topologyBuilder.setBolt(Chapter2CommonConstant.wordNormalizer_componentId, new WordNormalizerBolt(), 1)
		// .fieldsGrouping(Chapter2CommonConstant.wordProducer_componentId, new
		// Fields(Chapter2CommonConstant.wordProducer_fields));
		//
		// topologyBuilder.setBolt(Chapter2CommonConstant.wordCounter_componentId, new WordCounterBolt(), 1)
		// .fieldsGrouping(Chapter2CommonConstant.wordNormalizer_componentId, new
		// Fields(Chapter2CommonConstant.wordNormalizer_fields));
		//

		// 2.
		// topologyBuilder.setBolt(Chapter2CommonConstant.wordNormalizer_componentId, new WordNormalizerBolt(), 1)
		// .shuffleGrouping(Chapter2CommonConstant.wordProducer_componentId, Chapter2CommonConstant.wordProducer_streamId);
		//
		// topologyBuilder.setBolt(Chapter2CommonConstant.wordCounter_componentId, new WordCounterBolt(), 1)
		// .shuffleGrouping(Chapter2CommonConstant.wordNormalizer_componentId, Chapter2CommonConstant.wordNormalizer_streamId);
		//

		// 3.
		topologyBuilder.setBolt(Chapter2CommonConstant.wordNormalizer_componentId, new WordNormalizerBolt(), 1)
				.fieldsGrouping(Chapter2CommonConstant.wordProducer_componentId, Chapter2CommonConstant.wordProducer_streamId, new Fields(Chapter2CommonConstant.wordProducer_fields));

		topologyBuilder.setBolt(Chapter2CommonConstant.wordCounter_componentId, new WordCounterBolt(), 1)
				.fieldsGrouping(Chapter2CommonConstant.wordNormalizer_componentId, Chapter2CommonConstant.wordNormalizer_streamId, new Fields(Chapter2CommonConstant.wordNormalizer_fields));

		LocalCluster localCluster = new LocalCluster();

		Config conf = new Config();
		conf.setDebug(true);
		conf.setNumWorkers(1);
		localCluster.submitTopology("Chapter2TopologyMain", conf, topologyBuilder.createTopology());

	}

}
