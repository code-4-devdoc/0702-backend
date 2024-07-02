package com.devdoc.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActivityDTO {
    private Integer id;
    private String activityName;
    private String organizationName;
    private String startDate;
    private String endDate;
    private Boolean isCurrent;
}
