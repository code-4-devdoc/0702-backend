package com.devdoc.backend.service;

import com.devdoc.backend.dto.ActivityDTO;
import com.devdoc.backend.model.Activity;
import com.devdoc.backend.model.Resume;
import com.devdoc.backend.repository.ActivityRepository;
import com.devdoc.backend.repository.ResumeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ActivityService {

    @Autowired
    private ResumeRepository resumeRepository;

    @Autowired
    private ActivityRepository activityRepository;

    // Activity 항목 데이터 저장 또는 업데이트
    @Transactional
    public ActivityDTO saveOrUpdateActivity(int resumeId, ActivityDTO activityDTO) {
        Optional<Resume> optionalResume = resumeRepository.findById(resumeId);
        if (optionalResume.isPresent()) {
            Resume resume = optionalResume.get();
            Activity activity = activityRepository.findByIdAndResumeId(activityDTO.getId(), resumeId)
                    .orElse(new Activity());

            boolean isNew = (activity.getId() == null); // 새로운 항목인지 확인

            activity.setActivityName(activityDTO.getActivityName());
            activity.setOrganizationName(activityDTO.getOrganizationName());
            activity.setStartDate(activityDTO.getStartDate());
            activity.setEndDate(activityDTO.getEndDate());
            activity.setIsCurrent(activityDTO.getIsCurrent());
            activity.setResume(resume);

            Activity savedActivity = activityRepository.save(activity);

            return new ActivityDTO(savedActivity.getId(), savedActivity.getActivityName(), savedActivity.getOrganizationName(), savedActivity.getStartDate(), savedActivity.getEndDate(), savedActivity.getIsCurrent());
        };
        throw new RuntimeException("Resume not found");
    }

    // Activity 항목 데이터 삭제
    @Transactional
    public void deleteActivity(int resumeId, int activityId) {
        Optional<Activity> activity = activityRepository.findByIdAndResumeId(activityId, resumeId);
        activity.ifPresent(activityRepository::delete);
    }
}
