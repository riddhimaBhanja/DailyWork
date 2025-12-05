package com.bezkoder.spring.login.repository;

import com.bezkoder.spring.login.models.ERole;
import com.bezkoder.spring.login.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByName(ERole name);
}
