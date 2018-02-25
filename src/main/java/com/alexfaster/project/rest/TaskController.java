package com.alexfaster.project.rest;

import com.alexfaster.project.dto.TaskDTO;
import com.alexfaster.project.model.Task;
import com.alexfaster.project.service.TaskService;
import com.alexfaster.project.service.assembler.TaskAssemblerService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Api("Task api")
@RestController
@RequestMapping(path = "/api", produces = "application/json")
public class TaskController {

    private final TaskService taskService;

    private final TaskAssemblerService taskAssemblerService;

    @Autowired
    public TaskController(
            final TaskService taskService,
            final TaskAssemblerService taskAssemblerService
    ) {

        this.taskService = taskService;
        this.taskAssemblerService = taskAssemblerService;
    }

    @GetMapping("/v1/tasks")
    public List<TaskDTO> getTasks() {
        return taskService.getTasks()
                .stream()
                .map(taskAssemblerService::assembleDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/v1/tasks/{id}")
    public TaskDTO getTask(
            @PathVariable("id") final long id
    ) {
        return taskService.getTask(id).map(taskAssemblerService::assembleDTO)
                .orElseThrow(() -> new RuntimeException("Not Found"));
    }

    @PostMapping("/v1/tasks")
    public TaskDTO addTask(
            @RequestBody final TaskDTO taskDTO
    ) {
        final Task task = taskAssemblerService.dtoToEntity(taskDTO);
        return taskAssemblerService.assembleDTO(
                taskService.addTask(task)
        );
    }

    @PutMapping("/v1/tasks/{id}")
    public TaskDTO updateTask(
            @PathVariable("id") final long id,
            @RequestBody final TaskDTO taskDTO
    ) {
        return taskService.getTask(id).map(task -> {
                    final Task taskIn = taskAssemblerService.mergeWithDTO(task, taskDTO);
                    taskService.updateTask(taskIn);
                    return taskAssemblerService.assembleDTO(taskIn);
                }
        ).orElseThrow(() -> new RuntimeException("Not Found"));
    }

    @DeleteMapping("/v1/tasks/{id}")
    public void deleteTask(
            @PathVariable("id") final long id
    ) {
        taskService.deleteTask(id);
    }
}