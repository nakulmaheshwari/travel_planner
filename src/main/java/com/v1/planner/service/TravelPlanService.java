package com.v1.planner.service;

import com.v1.planner.entity.TravelPlan;
import com.v1.planner.repository.TravelPlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TravelPlanService {
    @Autowired
    private TravelPlanRepository travelPlanRepository;

    public List<TravelPlan> getAllTravelPlans() {
        return travelPlanRepository.findAll();
    }

    public Optional<TravelPlan> getTravelPlanById(Long id) {
        return travelPlanRepository.findById(id);
    }

    public TravelPlan saveTravelPlan(TravelPlan travelPlan) {
        return travelPlanRepository.save(travelPlan);
    }

    public TravelPlan updateTravelPlan(Long id, TravelPlan travelPlan) {
        Optional<TravelPlan> existingTravelPlanOpt = travelPlanRepository.findById(id);
        if (existingTravelPlanOpt.isPresent()) {
            TravelPlan existingTravelPlan = existingTravelPlanOpt.get();
            existingTravelPlan.setDestination(travelPlan.getDestination());
            existingTravelPlan.setStartDate(travelPlan.getStartDate());
            existingTravelPlan.setEndDate(travelPlan.getEndDate());
            existingTravelPlan.setDescription(travelPlan.getDescription());
            existingTravelPlan.setPhotos(travelPlan.getPhotos());
            return travelPlanRepository.save(existingTravelPlan);
        } else {
            throw new RuntimeException("Travel plan not found");
        }
    }

    public void deleteTravelPlan(Long id) {
        travelPlanRepository.deleteById(id);
    }

    public TravelPlan buyTravelPlan(Long userId, Long travelPlanId) {
        Optional<TravelPlan> travelPlanOpt = travelPlanRepository.findById(travelPlanId);
        if (travelPlanOpt.isPresent()) {
            TravelPlan travelPlan = travelPlanOpt.get();
            // Add logic to associate the travel plan with the user
            // For example, you might add the travel plan to the user's list of purchased plans
            // user.addTravelPlan(travelPlan);
            // userRepository.save(user);
            return travelPlanRepository.save(travelPlan);
        } else {
            throw new RuntimeException("Travel plan not found");
        }
    }
}
