package com.devdoc.backend.repository;

import com.devdoc.backend.model.AboutMe;
import com.devdoc.backend.model.Resume;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AboutMeRepository extends JpaRepository<AboutMe, Integer> {

    Optional<AboutMe> findByResume(Resume resume);
    Optional<AboutMe> findByIdAndResumeId(Integer id, Integer resumeId);
}
