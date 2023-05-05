package com.kristianandi.BackendTest.Rest;

import com.kristianandi.BackendTest.Core.Domain.ApiResponse;
import com.kristianandi.BackendTest.Core.Domain.Login;
import com.kristianandi.BackendTest.Core.Domain.User;
import com.kristianandi.BackendTest.Core.Interfaces.UserDAO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.CredentialNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserDAO userDAO;

    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse Register(@Valid @RequestBody User newUser)
    {
        User user = userDAO.save(newUser);
        ApiResponse response = new ApiResponse(201, "Created");
        response.data = user;

        return  response;
    }

    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse Login(@Valid @RequestBody Login loginData) throws Exception
    {
        ApiResponse result = new ApiResponse();
        List<User> listUsers =  userDAO.findByPhoneNumberAndPassword(loginData.getPhoneNumber(), loginData.getPassword());
        Boolean found = (listUsers.size() > 0);

        if(found)
        {
            result.statusCode = 200;
            result.message = "OK";
            result.data = listUsers;

            return result;
        }

        throw new CredentialNotFoundException("User not found!");
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(CredentialNotFoundException.class)
    public Map<String, String> handleValidationExceptions(CredentialNotFoundException ex) {
        Map<String, String> errors = new HashMap<>();

        errors.put("message", ex.getMessage());

        return errors;
    }
}
