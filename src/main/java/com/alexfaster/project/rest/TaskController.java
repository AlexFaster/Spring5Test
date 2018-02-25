package com.alexfaster.project.rest;

import com.alexfaster.project.dto.TaskDTO;
import com.alexfaster.project.model.Task;
import com.alexfaster.project.service.TaskService;
import com.alexfaster.project.service.assembler.TaskAssemblerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

@Api(tags = {"Task operations"}, description = " ")
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
    @ApiOperation(
            value = "Get tasks",
            response = TaskDTO.class,
            responseContainer = "List"
    )
    public List<TaskDTO> getTasks() {
        return taskService.getTasks()
                .stream()
                .map(taskAssemblerService::assembleDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/v1/tasks/{id}")
    @ApiOperation(
            value = "Get task",
            response = TaskDTO.class
    )
    public TaskDTO getTask(
            @ApiParam(required = true, name = "id")
            @NotNull
            @PathVariable("id") final long id
    ) {
        return taskService.getTask(id).map(taskAssemblerService::assembleDTO)
                .orElseThrow(() -> new RuntimeException("Not Found"));
    }

    @PostMapping("/v1/tasks")
    @ApiOperation(
            value = "Add task",
            response = TaskDTO.class
    )
    public TaskDTO addTask(
            @ApiParam(required = true, name = "task")
            @NotNull
            @RequestBody final TaskDTO taskDTO
    ) {
        final Task task = taskAssemblerService.dtoToEntity(taskDTO);
        return taskAssemblerService.assembleDTO(
                taskService.addTask(task)
        );
    }

    @PutMapping("/v1/tasks/{id}")
    @ApiOperation(
            value = "Update task",
            response = TaskDTO.class
    )
    public TaskDTO updateTask(
            @ApiParam(required = true, name = "id")
            @NotNull
            @PathVariable("id") final long id,

            @ApiParam(required = true, name = "task")
            @NotNull
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
    @ApiOperation(
            value = "Delete task"
    )
    public void deleteTask(
            @ApiParam(required = true, name = "id")
            @NotNull
            @PathVariable("id") final long id
    ) {
        taskService.deleteTask(id);
    }
}