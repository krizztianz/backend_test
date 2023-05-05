package com.kristianandi.BackendTest.Rest;

import com.kristianandi.BackendTest.Core.Domain.ApiResponse;
import com.kristianandi.BackendTest.Core.Domain.Login;
import com.kristianandi.BackendTest.Core.Domain.User;
import com.kristianandi.BackendTest.Core.Interfaces.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import javax.security.auth.login.CredentialNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse Register(@Valid @RequestBody User newUser)
    {
        User user = userRepository.save(newUser);
        ApiResponse response = new ApiResponse(201, "Created");
        response.data = user;

        return  response;
    }

    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse Login(@Valid @RequestBody Login loginData) throws Exception
    {
        ApiResponse result = new ApiResponse();
        List<User> listUsers = userRepository.findByPhoneNumberAndPassword(loginData.getPhoneNumber(), loginData.getPassword());
        Boolean found = (listUsers.size() > 0);

        if(found)
        {
            result.statusCode = 200;
            result.message = "OK";
            result.data = listUsers;
        }
        else
        {
            throw new CredentialNotFoundException("User not found!");
        }

        return result;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(CredentialNotFoundException.class)
    public Map<String, String> handleValidationExceptions(CredentialNotFoundException ex) {
        Map<String, String> errors = new HashMap<>();

        errors.put("message", ex.getMessage());

        return errors;
    }
}
