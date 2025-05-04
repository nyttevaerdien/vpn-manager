package com.kotprotiv.vpn_manager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.retry.annotation.EnableRetry;

@EnableRetry
@ServletComponentScan
@SpringBootApplication
@EnableJpaRepositories
public class VpnManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(VpnManagerApplication.class, args);
	}

}
