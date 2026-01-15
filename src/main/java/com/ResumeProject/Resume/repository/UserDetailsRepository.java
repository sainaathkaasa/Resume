package com.ResumeProject.Resume.repository;

import com.ResumeProject.Resume.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDetailsRepository extends JpaRepository<Users,Long> {
    Optional<Users> findByUsername(String username);

    Optional<Users> findByEmail(String email);
}
