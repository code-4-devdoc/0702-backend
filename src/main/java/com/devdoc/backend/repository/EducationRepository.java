package com.devdoc.backend.repository;

import com.devdoc.backend.model.Education;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EducationRepository extends JpaRepository<Education, Integer> {

    List<Education> findByResumeId(Integer resumeId);
    Optional<Education> findByIdAndResumeId(Integer id, Integer resumeId);
}
