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
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * @author rp
 */
@Configuration
@MapperScan(basePackages = "com.xypsp.admin.domain.mapper.admin", sqlSessionFactoryRef = "adminSqlSessionFactory")
public class AdminSessionFactoryConfiguration {

    @Bean(name = "adminDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.admin")
    public DataSource adminDataSource() {
        return DataSourceBuilder.create().build();
    }

    /**
     * 默认使用此事务管理器  如果不想使用 则使用 @Transactional(transactionManager = "transactionManagerWeb") 来指定其他的事务处理器
     * */
    @Bean(name = "transactionManagerAdmin")
    @Qualifier(value = "admin")
    @Primary
    public DataSourceTransactionManager transactionManagerAdmin() {
        return new DataSourceTransactionManager(adminDataSource());
    }

    @Bean(name = "adminSqlSessionFactory")
    public SqlSessionFactory activitySqlSessionFactory(@Qualifier("adminDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sfb = new SqlSessionFactoryBean();
        sfb.setDataSource(dataSource);
        sfb.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mappers/admin/**.xml"));
        sfb.setTypeAliasesPackage("com.xypsp.admin.domain.model.admin");
        sfb.setVfs(SpringBootVFS.class);
        SqlSessionFactory factory = sfb.getObject();
        assert factory != null;
        factory.getConfiguration().setMapUnderscoreToCamelCase(true);
        return factory;
    }

    /**
     * liquibase配置
     * */
    @Bean(name = "adminLiquibaseProperties")
    @ConfigurationProperties(prefix = "spring.datasource.admin.liquibase")
    public LiquibaseProperties adminLiquibaseProperties() {
        return new LiquibaseProperties();
    }

    @Bean(name = "adminLiquibase")
    public SpringLiquibase adminLiquibase() {
        return springLiquibase(adminDataSource(), adminLiquibaseProperties());
    }

    private static SpringLiquibase springLiquibase(DataSource dataSource, LiquibaseProperties properties) {
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setDataSource(dataSource);
        liquibase.setChangeLog(properties.getChangeLog());
        liquibase.setContexts(properties.getContexts());
        liquibase.setDefaultSchema(properties.getDefaultSchema());
        liquibase.setDropFirst(properties.isDropFirst());
        liquibase.setShouldRun(properties.isEnabled());
        liquibase.setLabels(properties.getLabels());
        liquibase.setChangeLogParameters(properties.getParameters());
        liquibase.setRollbackFile(properties.getRollbackFile());
        return liquibase;
    }

}
