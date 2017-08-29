package com.pl10.dermalif;

import javax.annotation.Resource;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.pl10.dermalif.service.StorageService;

@SpringBootApplication
public class DermalifApplication implements CommandLineRunner{
	
	@Resource
	StorageService storageService;

	public static void main(String[] args) {
		SpringApplication.run(DermalifApplication.class, args);
		
	}
	
	@Override
	public void run(String... args) throws Exception {
		storageService.init();
	}
}
