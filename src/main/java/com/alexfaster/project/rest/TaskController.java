package com.alexfaster.project.rest;

import com.alexfaster.project.model.Task;
import com.alexfaster.project.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/tasks", produces = "application/json")
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public List<Task> getTasks() {
        return taskService.getTasks();
    }

    @GetMapping
    @RequestMapping("/{id}")
    public Task getTask(@PathVariable("id") long id) {
        return taskService.getTask(id)
                .orElseThrow(() -> new RuntimeException("Not Found"));
    }
}
