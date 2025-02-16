package com.jcinema.aggregatorserver.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.function.ServerRequest;
import org.springframework.web.servlet.function.ServerResponse;

import com.jcinema.aggregatorserver.dto.UsersDto;
import com.jcinema.aggregatorserver.service.client.UsersSummaryClient;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class UsersCompositeHandler {
    private final UsersSummaryClient usersClient;

    public Mono<ServerResponse> fetchUserDetails(ServerRequest request) {
        String userName = request.pathVariable("userName");
       
        Mono<ResponseEntity<UsersDto>> usersDetail = usersClient.fetchUserDetails(userName);
        
        return Mono.zip(usersDetail).flatMap(tuple -> {
            ResponseEntity<UsersDto> users = tuple.getT1();

            UsersSummaryDto UsersSummaryDto = new UsersSummaryDto(users.getBody());

            return ServerResponse.ok().contentType(APPLICATION_JSON).body(BodyInserters.fromValue(UsersSummaryDto));
        });
    }
}
