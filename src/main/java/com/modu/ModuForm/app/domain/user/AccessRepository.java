package com.modu.ModuForm.app.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccessRepository extends JpaRepository<Access, Long> {
    Optional<Access> findByUser(User user);
}
