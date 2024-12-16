package com.jcinema.users.service;

import com.jcinema.users.dto.UsersDto;

public interface IUsersService {
    public UsersDto registerUser(UsersDto usersDto);
    public boolean updateUser(UsersDto usersDto);
    public boolean deleteUser(String username);
    public UsersDto getUser(String username);
}
