package ddd.demo.infrastructure.repository;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import java.sql.Connection;

/**
 * Created by lil.yang on 2017/6/4.
 */
@Configuration
@PropertySource("file:${user.dir}/config/datasource.properties")
public class DataSourceConfig {
    @Value("${prop.jdbc.url}")
    private String url;
    @Value("${prop.jdbc.username}")
    private String username;
    @Value("${prop.jdbc.password}")
    private String password;

    @Bean
    public DataSource getDataSource() {
        DataSource ds = new DataSource();
        ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
        ds.setUrl(this.url);
        ds.setUsername(this.username);
        ds.setPassword(this.password);
        ds.setDefaultAutoCommit(false);
        ds.setDefaultTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
        ds.setInitialSize(10);
        ds.setMinIdle(3);
        ds.setMaxIdle(10);
        ds.setMaxActive(100);
        ds.setMaxWait(3000);
        ds.setValidationInterval(600000);
        ds.setTestWhileIdle(true);
        ds.setValidationQuery("SELECT 1");
        ds.setTimeBetweenEvictionRunsMillis(120000);
        ds.setMinEvictableIdleTimeMillis(300000);
        ds.setRemoveAbandoned(true);
        ds.setRemoveAbandonedTimeout(28800);
        return ds;
    }

    @Bean
    public DataSourceTransactionManager getTransactionManager() {
        DataSourceTransactionManager trans = new DataSourceTransactionManager(
                getDataSource());
        return trans;
    }

    @Bean
    public TransactionTemplate transactionTemplate() {
        return new TransactionTemplate(getTransactionManager());
    }

    @Bean
    public SqlSessionFactoryBean getSqlSessionFactoryBean() {
        SqlSessionFactoryBean factory = new SqlSessionFactoryBean();
        factory.setDataSource(getDataSource());

        ClassPathResource classPathResource = new ClassPathResource(
                "SqlMapConfig.xml"); //ddd/demo/infrastructure/repository/
        factory.setConfigLocation(classPathResource);
        return factory;
    }

    @Bean
    public SqlSessionTemplate getSqlSessionTemplate(@Autowired SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}