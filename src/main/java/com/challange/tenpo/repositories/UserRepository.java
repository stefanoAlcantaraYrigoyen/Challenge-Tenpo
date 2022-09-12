package com.challange.tenpo.repositories;

import com.challange.tenpo.entitys.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);
    User findByEmail(String username);
}