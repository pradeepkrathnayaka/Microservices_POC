package com.cg.fm.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.cg.fm.util.Constant;

@Profile("prod")
@Configuration
@EnableTransactionManagement(proxyTargetClass = true)
@EnableJpaRepositories(Constant.JPA_REPO_PACKAGE)
public class DBConfigPG {
	
	@Value("${db.username}")
    String userName;

    @Value("${db.password}")
    String password;

    @Value("${db.url}")
    String url;

    @Value("${db.driver}")
    String driver;

    @Value("${db.dialect}")
    String dialect;

    /**
     * @return DriverManagerDataSource
     */
    @Bean(name = "datasource")
    public DriverManagerDataSource getDriverManagerDataSource() {
        DriverManagerDataSource driverManagerDataSource =
            new DriverManagerDataSource();
        driverManagerDataSource.setUsername(userName);
        driverManagerDataSource.setPassword(password);
        driverManagerDataSource.setUrl(url);
        driverManagerDataSource.setDriverClassName(driver);
        return driverManagerDataSource;
    }

    /**
     * @return LocalContainerEntityManagerFactoryBean
     */
    @Bean(name = "entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean getLocalContainerEntityManagerFactoryBean() {
        LocalContainerEntityManagerFactoryBean emf =
            new LocalContainerEntityManagerFactoryBean();
        HibernateJpaVendorAdapter vendor = new HibernateJpaVendorAdapter();
        vendor.setShowSql(true);
        vendor.setGenerateDdl(true);
        vendor.setDatabasePlatform(dialect);
        emf.setJpaVendorAdapter(vendor);
        emf.setDataSource(getDriverManagerDataSource());
        emf.setPackagesToScan(Constant.ENTITY_SCAN_PACKAGE);
        return emf;
    }
}
