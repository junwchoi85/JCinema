package com.jcinema.users.mapper;

import com.jcinema.users.dto.UsersDto;
import com.jcinema.users.entity.Users;

public class UsersMapper {
    public static UsersDto mapToDto(Users users, UsersDto usersDto) {
        usersDto.setUsername(users.getUsername());
        usersDto.setEmail(users.getEmail());
        usersDto.setPassword(users.getPassword());
        return usersDto;
    }

    public static Users mapToEntity(UsersDto usersDto, Users users) {
        users.setUsername(usersDto.getUsername());
        users.setEmail(usersDto.getEmail());
        users.setPassword(usersDto.getPassword());
        return users;
    }

    public static UsersDto convertToDto(Users users) {
        UsersDto usersDto = new UsersDto();
        usersDto.setUsername(users.getUsername());
        usersDto.setEmail(users.getEmail());
        usersDto.setPassword(users.getPassword());
        return usersDto;
    }

    public static Users convertToEntity(UsersDto usersDto) {
        Users users = new Users();
        users.setUsername(usersDto.getUsername());
        users.setEmail(usersDto.getEmail());
        users.setPassword(usersDto.getPassword());
        return users;
    }
}
