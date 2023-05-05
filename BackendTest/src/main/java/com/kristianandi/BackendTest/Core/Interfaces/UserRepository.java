package com.kristianandi.BackendTest.Core.Interfaces;

import com.kristianandi.BackendTest.Core.Domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
