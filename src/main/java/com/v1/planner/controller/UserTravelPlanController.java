package com.v1.planner.controller;
import com.v1.planner.entity.User;
import com.v1.planner.service.UserTravelPlanService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user-travel-plans")
public class UserTravelPlanController {
    private final UserTravelPlanService userTravelPlanService;

    public UserTravelPlanController(UserTravelPlanService userTravelPlanService) {
        this.userTravelPlanService = userTravelPlanService;
    }

    @PostMapping("/{userId}/{travelPlanId}")
    public ResponseEntity<Void> registerUserToTravelPlan(@PathVariable Long userId, @PathVariable Long travelPlanId) {
        userTravelPlanService.registerUserToTravelPlan(userId, travelPlanId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{userId}/{travelPlanId}")
    public ResponseEntity<Void> unregisterUserFromTravelPlan(@PathVariable Long userId, @PathVariable Long travelPlanId) {
        userTravelPlanService.unregisterUserFromTravelPlan(userId, travelPlanId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{travelPlanId}/users")
    public ResponseEntity<List<User>> getRegisteredUsers(@PathVariable Long travelPlanId) {
        return ResponseEntity.ok(userTravelPlanService.getRegisteredUsers(travelPlanId));
    }


}