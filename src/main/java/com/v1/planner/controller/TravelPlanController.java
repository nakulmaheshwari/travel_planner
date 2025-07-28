package com.v1.planner.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.v1.planner.dto.TravelPlanRequest;
import com.v1.planner.entity.TravelPlan;
import com.v1.planner.service.TravelPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController

@RequestMapping("/api/travel-plans")
@PreAuthorize("hasRole('ADMIN')")
public class TravelPlanController {
    private final TravelPlanService travelPlanService;

    public TravelPlanController(TravelPlanService travelPlanService) {
        this.travelPlanService = travelPlanService;
    }

    @GetMapping
    public ResponseEntity<List<TravelPlan>> getAllTravelPlans() {
        List<TravelPlan> travelPlans = travelPlanService.getAllTravelPlans();
        return ResponseEntity.ok(travelPlans);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TravelPlan> getTravelPlanById(@PathVariable Long id) {
        Optional<TravelPlan> travelPlan = travelPlanService.getTravelPlanById(id);
        return travelPlan.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // @PostMapping
    // public ResponseEntity<TravelPlan> createTravelPlan(@RequestBody TravelPlan travelPlan) {
    //     TravelPlan savedTravelPlan = travelPlanService.saveTravelPlan(travelPlan);
    //     return ResponseEntity.ok(savedTravelPlan);
    // }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<TravelPlan> createPlan(
            @RequestParam("travelPlan") String travelPlanJson,
            @RequestParam("files") List<MultipartFile> files) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules(); // Auto-registers modules including jsr310
        TravelPlanRequest travelPlanRequest = objectMapper.readValue(travelPlanJson, TravelPlanRequest.class);




        TravelPlan travelPlan = travelPlanService.createTravelPlan(travelPlanRequest, files);

        return ResponseEntity.ok(travelPlan);
    }




    @PutMapping("/{id}")
    public ResponseEntity<TravelPlan> updateTravelPlan(@PathVariable Long id, @RequestBody TravelPlan travelPlan) {
        TravelPlan updatedTravelPlan = travelPlanService.updateTravelPlan(id, travelPlan);
        return ResponseEntity.ok(updatedTravelPlan);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTravelPlan(@PathVariable Long id) {
        travelPlanService.deleteTravelPlan(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/buy")
    public ResponseEntity<TravelPlan> buyTravelPlan(@RequestParam Long userId, @RequestParam Long travelPlanId) {
        TravelPlan purchasedTravelPlan = travelPlanService.buyTravelPlan(userId, travelPlanId);
        return ResponseEntity.ok(purchasedTravelPlan);
    }
}
