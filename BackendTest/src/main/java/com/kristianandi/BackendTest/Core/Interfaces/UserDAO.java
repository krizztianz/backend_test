package com.kristianandi.BackendTest.Core.Interfaces;

import com.kristianandi.BackendTest.Core.Domain.User;
import jakarta.persistence.EntityManager;

import java.util.List;

public interface UserDAO {
    public List<User> findAll();
    public User findById(Long id);
    public User findByPhoneNumber(String phoneNumber);
    public List<User> findByPhoneNumberAndPassword(String phoneNumber, String password);
    public User save(User user);
    public User update(User user);
    public User delete(User user);
    public User deleteById(Long id);
    public int deleteAll();
}