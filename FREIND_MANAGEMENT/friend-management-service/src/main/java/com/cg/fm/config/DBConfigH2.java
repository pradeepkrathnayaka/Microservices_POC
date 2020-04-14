package com.cg.fm.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.cg.fm.util.Constant;

import lombok.extern.slf4j.Slf4j;

@Profile("dev")
@Configuration
@EnableTransactionManagement(proxyTargetClass = true)
@EnableJpaRepositories(Constant.JPA_REPO_PACKAGE)
@Slf4j
public class DBConfigH2 {
	static {
		log.info("Start DBConfigH2......");
	}
	
	@Profile("dev")
	public DataSource dataSource() {
		EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
		return builder.setType(EmbeddedDatabaseType.H2)
				.addScript("schema.sql")
				.addScript("data.sql")
				.build();
	}

	@Bean
	public JdbcTemplate createJdbcTeamplate() {
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		jdbcTemplate.setDataSource(dataSource());
		return jdbcTemplate;
	}
}