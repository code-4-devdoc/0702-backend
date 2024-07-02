package com.devdoc.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrainingDTO {

    private Integer id;
    private String courseName;
    private String institution;
    private String startDate;
    private String endDate;
    private Boolean isCurrent;
}
