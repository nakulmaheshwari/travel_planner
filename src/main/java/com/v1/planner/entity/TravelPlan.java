package com.v1.planner.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "travel_plan")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TravelPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String destination;
    private LocalDate startDate;
    private LocalDate endDate;
    @Column(columnDefinition = "TEXT")
    private String description;
    @ElementCollection
    @CollectionTable(name = "travel_plan_photos", joinColumns = @JoinColumn(name = "travel_plan_id"))
    @Column(name = "photo_url")
    private List<String> photos;

    @ManyToMany(mappedBy = "travelPlans")
    private List<User> users;

}
