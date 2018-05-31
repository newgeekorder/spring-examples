package com.waitrose.ecomm.services.saleforceetl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
public class SaleforceEtlApplication {

	@Autowired
	EtlRepository etlRepository;


	public static void main(String[] args) {
		String [] debug  = {"--debug"};
		SpringApplication.run(SaleforceEtlApplication.class, debug);
	}
}
