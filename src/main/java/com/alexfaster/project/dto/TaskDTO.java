package com.alexfaster.project.dto;

import com.alexfaster.project.model.TaskStatus;
import lombok.Data;

@Data
public class TaskDTO {

    private Long id;

    private String title;

    private String description;

    private TaskStatus status;

}
