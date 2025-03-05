package com.v1.planner.repository;

import com.v1.planner.entity.TravelPlan;
import com.v1.planner.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TravelPlanRepository extends JpaRepository<TravelPlan, Long> {

    @Query("SELECT t.users FROM TravelPlan t WHERE t.id = :travelPlanId")
    List<User> findUsersByTravelPlanId(@Param("travelPlanId") Long travelPlanId);
}
