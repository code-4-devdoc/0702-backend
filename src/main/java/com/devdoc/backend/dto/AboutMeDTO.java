package com.devdoc.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AboutMeDTO {

    private Integer id;
    private String photo;
    private String name;
    private String birthday;
    private String github;
    private String email;
    private String phoneNumber;
    private String blog;
    private String introduction;
}
