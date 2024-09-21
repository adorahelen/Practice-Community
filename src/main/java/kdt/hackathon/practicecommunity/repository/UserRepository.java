package kdt.hackathon.practicecommunity.repository;

import kdt.hackathon.practicecommunity.entitiy.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> { // Note: Primary key is a String (ULID)
    // Find user by email
    Optional<User> findByEmail(String email);
}
