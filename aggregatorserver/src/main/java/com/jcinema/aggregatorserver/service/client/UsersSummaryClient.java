package com.jcinema.aggregatorserver.service.client;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;

import com.jcinema.aggregatorserver.dto.UsersDto;

import reactor.core.publisher.Mono;

public interface UsersSummaryClient {
    @GetExchange(value = "/jcinema/users/api/fetch", accept = "application/json")
    Mono<ResponseEntity<UsersDto>> fetchUserDetails(@RequestParam String userName);
}
