package com.devdoc.backend.service;

import com.devdoc.backend.dto.TrainingDTO;
import com.devdoc.backend.model.Resume;
import com.devdoc.backend.model.Training;
import com.devdoc.backend.repository.ResumeRepository;
import com.devdoc.backend.repository.TrainingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class TrainingService {

    @Autowired
    private ResumeRepository resumeRepository;

    @Autowired
    private TrainingRepository trainingRepository;

    // Training 항목 데이터 저장 또는 업데이트
    @Transactional
    public TrainingDTO saveOrUpdateTraining(int resumeId, TrainingDTO trainingDTO) {
        Optional<Resume> optionalResume = resumeRepository.findById(resumeId);
        if (optionalResume.isPresent()) {
            Resume resume = optionalResume.get();
            Training training = trainingRepository.findByIdAndResumeId(trainingDTO.getId(), resumeId)
                    .orElse(new Training());

            boolean isNew = (training.getId() == null); // 새로운 항목인지 확인

            training.setCourseName(trainingDTO.getCourseName());
            training.setInstitution(trainingDTO.getInstitution());
            training.setStartDate(trainingDTO.getStartDate());
            training.setEndDate(trainingDTO.getEndDate());
            training.setIsCurrent(trainingDTO.getIsCurrent());
            training.setResume(resume);

            Training savedTraining = trainingRepository.save(training);

            return new TrainingDTO(savedTraining.getId(), savedTraining.getCourseName(), savedTraining.getInstitution(), savedTraining.getStartDate(), savedTraining.getEndDate(), savedTraining.getIsCurrent());
        }
        throw new RuntimeException("Resume not found");
    }

    // Training 항목 데이터 삭제
    @Transactional
    public void deleteTraining(int resumeId, int trainingId) {
        Optional<Training> training = trainingRepository.findByIdAndResumeId(trainingId, resumeId);
        training.ifPresent(trainingRepository::delete);
    }
}
