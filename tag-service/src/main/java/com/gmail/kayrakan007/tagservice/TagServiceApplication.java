package com.gmail.kayrakan007.tagservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import com.gmail.kayrakan007.tagservice.client.CustomErrorDecoder;

import feign.codec.ErrorDecoder;

@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class TagServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TagServiceApplication.class, args);
	}

    @Bean
    ErrorDecoder errorDecoder() {
		return new CustomErrorDecoder();
	}

}
