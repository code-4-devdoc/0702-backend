package com.devdoc.backend.service;

import com.devdoc.backend.dto.AboutMeDTO;
import com.devdoc.backend.model.AboutMe;
import com.devdoc.backend.model.Resume;
import com.devdoc.backend.repository.AboutMeRepository;
import com.devdoc.backend.repository.ResumeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class AboutMeService {

    @Autowired
    private AboutMeRepository aboutMeRepository;

    @Autowired
    private ResumeRepository resumeRepository;

    // AboutMe 항목 데이터 저장 또는 업데이트
    @Transactional
    public AboutMeDTO saveOrUpdateAboutMe(int resumeId, AboutMeDTO aboutMeDTO) {
        Optional<Resume> optionalResume = resumeRepository.findById(resumeId);
        if (optionalResume.isPresent()) {
            Resume resume = optionalResume.get();
            AboutMe aboutMe = aboutMeRepository.findByResume(resume)
                    .orElse(new AboutMe());

            aboutMe.setPhoto(aboutMeDTO.getPhoto());
            aboutMe.setName(aboutMeDTO.getName());
            aboutMe.setBirthday(aboutMeDTO.getBirthday());
            aboutMe.setEmail(aboutMeDTO.getEmail());
            aboutMe.setPhoneNumber(aboutMeDTO.getPhoneNumber());
            aboutMe.setBlog(aboutMeDTO.getBlog());
            aboutMe.setGithub(aboutMeDTO.getGithub());
            aboutMe.setIntroduction(aboutMeDTO.getIntroduction());
            aboutMe.setResume(resume);

            AboutMe savedAboutMe = aboutMeRepository.save(aboutMe);

            return new AboutMeDTO(savedAboutMe.getId(), savedAboutMe.getPhoto(), savedAboutMe.getName(), savedAboutMe.getBirthday(), savedAboutMe.getEmail(), savedAboutMe.getGithub(), savedAboutMe.getPhoneNumber(), savedAboutMe.getBlog(), savedAboutMe.getIntroduction());
        }
        throw new RuntimeException("Resume not found");
    }

    // AboutMe 항목 데이터 삭제
    @Transactional
    public void deleteAboutMe(int resumeId, int aboutMeId) {
        Optional<AboutMe> aboutMe = aboutMeRepository.findByIdAndResumeId(aboutMeId, resumeId);
        aboutMe.ifPresent(aboutMeRepository::delete);
    }
}
