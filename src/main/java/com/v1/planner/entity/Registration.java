package com.v1.planner.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "registrations")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Registration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "travel_plan_id", nullable = false)
    private TravelPlan travelPlan;

    @Column(name = "registered_at", nullable = false, updatable = false)
    private LocalDateTime registeredAt = LocalDateTime.now();
}
