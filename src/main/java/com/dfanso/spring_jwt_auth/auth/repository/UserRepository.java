package com.dfanso.spring_jwt_auth.auth.repository;


import com.dfanso.spring_jwt_auth.auth.model.User;
import com.dfanso.spring_jwt_auth.auth.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
}