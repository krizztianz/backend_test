package com.kristianandi.BackendTest.Rest;

import com.kristianandi.BackendTest.Core.Domain.ApiResponse;
import com.kristianandi.BackendTest.Core.Domain.User;
import com.kristianandi.BackendTest.Core.Interfaces.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/register")
    public ApiResponse Register()
    {
        return new ApiResponse(200, "OK");
    }
}
