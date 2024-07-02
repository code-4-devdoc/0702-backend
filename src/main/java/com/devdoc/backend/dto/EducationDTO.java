package com.devdoc.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EducationDTO {

    private Integer id;
    private String schoolName;
    private String major;
    private String startDate;
    private String endDate;
    private String status;
    private String educationType;
}
