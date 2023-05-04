package com.kristianandi.BackendTest.Rest;

import com.kristianandi.BackendTest.Core.Domain.ApiResponse;
import com.kristianandi.BackendTest.Core.Domain.User;
import com.kristianandi.BackendTest.Core.Interfaces.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/user")
    private List<User> GetAllUser()
    {
        List<User> listUsers = userRepository.findAll();

        return listUsers;
    }

    @PostMapping(value = "/user", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    private ApiResponse CreateUser(@RequestBody User newUser)
    {
        User user = userRepository.save(newUser);
        ApiResponse response = new ApiResponse(201, "Created");
        response.data = user;

        return  response;
    }

}
