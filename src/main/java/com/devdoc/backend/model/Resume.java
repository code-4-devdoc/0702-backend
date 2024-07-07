package com.devdoc.backend.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "이력서를 담는 모델")
public class Resume {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Resume ID", example = "5")
    private int id;

    @Schema(description = "이력서 제목", example = "준비된 백엔드 개발자!")
    private String title;

    @Schema(description = "생성 일자", example = "2024-07-05T10:15:30")
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @Schema(description = "이력서를 소유한 유저")
    private UserEntity user; // 이력서를 소유한 유저

    @OneToMany(mappedBy = "resume", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Schema(description = "이력서에 포함된 어학 목록")
    private List<Language> languages; // 이력서에 포함된 Language 목록

    @OneToMany(mappedBy = "resume", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Schema(description = "이력서에 포함된 수상 목록")
    private List<Award> awards; // 이력서에 포함된 Award 목록

    @OneToMany(mappedBy = "resume", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
    @Schema(description = "이력서에 포함된 자격증 목록")
    private List<Certificate> certificates; // 이력서에 포함된 Certificate 목록

    @OneToMany(mappedBy = "resume", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Schema(description = "이력서에 포함된 기술 목록")
    private List<Skill> skills;

    @OneToMany(mappedBy = "resume", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Schema(description = "이력서에 포함된 경력 목록")
    private List<Career> careers;

    @OneToMany(mappedBy = "resume", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Schema(description = "이력서에 포함된 프로젝트 목록")
    private List<Project> projects;

    @OneToMany(mappedBy = "resume", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Schema(description = "이력서에 포함된 대외 활동 목록")
    private List<Activity> activities;

    @OneToMany(mappedBy = "resume", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Schema(description = "이력서에 포함된 교육 목록")
    private List<Training> trainings;

    @OneToOne(mappedBy = "resume", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Schema(description = "이력서에 포함된 단일 프로필")
    private AboutMe aboutMe;

    @OneToMany(mappedBy = "resume", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Schema(description = "이력서에 포함된 학력 목록")
    private List<Education> educations;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}

