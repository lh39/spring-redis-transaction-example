package de.lh39.springredistransactionexample.configuration;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.transaction.PlatformTransactionManager;

/**
 * Redis configuration.
 *
 * @author lh39
 */
@Configuration
public class RedisConfiguration {

  @Bean
  JedisConnectionFactory jedisConnectionFactory() {
    JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
    return jedisConnectionFactory;
  }

  @Bean
  public RedisTemplate<String, Object> redisTemplate() {
    RedisTemplate<String, Object> template = new RedisTemplate<>();
    template.setConnectionFactory(jedisConnectionFactory());
    template.setEnableTransactionSupport(true);
    template.afterPropertiesSet();
    return template;
  }

  //Transaction datasource
  @Bean
  public DataSource dataSource() throws SQLException {
    EmbeddedDatabaseBuilder databaseBuilder = new EmbeddedDatabaseBuilder();
    databaseBuilder.setName("transactionDatabase");
    databaseBuilder.setType(EmbeddedDatabaseType.H2);
    return databaseBuilder.build();
  }

  @Bean
  public PlatformTransactionManager transactionManager() throws SQLException {
    return new DataSourceTransactionManager(dataSource());
  }

}
