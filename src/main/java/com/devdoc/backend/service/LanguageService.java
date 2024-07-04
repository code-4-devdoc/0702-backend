package com.devdoc.backend.service;

import com.devdoc.backend.dto.LanguageDTO;
import com.devdoc.backend.model.Language;
import com.devdoc.backend.model.Resume;
import com.devdoc.backend.repository.LanguageRepository;
import com.devdoc.backend.repository.ResumeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LanguageService {

    @Autowired
    private ResumeRepository resumeRepository;

    @Autowired
    private LanguageRepository languageRepository;

    // Language 항목 데이터 저장 또는 업데이트
    @Transactional
    public LanguageDTO saveOrUpdateLanguage(int resumeId, LanguageDTO languageDTO) {
        Optional<Resume> optionalResume = resumeRepository.findById(resumeId);
        if (optionalResume.isPresent()) {
            Resume resume = optionalResume.get();
            Language language = languageRepository.findByIdAndResumeId(languageDTO.getId(), resumeId)
                    .orElse(new Language());

            boolean isNew = (language.getId() == null); // 새로운 항목인지 확인

            language.setLanguage(languageDTO.getLanguage());
            language.setTestName(languageDTO.getTestName());
            language.setScore(languageDTO.getScore());
            language.setDate(languageDTO.getDate());
            language.setResume(resume);

            Language savedLanguage = languageRepository.save(language);

            return new LanguageDTO(savedLanguage.getId(), savedLanguage.getLanguage(), savedLanguage.getTestName(), savedLanguage.getScore(), savedLanguage.getDate());
        }
        throw new RuntimeException("Resume not found");
    }

    // Language 항목 데이터 삭제
    @Transactional
    public void deleteLanguage(int resumeId, int languageId) {
        Optional<Language> language = languageRepository.findByIdAndResumeId(languageId, resumeId);
        language.ifPresent(languageRepository::delete);
    }

    // 특정 Resume의 모든 Language 조회
    public List<LanguageDTO> getLanguagesByResumeId(int resumeId) {
        Optional<Resume> optionalResume = resumeRepository.findById(resumeId);
        if (optionalResume.isPresent()) {
            Resume resume = optionalResume.get();
            return resume.getLanguages().stream()
                    .map(language -> new LanguageDTO(language.getId(), language.getLanguage(), language.getTestName(), language.getScore(), language.getDate()))
                    .collect(Collectors.toList());
        }
        return null;
    }
}
