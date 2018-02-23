package com.alexfaster.project.service.assembler;

import com.alexfaster.project.dto.TaskDTO;
import com.alexfaster.project.model.Task;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskAssemblerService {

    private final ModelMapper modelMapper;

    @Autowired
    public TaskAssemblerService(final ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public TaskDTO assembleDTO(final Task task) {
        return modelMapper.map(task, TaskDTO.class);
    }

    public Task dtoToEntity(final TaskDTO taskDTO) {
        return modelMapper.map(taskDTO, Task.class);
    }

    public Task mergeWithDTO(final Task task, final TaskDTO dto) {
        if (dto.getDescription() != null) {
            task.setDescription(dto.getDescription());
        }
        if (dto.getTitle() != null) {
            task.setTitle(dto.getTitle());
        }
        if (dto.getStatus() != null) {
            task.setStatus(dto.getStatus());
        }
        return task;
    }
}
