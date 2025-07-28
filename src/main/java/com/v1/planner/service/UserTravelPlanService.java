package com.v1.planner.service;
import com.v1.planner.entity.User;
import com.v1.planner.entity.TravelPlan;
import com.v1.planner.repository.UserRepository;
import com.v1.planner.repository.TravelPlanRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserTravelPlanService {
    private final UserRepository userRepository;
    private final TravelPlanRepository travelPlanRepository;

    public UserTravelPlanService(UserRepository userRepository, TravelPlanRepository travelPlanRepository) {
        this.userRepository = userRepository;
        this.travelPlanRepository = travelPlanRepository;
    }

    @Transactional
    public void registerUserToTravelPlan(Long userId, Long travelPlanId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("User not found with id: " + userId));
        TravelPlan travelPlan = travelPlanRepository.findById(travelPlanId)
                .orElseThrow(() -> new NoSuchElementException("Travel plan not found with id: " + travelPlanId));

        if (user.getTravelPlans().contains(travelPlan)) {
            throw new IllegalStateException("User is already registered for this travel plan.");
        }


        user.addTravelPlan(travelPlan);
        userRepository.save(user);
    }

    @Transactional
    public void unregisterUserFromTravelPlan(Long userId, Long travelPlanId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("User not found with id: " + userId));
        TravelPlan travelPlan = travelPlanRepository.findById(travelPlanId)
                .orElseThrow(() -> new NoSuchElementException("Travel plan not found with id: " + travelPlanId));

        // Check if deregistration is allowed before the deadline
        LocalDate deadline = travelPlan.getStartDate().minusDays(5); // Example: 5 days before start
        if (LocalDate.now().isAfter(deadline)) {
            throw new IllegalStateException("Deregistration is not allowed after the deadline.");
        }


        user.removeTravelPlan(travelPlan);
        userRepository.save(user);
    }

    public List<User> getRegisteredUsers(Long travelPlanId) {
        return travelPlanRepository.findUsersByTravelPlanId(travelPlanId);
    }
}