package com.jcinema.users.repository;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import com.jcinema.users.audit.AuditAwareImpl;
import com.jcinema.users.entity.Users;

@DataJpaTest
@Import(AuditAwareImpl.class)
public class UsersRepositoryTest {
    
    @Autowired
    private UsersRepository usersRepository;

    @Test
    public void testRegisterUser() {
        //Arrange
        String username = "testuser";
        Users users = new Users();
        users.setUsername(username);
        users.setEmail(username + "@test.com");
        users.setPassword("testpassword");

        //Act
        Users savedUser = usersRepository.save(users);

        //Assert
        assert(savedUser.getId() != null);
        assert(savedUser.getUsername().equals(username));
    }

    @Test
    public void testFindByUsername() {
        //Arrange
        String username = "testuser";
        Users users = new Users();
        users.setUsername(username);
        users.setEmail(username + "@test.com");
        users.setPassword("testpassword");
        usersRepository.save(users);

        // Debug: Verify that the user was saved
        Optional<Users> savedUser = usersRepository.findByUsername(username);
        System.out.println("Saved User: " + savedUser);

        //Act
        Optional<Users> foundUser = usersRepository.findByUsername(username);

        // Debug: Verify that the user was found
        System.out.println("Found User: " + foundUser);

        //Assert
        assert(foundUser.isPresent());
        assert(foundUser.get().getUsername().equals(username));
    }

    @Test
    public void testFindByEmail() {
        //Arrange
        String email = "email@test.com";
        Users users = new Users();
        users.setUsername("testuser");
        users.setEmail(email);
        users.setPassword("testpassword");
        usersRepository.save(users);

        //Act
        Optional<Users> foundUser = usersRepository.findByEmail(email);

        //Assert
        assert(foundUser.isPresent());
        assert(foundUser.get().getEmail().equals(email));
    }

    @Test
    public void testUpdateUser() {
        //Arrange
        String username = "testuser";
        Users users = new Users();
        users.setUsername(username);
        users.setEmail(username + "@test.com");
        users.setPassword("testpassword");
        Users savedUser = usersRepository.save(users);

        String updatedEmail = "updatedEmail@test.com";

        //Act
        savedUser.setEmail(updatedEmail);
        Users updatedUser = usersRepository.save(savedUser);

        //Assert
        assert(updatedUser.getEmail().equals(updatedEmail));
    }

    @Test
    public void testDeleteUser() {
        //Arrange
        String username = "testuser";
        Users users = new Users();
        users.setUsername(username);
        users.setEmail(username + "@test.com");
        users.setPassword("testpassword");
        Users savedUser = usersRepository.save(users);

        //Act
        usersRepository.delete(savedUser);

        //Assert
        assert(usersRepository.findByUsername(username).isEmpty());
    }
}
