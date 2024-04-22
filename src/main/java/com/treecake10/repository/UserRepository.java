package com.treecake10.repository;

import com.treecake10.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
public interface UserRepository extends JpaRepository<User, Long> {

    public User findByEmail(String username);
}
