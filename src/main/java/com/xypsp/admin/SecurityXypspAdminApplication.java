package com.xypsp.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author renpei
 */
@EnableAsync
@SpringBootApplication
public class SecurityXypspAdminApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecurityXypspAdminApplication.class, args);
	}
}
