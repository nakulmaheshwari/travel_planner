package com.v1.planner.entity;

import com.v1.planner.enums.Role;
import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;
import java.util.List;


@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;

    private String email;
    private String password;

@ManyToMany
   @JoinTable(
     name = "user_travel_plan",
       joinColumns = @JoinColumn(name = "user_id"),
    inverseJoinColumns = @JoinColumn(name = "travel_plan_id")
   )
 private List<TravelPlan> travelPlans;

    @Enumerated(EnumType.STRING)
    private Role role;

    
    


    // Default constructor

}

