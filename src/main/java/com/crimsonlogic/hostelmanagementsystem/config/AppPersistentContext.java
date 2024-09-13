package com.crimsonlogic.hostelmanagementsystem.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@PropertySource("classpath:database.properties")
@EnableTransactionManagement
@ComponentScan("com.crimsonlogic.hostelmanagementsystem")
@EnableJpaRepositories(basePackages = "com.crimsonlogic.hostelmanagementsystem.repository")
public class AppPersistentContext {

	@Autowired // used for injecting the dependencies(to implement IOC in spring framework)
	private Environment environment;

	/*
	 * @Bean public LocalSessionFactoryBean sessionFactory() {
	 * LocalSessionFactoryBean lsfb = new LocalSessionFactoryBean();
	 * lsfb.setDataSource(dataSource()); // reading all the classes under the
	 * specified model lsfb.setPackagesToScan(new
	 * String("com.crimsonlogic.springmvcwithdatajpa.entity"));
	 * lsfb.setHibernateProperties(hibernateProperties()); return lsfb; }
	 */

	@Bean
	public Properties hibernateProperties() {
		Properties prop = new Properties();
		prop.put("hibernate.dialect", environment.getRequiredProperty("hibernate.dialect"));
		prop.put("hibernate.show_sql", environment.getRequiredProperty("hibernate.show_sql"));
		prop.put("hibernate.format_sql", environment.getRequiredProperty("hibernate.format_sql"));
		prop.put("hibernate.hbm2ddl.auto", environment.getRequiredProperty("hibernate.hbm2ddl.auto"));
		return prop;
	}

	/*
	 * @Bean public HibernateTransactionManager transactionManager() {
	 * HibernateTransactionManager htm = new HibernateTransactionManager();
	 * htm.setSessionFactory(sessionFactory().getObject()); return htm; }
	 */

	@Bean
	public PlatformTransactionManager transactionManager(final EntityManagerFactory emf) {
		final JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(emf);
		return transactionManager;
	}

	@Bean
	public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
		return new PersistenceExceptionTranslationPostProcessor();
	}

	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dmds = new DriverManagerDataSource();
		dmds.setDriverClassName(environment.getRequiredProperty("jdbc.driverClassName"));
		dmds.setUrl(environment.getRequiredProperty("jdbc.url"));
		dmds.setUsername(environment.getRequiredProperty("jdbc.username"));
		dmds.setPassword(environment.getRequiredProperty("jdbc.password"));
		return dmds;
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		final LocalContainerEntityManagerFactoryBean lcmfb = new LocalContainerEntityManagerFactoryBean();
		lcmfb.setDataSource(dataSource());
		lcmfb.setPackagesToScan(new String[] { "com.crimsonlogic.hostelmanagementsystem.entity" });
		final HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		lcmfb.setJpaVendorAdapter(vendorAdapter);
		lcmfb.setJpaProperties(hibernateProperties());
		return lcmfb;
	}
}
