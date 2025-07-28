package com.v1.planner.service;

import com.v1.planner.dto.TravelPlanRequest;
import com.v1.planner.entity.TravelPlan;
import com.v1.planner.repository.TravelPlanRepository;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class TravelPlanService {
    private final TravelPlanRepository travelPlanRepository;

    public TravelPlanService(TravelPlanRepository travelPlanRepository) {
        this.travelPlanRepository = travelPlanRepository;
    }



    @Value("${upload.path}")
    private String uploadDir;

    public List<TravelPlan> getAllTravelPlans() {
        return travelPlanRepository.findAll();
    }


    public Optional<TravelPlan> getTravelPlanById(Long id) {
        return Optional.ofNullable(travelPlanRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Travel plan not found with id: " + id)));
    }

    @Transactional
    public TravelPlan createTravelPlan(TravelPlanRequest travelPlanRequest, List<MultipartFile> files) throws IOException {
        List<String> filePaths = new ArrayList<>();

        File directory = new File(uploadDir);
        if (!directory.exists()) {
            directory.mkdirs(); // Create directory if it doesn't exist
        }


        for (MultipartFile file : files) {
            if (!file.isEmpty()) {
                String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
                String filePath = Paths.get(uploadDir, fileName).toString();
                System.out.println("/////////////////"+filePath+"    "+fileName);
                Files.write(Path.of(filePath), file.getBytes());
                filePaths.add(filePath);
            }
        }

        TravelPlan travelPlan = new TravelPlan();
        travelPlan.setDestination(travelPlanRequest.getDestination());
        travelPlan.setStartDate(travelPlanRequest.getStartDate());
        travelPlan.setEndDate(travelPlanRequest.getEndDate());
        travelPlan.setDescription(travelPlanRequest.getDescription());
        travelPlan.setPhotos(filePaths);

        return travelPlanRepository.save(travelPlan);
    }

    @Transactional
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


    @Transactional
    public void deleteTravelPlan(Long id) {
        if (!travelPlanRepository.existsById(id)) {
            throw new NoSuchElementException("Cannot delete. Travel plan not found with id: " + id);
        }
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
