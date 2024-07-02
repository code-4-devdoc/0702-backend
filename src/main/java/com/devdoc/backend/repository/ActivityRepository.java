package com.devdoc.backend.repository;

import com.devdoc.backend.model.Activity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ActivityRepository extends JpaRepository<Activity, Integer> {

    List<Activity> findByResumeId(Integer resumeId);
    Optional<Activity> findByIdAndResumeId(Integer id, Integer resumeId);
}
