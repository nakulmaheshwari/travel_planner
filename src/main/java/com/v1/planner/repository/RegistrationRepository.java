package com.v1.planner.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.v1.planner.entity.Registration;

public interface RegistrationRepository extends JpaRepository<Registration, Long> {
    // Custom query methods can be added here
}
