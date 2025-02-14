package com.v1.planner.service;

import com.v1.planner.entity.Registration;
import com.v1.planner.repository.RegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RegistrationService {

    @Autowired
    private RegistrationRepository registrationRepository;

    public List<Registration> getAllRegistrations() {
        return registrationRepository.findAll();
    }

    public Optional<Registration> getRegistrationById(Long id) {
        return registrationRepository.findById(id);
    }

    public Registration saveRegistration(Registration registration) {
        return registrationRepository.save(registration);
    }

    public void deleteRegistration(Long id) {
        registrationRepository.deleteById(id);
    }

    public String getRegistration() {
        return "Registration Service Response";
    }
}
