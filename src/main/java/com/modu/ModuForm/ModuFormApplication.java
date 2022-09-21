package com.modu.ModuForm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication
public class ModuFormApplication {
	public static void main(String[] args) {
		SpringApplication.run(ModuFormApplication.class, args);
	}
}
