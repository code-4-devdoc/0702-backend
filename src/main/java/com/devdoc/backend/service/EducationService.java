package com.devdoc.backend.service;

import com.devdoc.backend.dto.EducationDTO;
import com.devdoc.backend.model.Education;
import com.devdoc.backend.model.Resume;
import com.devdoc.backend.repository.EducationRepository;
import com.devdoc.backend.repository.ResumeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class EducationService {

    @Autowired
    private EducationRepository educationRepository;

    @Autowired
    private ResumeRepository resumeRepository;

    // Education 항목 데이터 저장 또는 업데이트
    @Transactional
    public EducationDTO saveOrUpdateEducation(int resumeId, EducationDTO educationDTO) {
        Optional<Resume> optionalResume = resumeRepository.findById(resumeId);
        if (optionalResume.isPresent()) {
            Resume resume = optionalResume.get();
            Education education = educationRepository.findByIdAndResumeId(educationDTO.getId(), resumeId)
                    .orElse(new Education());

            boolean isNew = (education.getId() == null); // 새로운 항목인지 확인

            education.setSchoolName(educationDTO.getSchoolName());
            education.setMajor(educationDTO.getMajor());
            education.setStartDate(educationDTO.getStartDate());
            education.setEndDate(educationDTO.getEndDate());
            education.setStatus(educationDTO.getStatus());
            education.setEducationType(educationDTO.getEducationType());
            education.setResume(resume);

            Education savedEducation = educationRepository.save(education);

            return new EducationDTO(savedEducation.getId(), savedEducation.getSchoolName(), savedEducation.getMajor(), savedEducation.getStartDate(), savedEducation.getEndDate(), savedEducation.getStatus(), savedEducation.getEducationType());
        }
        throw new RuntimeException("Resume not found");
    }

    // Education 항목 데이터 삭제
    @Transactional
    public void deleteEducation(int resumeId, int educationId) {
        Optional<Education> education = educationRepository.findByIdAndResumeId(educationId, resumeId);
        education.ifPresent(educationRepository::delete);
    }
}
