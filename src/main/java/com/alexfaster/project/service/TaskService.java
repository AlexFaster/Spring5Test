package com.alexfaster.project.service;

import com.alexfaster.project.model.Task;
import com.alexfaster.project.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    @Autowired
    public TaskService(final TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> getTasks() {
        return taskRepository.findAll();
    }

    public Optional<Task> getTask(final long id) {
        return taskRepository.findById(id);
    }

    public Task addTask(final Task task) {
        return taskRepository.save(task);
    }

    public Task updateTask(final Task task) {
        return taskRepository.save(task);
    }

    public void deleteTask(final long id) {
        taskRepository.deleteById(id);
    }
}
