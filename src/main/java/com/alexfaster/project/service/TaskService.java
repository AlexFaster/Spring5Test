package com.alexfaster.project.service;

import com.alexfaster.project.dto.TaskDTO;
import com.alexfaster.project.model.Task;
import com.alexfaster.project.model.User;
import com.alexfaster.project.repository.TaskRepository;
import com.alexfaster.project.service.assembler.TaskAssemblerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    private final TaskAssemblerService taskAssemblerService;

    @Autowired
    public TaskService(TaskRepository taskRepository, TaskAssemblerService taskAssemblerService) {
        this.taskRepository = taskRepository;
        this.taskAssemblerService = taskAssemblerService;
    }

    public List<Task> getTasks(final User user) {
        return taskRepository.findByUser(user);
    }

    public Optional<Task> getTask(final long id, final User user) {
        return taskRepository.findByIdAndUser(id, user);
    }

    public Task addTask(final User user, final TaskDTO taskDTO) {
        final Task task = taskAssemblerService.dtoToEntity(taskDTO);
        task.setUser(user);
        return taskRepository.save(task);
    }

    public Optional<Task> updateTask(final long taskId, final User user, final TaskDTO taskDTO) {
        return getTask(taskId, user)
                .map(task -> taskAssemblerService.mergeWithDTO(task, taskDTO))
                .map(task -> taskRepository.save(task));
    }

    public void deleteTask(final long id, final User user) {
        final Optional<Task> task = getTask(id, user);
        task.ifPresent(taskRepository::delete);
    }
}
