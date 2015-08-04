package com.doctor.common.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.StormSubmitter;
import backtype.storm.generated.AlreadyAliveException;
import backtype.storm.generated.InvalidTopologyException;
import backtype.storm.generated.StormTopology;

/**
 * Topology 提交工具类
 * 
 * @see 参考：https://github.com/apache/storm/blob/master/examples/storm-starter/src/jvm/storm/starter/util/StormRunner.java
 * 
 * @author doctor
 *
 * @time 2015年5月9日 下午10:36:59
 */
public final class StormSubmitterUtil {

	public static void submitTopologyLocally(StormTopology topology, String topologyName, Config conf, int runtimeInMinutes) throws InterruptedException {
		LocalCluster localCluster = new LocalCluster();
		localCluster.submitTopology(topologyName, conf, topology);
		TimeUnit.MINUTES.sleep(runtimeInMinutes);
		localCluster.killTopology(topologyName);
		localCluster.shutdown();
	}

	public static void submitTopologyRemotely(StormTopology topology, String topologyName, Config conf) throws AlreadyAliveException, InvalidTopologyException {
		StormSubmitter.submitTopology(topologyName, conf, topology);
	}

	/**
	 * 从外部文件载入jstorm配置
	 * 
	 * @param propFile
	 * @return
	 */
	public static Config LoadConfigFromPropertyFile(final String propFile) {
		Properties properties = new Properties();

		try (FileInputStream inputStream = new FileInputStream(propFile)) {
			properties.load(inputStream);
		} catch (IOException e) {
			throw new RuntimeException("load file error:" + propFile, e);
		}

		Config config = new Config();
		for (Entry<Object, Object> entry : properties.entrySet()) {
			config.put(entry.getKey().toString(), entry.getValue());
		}

		return config;
	}
}
