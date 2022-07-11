package com.example.neprep.repository;

import com.example.neprep.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    User findUserByFirstName(String name);
    User findUserById(UUID id);
    User findByEmail(String email);
}
