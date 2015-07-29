package com.doctor.starter;

import backtype.storm.Config;
import backtype.storm.topology.TopologyBuilder;
import backtype.storm.tuple.Fields;

import com.doctor.common.util.StormSubmitterUtil;
import com.doctor.starter.bolt.SplitSentenceBolt;
import com.doctor.starter.bolt.WordCountBolt;
import com.doctor.starter.common.JstromComponentConstrant;
import com.doctor.starter.spout.RandomSentenceSpout;

public class JstormMain {

	public static void main(String[] args) throws InterruptedException {
		TopologyBuilder builder = new TopologyBuilder();

		builder.setSpout(JstromComponentConstrant.RandomSentenceSpout_ComponentId, new RandomSentenceSpout(), 1);
		builder.setBolt(JstromComponentConstrant.SplitSentenceBolt_ComponentId, new SplitSentenceBolt(), 6)
				.shuffleGrouping(JstromComponentConstrant.RandomSentenceSpout_ComponentId);
		builder.setBolt(JstromComponentConstrant.WordCountBolt_ComponentId, new WordCountBolt(), 8)
				.fieldsGrouping(JstromComponentConstrant.SplitSentenceBolt_ComponentId, new Fields(JstromComponentConstrant.SplitSentenceBolt_Fields));

		Config config = new Config();
		config.setNumWorkers(3);
		config.setDebug(true);
		StormSubmitterUtil.submitTopologyLocally(builder.createTopology(), "starter-wordcount", config, 30);
	}
}
