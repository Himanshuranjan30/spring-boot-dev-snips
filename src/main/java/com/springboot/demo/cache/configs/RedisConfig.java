package com.springboot.demo.cache.configs;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.connection.RedisPassword;
//import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
//import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
//import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
//
//import java.time.Duration;
//
//@Configuration
//public class RedisConfig {
//
////    @Value("${redis.host}")
////    private String host;
////
////    @Value("${redis.port}")
////    private Integer port;
////
////    @Value("${redis.database}")
////    private Integer database;
////
////    @Value("${redis.password}")
////    private String password;
//
//    @Bean
//    JedisConnectionFactory jedisConnectionFactory(){
//        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
//        redisStandaloneConfiguration.setDatabase(0);
//        redisStandaloneConfiguration.setHostName("localhost");
//        redisStandaloneConfiguration.setPassword(RedisPassword.of(""));
//        redisStandaloneConfiguration.setPort(6379);
//
//        //setting jedi config using builder pattern
//        JedisClientConfiguration.JedisClientConfigurationBuilder jedisClientConfiguration = JedisClientConfiguration.builder();
//        jedisClientConfiguration.connectTimeout(Duration.ofSeconds(60));
//        return new JedisConnectionFactory(redisStandaloneConfiguration,jedisClientConfiguration.build());
//    }
//
//    @Bean
//    public RedisTemplate<String,Object> redisTemplate(){
//        RedisTemplate<String,Object> template = new RedisTemplate<>();
//        template.setConnectionFactory(jedisConnectionFactory());
//        return template;
//
//    }
//
//}

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.spring.cache.CacheConfig;
import org.redisson.spring.cache.RedissonSpringCacheManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class RedisConfig{

    @Value("${redis.host}")
    private String host;

    @Value("${redis.port}")
    private Integer port;

    private static final long ONE_MONTH = 2419200000L;

    @Bean
    RedissonClient redisson() {
        Config config = new Config();
        config.useSingleServer().setAddress(String.format("redis://%s:%s",host,port));
        return Redisson.create(config);
    }

    //maxIdleTime enables cached object to be kept in as long as it is requested in periods shorter than maxIdleTime
    //ttl will make the cached object be invalidated after that many seconds regardless of how many times or when it was requested.
    @Bean
    public CacheManager cacheManager() {
        Map<String, CacheConfig> config = new HashMap<>();
        //setting ttl and maxidleTime
        config.put("USER_DATA_CONFIG",new CacheConfig(ONE_MONTH,ONE_MONTH));
        return new RedissonSpringCacheManager(redisson(), config);
    }

}