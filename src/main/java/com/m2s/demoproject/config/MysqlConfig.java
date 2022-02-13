package com.m2s.demoproject.config;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableJpaRepositories(entityManagerFactoryRef = "mysqlEntityManager", transactionManagerRef = "mysqlTransactionManager", basePackages = {
		"com.m2s.demoproject.repository" })
public class MysqlConfig {

	@Autowired
	JpaProperties jpaProperties;

	@Primary
	@Bean(name = "mysqlDatasource")
	@ConfigurationProperties(prefix = "spring.datasource")
	public DataSource datasource() {
		return DataSourceBuilder.create().build();
	}

	@Bean(name="mysqlEntityManager")
	public LocalContainerEntityManagerFactoryBean mysqlEntityManager(EntityManagerFactoryBuilder builder,
			DataSource dataSource) {
		jpaProperties.getProperties().put("hibernate.implicit_naming_strategy",
				"org.springframework.boot.orm.jpa.hibernate.SpringImplicitNamingStrategy");
		jpaProperties.getProperties().put("hibernate.physical_naming_strategy",
				"org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy");

		return builder.dataSource(dataSource).properties(jpaProperties.getProperties())
				.packages("com.m2s.demoproject.entity").persistenceUnit("mysqlPU").build();
	}

	@Bean(name="mysqlTransactionManager")
	public PlatformTransactionManager mysqlTransanctionManager(EntityManagerFactory emf) {
		return new JpaTransactionManager(emf);
	}
}
