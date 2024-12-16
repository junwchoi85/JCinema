package com.jcinema.users.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.jcinema.users.dto.UsersDto;
import com.jcinema.users.entity.Users;
import com.jcinema.users.mapper.UsersMapper;
import com.jcinema.users.repository.UsersRepository;
import com.jcinema.users.service.impl.UsersServiceImpl;

@ExtendWith(MockitoExtension.class)
public class UsersServiceTest {
    
    @InjectMocks
    private UsersServiceImpl usersService;

    @Mock
    private UsersRepository usersRepository;

    @Test
    public void testRegisterUser() {
        //Arrange
        String username = "testuser";
        UsersDto usersDto = new UsersDto();
        usersDto.setUsername(username);
        usersDto.setEmail(username + "@test.com");
        usersDto.setPassword("testpassword");

        // Mapping the DTO to the entity
        Users user = UsersMapper.mapToEntity(usersDto, new Users());

        // Mocking the repository methods
        when(usersRepository.findByUsername(username)).thenReturn(Optional.empty());
        when(usersRepository.save(any(Users.class))).thenReturn(user);

        //Act
        UsersDto result = usersService.registerUser(usersDto);

        //Assert
        assertNotNull(result);
        assertEquals(result.getUsername(), username);
    }

    @Test
    public void testRegisterUserAlreadyExists() {
        //Arrange
        String username = "testuser";
        UsersDto usersDto = new UsersDto();
        usersDto.setUsername(username);
        usersDto.setEmail(username + "@test.com");
        usersDto.setPassword("testpassword");

        // Mapping the DTO to the entity
        Users user = UsersMapper.mapToEntity(usersDto, new Users());

        // Mocking the repository methods
        when(usersRepository.findByUsername(username)).thenReturn(Optional.of(user));

        //Act and Assert
        try {
            usersService.registerUser(usersDto);
        } catch (IllegalArgumentException e) {
            assertEquals(e.getMessage(), "User already exists");
        }
    }

    @Test
    public void testFetchUser() {
        //Arrange
        String username = "testuser";
        UsersDto usersDto = new UsersDto();
        usersDto.setUsername(username);
        usersDto.setEmail(username + "@test.com");
        usersDto.setPassword("testpassword");

        // Mapping the DTO to the entity
        Users user = UsersMapper.mapToEntity(usersDto, new Users());

        // Mocking the repository methods
        when(usersRepository.findByUsername(username)).thenReturn(Optional.of(user));

        //Act
        UsersDto result = usersService.getUser(username);

        //Assert
        assertNotNull(result);
        assertEquals(result.getUsername(), username);
    }

    @Test
    public void testUpdateUser() {
        //Arrange
        String username = "testuser";
        UsersDto usersDto = new UsersDto();
        usersDto.setUsername(username);
        usersDto.setEmail(username + "@test.com");
        usersDto.setPassword("testpassword");

        // Mapping the DTO to the entity
        Users user = UsersMapper.mapToEntity(usersDto, new Users());

        // Mocking the repository methods
        when(usersRepository.findByUsername(username)).thenReturn(Optional.of(user));
        when(usersRepository.save(any(Users.class))).thenReturn(user);

        //Act
        boolean result = usersService.updateUser(usersDto);

        //Assert
        assertTrue(result);
    }

    @Test
    public void testDeleteUser() {
        //Arrange
        String username = "testuser";
        UsersDto usersDto = new UsersDto();
        usersDto.setUsername(username);
        usersDto.setEmail(username + "@test.com");
        usersDto.setPassword("testpassword");

        // Mapping the DTO to the entity
        Users user = UsersMapper.mapToEntity(usersDto, new Users());

        // Mocking the repository methods
        when(usersRepository.findByUsername(username)).thenReturn(Optional.of(user));

        //Act
        boolean result = usersService.deleteUser(username);

        //Assert
        assertTrue(result);
    }
}
