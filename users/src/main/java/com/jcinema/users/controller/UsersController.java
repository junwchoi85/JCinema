package com.jcinema.users.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jcinema.users.constants.UsersConstants;
import com.jcinema.users.dto.ResponseDto;
import com.jcinema.users.dto.UsersDto;
import com.jcinema.users.service.IUsersService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "/api", produces = { MediaType.APPLICATION_JSON_VALUE })
@AllArgsConstructor
@Validated
public class UsersController {

    private IUsersService usersService;

    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createUser(@Valid @RequestBody UsersDto usersDto) {
        UsersDto result = usersService.registerUser(usersDto);
        if(result == null) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(UsersConstants.STATUS_417, UsersConstants.MESSAGE_417_CREATE));
        }
        return ResponseEntity.ok(new ResponseDto("200", "User created successfully"));
    }

    @GetMapping("/fetch")
    public ResponseEntity<UsersDto> fetchUserDetails(@RequestParam String userName) {
        UsersDto usersDto = usersService.getUser(userName);
        return ResponseEntity.status(HttpStatus.OK).body(usersDto);
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateUserDetails(@Valid @RequestBody UsersDto usersDto) {
        boolean isUpdated = usersService.updateUser(usersDto);
        if (isUpdated) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseDto(UsersConstants.STATUS_200, UsersConstants.MSG_200_UPDATE));
        } else {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(UsersConstants.STATUS_417, UsersConstants.MESSAGE_417_UPDATE));
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteUser(@PathVariable String userName) {
        boolean isDeleted = usersService.deleteUser(userName);
        if (isDeleted) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseDto(UsersConstants.STATUS_200, UsersConstants.USER_DELETED));
        } else {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(UsersConstants.STATUS_417, UsersConstants.MESSAGE_417_DELETE));
        }
    }

    @GetMapping("ping")
    public String ping() {
        return "pong";
    }

}
