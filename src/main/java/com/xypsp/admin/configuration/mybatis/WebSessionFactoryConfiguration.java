package com.xypsp.admin.configuration.mybatis;

import liquibase.integration.spring.SpringLiquibase;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.SpringBootVFS;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.util.Objects;


/**
 * @author rp
 */
@Configuration
@MapperScan(basePackages = "com.xypsp.admin.domain.mapper.web", sqlSessionFactoryRef = "webSqlSessionFactory")
public class WebSessionFactoryConfiguration {

    @Bean(name = "webDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.web")
    public DataSource webDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Qualifier(value = "web")
    @Bean(name = "transactionManagerWeb")
    public DataSourceTransactionManager transactionManagerWeb() {
        return new DataSourceTransactionManager(webDataSource());
    }

    @Bean(name = "webSqlSessionFactory")
    public SqlSessionFactory activitySqlSessionFactory(@Qualifier("webDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sfb = new SqlSessionFactoryBean();
        sfb.setDataSource(dataSource);
        sfb.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mappers/web/**.xml"));
        sfb.setTypeAliasesPackage("com.xypsp.admin.domain.model.web");
        sfb.setVfs(SpringBootVFS.class);
        SqlSessionFactory factory = sfb.getObject();
        Objects.requireNonNull(factory).getConfiguration().setMapUnderscoreToCamelCase(true);
        return factory;
    }

    /**
     * liquibase配置
     * */
    @Bean(name = "webLiquibaseProperties")
    @ConfigurationProperties(prefix = "spring.datasource.web.liquibase")
    public LiquibaseProperties webLiquibaseProperties() {
        return new LiquibaseProperties();
    }

    @Bean(name = "webLiquibase")
    public SpringLiquibase webLiquibase() {
        return springLiquibase(webDataSource(), webLiquibaseProperties());
    }

    private static SpringLiquibase springLiquibase(DataSource dataSource, LiquibaseProperties properties) {
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setDataSource(dataSource);
        liquibase.setChangeLog(properties.getChangeLog());
        liquibase.setDefaultSchema(properties.getDefaultSchema());
        liquibase.setContexts(properties.getContexts());
        liquibase.setDropFirst(properties.isDropFirst());
        liquibase.setShouldRun(properties.isEnabled());
        liquibase.setChangeLogParameters(properties.getParameters());
        liquibase.setLabels(properties.getLabels());
        liquibase.setRollbackFile(properties.getRollbackFile());
        return liquibase;
    }

}

