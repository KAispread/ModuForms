package com.modu.ModuForm.app.domain.user.admin;

import com.modu.ModuForm.app.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, Long> {
    Optional<User> findByEmail(String email);
}
