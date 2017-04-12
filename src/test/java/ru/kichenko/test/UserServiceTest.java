package ru.kichenko.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;

import ru.kichenko.service.UserService;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserServiceTest {
    
    @Autowired
    private UserService userService;

    @Test(expected = AuthenticationCredentialsNotFoundException.class)
    public void testNotAuthenticated() {
        userService.findAllUsers();
    }

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    public void testAuthenticated() {
        userService.findAllUsers();
    }

    @Test(expected = AccessDeniedException.class)
    @WithMockUser(username = "user", roles = "USER")
    public void testAccessDenied() {
        userService.findAllUsers();
    }
}
