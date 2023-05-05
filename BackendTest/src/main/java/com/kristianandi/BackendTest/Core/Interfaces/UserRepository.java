package com.kristianandi.BackendTest.Core.Interfaces;

import com.kristianandi.BackendTest.Core.Domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select u from User u where u.phoneNumber = ?1 and u.password = ?2")
    List<User> findByPhoneNumberAndPassword(@Param("phone_number") String phoneNumber,
                                                           @Param("password") String password);
}
