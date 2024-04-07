package com.gmail.kayrakan007.tagservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "post-service")
public interface PostClient {
	
	@GetMapping("/exists/{id}")
	public boolean existsById(@PathVariable("id") Long id);

}
