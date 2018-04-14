package com.alexfaster.project.repository;

import com.alexfaster.project.model.Task;
import com.alexfaster.project.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    Optional<Task> findByIdAndUser(final long id, final User user);

    List<Task> findByUser(final User user);
}
