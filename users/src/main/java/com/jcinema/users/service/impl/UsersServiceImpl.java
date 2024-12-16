package com.jcinema.users.service.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.jcinema.users.dto.UsersDto;
import com.jcinema.users.entity.Users;
import com.jcinema.users.exception.ResourceNotFoundException;
import com.jcinema.users.mapper.UsersMapper;
import com.jcinema.users.repository.UsersRepository;
import com.jcinema.users.service.IUsersService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UsersServiceImpl implements IUsersService {
    private UsersRepository usersRepository;

    @Override
    public UsersDto registerUser(UsersDto usersDto) {
        Users user = UsersMapper.mapToEntity(usersDto, new Users());
        Optional<Users> optionalUser = usersRepository.findByUsername(user.getUsername());
        if (optionalUser.isPresent()) {
            throw new IllegalArgumentException("User already exists");
        }
        Users savedUser = usersRepository.save(user);
        return UsersMapper.convertToDto(savedUser);
    }

    @Override
    public boolean updateUser(UsersDto usersDto) {
        Users user = usersRepository.findByUsername(usersDto.getUsername())
            .orElseThrow(() -> new ResourceNotFoundException("Users", "username", usersDto.getUsername()));
        Users updatedUser = UsersMapper.mapToEntity(usersDto, user);
        usersRepository.save(updatedUser);
        return true;
    }

    @Override
    public boolean deleteUser(String username) {
        Users user = usersRepository.findByUsername(username)
            .orElseThrow(() -> new ResourceNotFoundException("Users", "username", username));
        usersRepository.deleteById(user.getId());
        return true;
    }

    @Override
    public UsersDto getUser(String username) {
        Users user = usersRepository.findByUsername(username)
            .orElseThrow(() -> new ResourceNotFoundException("Users", "username", username));

        return UsersMapper.convertToDto(user);
    }
}
