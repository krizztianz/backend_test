package com.kristianandi.BackendTest.Rest;

import com.kristianandi.BackendTest.Core.Domain.ApiResponse;
import com.kristianandi.BackendTest.Core.Domain.UpdateName;
import com.kristianandi.BackendTest.Core.Domain.User;
import com.kristianandi.BackendTest.Core.Interfaces.UserDAO;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.hibernate.FetchNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.CredentialNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class UserController {

    @Autowired
    private UserDAO userDAO;

    @GetMapping("/users/{phoneNumber}")
    private User GetUserName(@PathVariable String phoneNumber)
    {
        User user = userDAO.findByPhoneNumber(phoneNumber);

        if(user != null)
        {
            return user;
        }

        throw new EntityNotFoundException("User not found!");
    }

    @PutMapping(value = "/users/updatename/{phoneNumber}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    private User UpdateUserName(@PathVariable String phoneNumber, @RequestBody UpdateName request)
    {
        User user = userDAO.findByPhoneNumber(phoneNumber);

        if(user != null)
        {
            user.setName(request.getName());
            User updatedUser = userDAO.update(user);

            return updatedUser;
        }

        throw new EntityNotFoundException("User not found!");
    }

    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return errors;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EntityNotFoundException.class)
    public Map<String, String> handleValidationExceptions(EntityNotFoundException ex) {
        Map<String, String> errors = new HashMap<>();

        errors.put("message", ex.getMessage());

        return errors;
    }

}
