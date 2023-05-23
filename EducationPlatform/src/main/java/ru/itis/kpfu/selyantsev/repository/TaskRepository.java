package ru.itis.kpfu.selyantsev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itis.kpfu.selyantsev.model.Task;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TaskRepository extends JpaRepository<Task, UUID> {
    Optional<Task> findTaskByTitle(String title);
    Optional<Task> findTaskByUserId(UUID id);
    List<Task> findAllTaskByUserId(UUID id);
    void deleteTaskByTitle(String pathVariable);
}
