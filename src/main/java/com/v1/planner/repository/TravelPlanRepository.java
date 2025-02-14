package com.v1.planner.repository;

import com.v1.planner.entity.TravelPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TravelPlanRepository extends JpaRepository<TravelPlan, Long> {
}
