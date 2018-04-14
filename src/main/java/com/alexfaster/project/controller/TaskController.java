package com.alexfaster.project.controller;

import com.alexfaster.project.dto.TaskDTO;
import com.alexfaster.project.model.Task;
import com.alexfaster.project.model.User;
import com.alexfaster.project.service.TaskService;
import com.alexfaster.project.service.assembler.TaskAssemblerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    public List<TaskDTO> getTasks(
            @ApiParam(hidden = true)
            @AuthenticationPrincipal(expression = "user")
            final User user
    ) {
        return taskService.getTasks(user)
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

            @ApiParam(hidden = true)
            @AuthenticationPrincipal(expression = "user")
            final User user,

            @ApiParam(required = true, name = "id")
            @NotNull
            @PathVariable("id")
            final long id
    ) {
        return taskService.getTask(id, user)
                .map(taskAssemblerService::assembleDTO)
                .orElseThrow(() -> new RuntimeException("Not Found"));
    }

    @PostMapping("/v1/tasks")
    @ApiOperation(
            value = "Add task",
            response = TaskDTO.class
    )
    public TaskDTO addTask(

            @ApiParam(hidden = true)
            @AuthenticationPrincipal(expression = "user")
            final User user,

            @ApiParam(required = true, name = "task")
            @NotNull
            @RequestBody
            final TaskDTO taskDTO
    ) {
        final Task task = taskService.addTask(user, taskDTO);
        return taskAssemblerService.assembleDTO(task);
    }

    @PutMapping("/v1/tasks/{id}")
    @ApiOperation(
            value = "Update task",
            response = TaskDTO.class
    )
    public TaskDTO updateTask(

            @ApiParam(hidden = true)
            @AuthenticationPrincipal(expression = "user")
            final User user,

            @ApiParam(required = true, name = "id")
            @NotNull
            @PathVariable("id")
            final long id,

            @ApiParam(required = true, name = "task")
            @NotNull
            @RequestBody
            final TaskDTO taskDTO
    ) {
        return taskService.updateTask(id, user, taskDTO)
                .map(taskAssemblerService::assembleDTO)
                .orElseThrow(() -> new RuntimeException("Not Found"));
    }

    @DeleteMapping("/v1/tasks/{id}")
    @ApiOperation(
            value = "Delete task"
    )
    public void deleteTask(

            @ApiParam(hidden = true)
            @AuthenticationPrincipal(expression = "user")
            final User user,

            @ApiParam(required = true, name = "id")
            @NotNull
            @PathVariable("id")
            final long id
    ) {
        taskService.deleteTask(id, user);
    }
}