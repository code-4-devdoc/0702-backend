package com.devdoc.backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class AboutMe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 500000)
    private String photo;
    private String name;
    private String birthday;
    private String email;
    private String github;
    private String phoneNumber;
    private String blog;
    private String introduction;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "resume_id")
    private Resume resume;
}
