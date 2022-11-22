package com.demo.userdata.config;

import com.hazelcast.config.Config;
import com.hazelcast.config.EvictionConfig;
import com.hazelcast.config.EvictionPolicy;
import com.hazelcast.config.MapConfig;
import com.hazelcast.config.MapStoreConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HazelcastConfiguration {

	@Bean
	public Config hazelCastConfig() {
		return new Config()
				.setInstanceName("hazelcast-instance")
				.addMapConfig(new MapConfig()
						.setName("users")
						.setBackupCount(3)
						.setMapStoreConfig(new MapStoreConfig().setEnabled(true).setWriteBatchSize(5))
						.setEvictionConfig(new EvictionConfig().setSize(100).setEvictionPolicy(EvictionPolicy.LRU))
						.setTimeToLiveSeconds(20));
	}

}