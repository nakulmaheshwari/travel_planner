package com.v1.planner.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "travel_plan")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
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
    @JsonManagedReference
    private List<String> photos = new ArrayList<>();

    @ManyToMany(mappedBy = "travelPlans", fetch = FetchType.LAZY)
    private List<User> users = new ArrayList<>();

    // Helper methods to maintain bidirectional relationship
    public void addUser(User user) {
        users.add(user);
        user.getTravelPlans().add(this);
    }

    public void removeUser(User user) {
        users.remove(user);
        user.getTravelPlans().remove(this);
    }
}
