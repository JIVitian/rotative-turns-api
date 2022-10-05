package com.app.rotativeturnsapi.repositories;

import com.app.rotativeturnsapi.entities.WorkdayType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkdayTypeRepository extends JpaRepository<WorkdayType, Integer> {
    WorkdayType findByName(String name);
}