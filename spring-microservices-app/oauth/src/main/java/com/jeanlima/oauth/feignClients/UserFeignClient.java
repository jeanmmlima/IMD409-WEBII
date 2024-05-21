package com.jeanlima.oauth.feignClients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jeanlima.oauth.entities.User;

@Component
@FeignClient(name = "user", path ="/users")
public interface UserFeignClient {

    @GetMapping(value = "/search")
	ResponseEntity<User> findByEmail(@RequestParam String email);
    
}
