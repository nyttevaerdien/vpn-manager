package com.kotprotiv.vpn_manager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;

@EnableRetry
@SpringBootApplication
public class VpnManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(VpnManagerApplication.class, args);
	}

}
