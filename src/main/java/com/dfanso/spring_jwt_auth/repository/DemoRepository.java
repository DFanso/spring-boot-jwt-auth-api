package com.dfanso.spring_jwt_auth.repository;



import com.dfanso.spring_jwt_auth.model.Demo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DemoRepository extends JpaRepository<Demo, Long> {
    // Additional custom query methods can be defined here if needed
}
