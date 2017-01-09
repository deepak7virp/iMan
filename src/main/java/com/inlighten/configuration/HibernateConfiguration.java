package com.inlighten.configuration;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@ComponentScan({ "com.inlighten.configuration" })
@PropertySource(value = { "classpath:application.properties" })
public class HibernateConfiguration {

    @Autowired
    private Environment environment;

    @Bean
    public LocalSessionFactoryBean sessionFactory() {
    	System.out.println("Inside sessionFactory() of HibernateConfiguration");
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan(new String[] { "com.inlighten.model" });
        sessionFactory.setHibernateProperties(hibernateProperties());
        return sessionFactory;
     }
	
    @Bean
    public DataSource dataSource() {
    	System.out.println("Inside dataSource() of HibernateConfiguration");
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(environment.getRequiredProperty("jdbc.driverClassName"));
        dataSource.setUrl(environment.getRequiredProperty("jdbc.url"));
        
        return dataSource;
    }
    
    private Properties hibernateProperties() {
    	System.out.println("Inside hibernateProperties() of HibernateConfiguration");
        Properties properties = new Properties();
        
        properties.put("hibernate.dialect", environment.getRequiredProperty("hibernate.dialect"));
        return properties;        
    }
    
	@Bean
    @Autowired
    public HibernateTransactionManager transactionManager(SessionFactory s) {
		System.out.println("Inside transactionManager() of HibernateConfiguration");
       HibernateTransactionManager txManager = new HibernateTransactionManager();
       txManager.setSessionFactory(s);
       return txManager;
    }
}

