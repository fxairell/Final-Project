package com.airell.bus.config;

import javax.sql.DataSource;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

/*
 * Ini adalah tag Configuration
 * Easy access untuk ke database SQL
 */
@Configuration
public class FlywayConfiguration {
	/*
	 * Dihubungkan untuk konfigurasinya
	 * Menggunakan parameter Datasource yang merupakan databasenya
	 */
	@Autowired
	public FlywayConfiguration(DataSource dataSource) {
		Flyway.configure().baselineOnMigrate(true).dataSource(dataSource).load().migrate();
	}
}
