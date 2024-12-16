package com.jcinema.users.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jcinema.users.audit.AuditAwareImpl;
import com.jcinema.users.dto.UsersDto;
import com.jcinema.users.service.impl.UsersServiceImpl;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(controllers = UsersController.class, excludeFilters = {
    @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {AuditAwareImpl.class})
})
public class UsersControllerTest {
    
    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @Mock
    private UsersServiceImpl usersService;

    @InjectMocks
    private UsersController usersController;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void testCreateUser() throws Exception {
        //Arrange
        String username = "testuser";
        UsersDto usersDto = new UsersDto();
        usersDto.setUsername(username);
        usersDto.setEmail(username + "@test.com");
        usersDto.setPassword("testpassword");

        // Mocking the service methods
        when(usersService.registerUser(any(UsersDto.class))).thenReturn(usersDto);

        //Act
        mockMvc.perform(post("/api/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(usersDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value(username))
                .andExpect(jsonPath("$.email").value(username + "@test.com"));
    }
}