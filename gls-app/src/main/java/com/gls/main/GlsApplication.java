package com.gls.main;

import java.io.IOException;

import org.dozer.DozerBeanMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import com.maxmind.geoip2.DatabaseReader;

@Configuration
@EnableAutoConfiguration
@ComponentScan("com.gls.*")
public class GlsApplication {

	public static void main(String[] args) {
		SpringApplication.run(GlsApplication.class, args);
	}

	@Bean
	public DatabaseReader databaseReader() throws IOException {
		return new DatabaseReader(new ClassPathResource("GeoLite2-City.mmdb").getFile());
	}

	@Bean
	public DozerBeanMapper dozerBeanMapper() {
		return new DozerBeanMapper();
	}

}
