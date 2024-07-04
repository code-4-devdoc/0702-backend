package com.devdoc.backend.service;

import com.devdoc.backend.dto.AwardDTO;
import com.devdoc.backend.model.Award;
import com.devdoc.backend.model.Resume;
import com.devdoc.backend.repository.AwardRepository;
import com.devdoc.backend.repository.ResumeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AwardService {

    @Autowired
    private ResumeRepository resumeRepository;

    @Autowired
    private AwardRepository awardRepository;

    // Award 항목 데이터 저장 또는 업데이트
    @Transactional
    public AwardDTO saveOrUpdateAward(int resumeId, AwardDTO awardDTO) {
        Optional<Resume> optionalResume = resumeRepository.findById(resumeId);
        if (optionalResume.isPresent()) {
            Resume resume = optionalResume.get();
            Award award = awardRepository.findByIdAndResumeId(awardDTO.getId(), resumeId)
                    .orElse(new Award());

            boolean isNew = (award.getId() == null);

            award.setAwardName(awardDTO.getAwardName());
            award.setAwardingInstitution(awardDTO.getAwardingInstitution());
            award.setDate(awardDTO.getDate());
            award.setDescription(awardDTO.getDescription());
            award.setResume(resume);

            Award savedAward = awardRepository.save(award);

            return new AwardDTO(savedAward.getId(), savedAward.getAwardName(), savedAward.getAwardingInstitution(), savedAward.getDate(), savedAward.getDescription());
        }
        throw new RuntimeException("Resume not found");
    }

    // Award 항목 데이터 삭제
    @Transactional
    public void deleteAward(int resumeId, int awardId) {
        Optional<Award> award = awardRepository.findByIdAndResumeId(awardId, resumeId);
        award.ifPresent(awardRepository::delete);
    }


    // 특정 Resume의 모든 Award 조회
    public List<AwardDTO> getAwardsByResumeId(int resumeId) {
        Optional<Resume> optionalResume = resumeRepository.findById(resumeId);
        if (optionalResume.isPresent()) {
            Resume resume = optionalResume.get();
            return resume.getAwards().stream()
                    .map(award -> new AwardDTO(award.getId(), award.getAwardName(), award.getAwardingInstitution(), award.getDate(), award.getDescription()))
                    .collect(Collectors.toList());
        }
        return null;
    }
}
