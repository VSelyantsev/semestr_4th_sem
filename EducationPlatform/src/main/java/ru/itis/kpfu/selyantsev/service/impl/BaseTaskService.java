package ru.itis.kpfu.selyantsev.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.itis.kpfu.selyantsev.dto.request.TaskRequest;
import ru.itis.kpfu.selyantsev.dto.response.TaskResponse;
import ru.itis.kpfu.selyantsev.model.ExecutionStatus;
import ru.itis.kpfu.selyantsev.model.Task;
import ru.itis.kpfu.selyantsev.model.User;
import ru.itis.kpfu.selyantsev.repository.TaskRepository;
import ru.itis.kpfu.selyantsev.service.TaskService;
import ru.itis.kpfu.selyantsev.service.UserService;
import ru.itis.kpfu.selyantsev.utils.mappers.TaskMapper;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class BaseTaskService implements TaskService {

    private final TaskRepository taskRepository;
    private final UserService userService;
    private final TaskMapper mapper;

    @Override
    public void createTask(TaskRequest taskRequest) {
        User user = userService.getCurrentUser();
        Task taskToSave = mapper.toEntity(taskRequest);
        taskToSave.setUser(user);
        taskToSave.setExecutionStatus(ExecutionStatus.IN_PROGRESS);
        taskRepository.save(taskToSave);
    }

    @Override
    public TaskResponse updateTask(TaskRequest entity) {
        User currentUser = userService.getCurrentUser();
        Task taskToResponse = taskRepository.findTaskByUserId(currentUser.getId())
                .orElseThrow();
        Task task = mapper.toEntity(entity);
        taskToResponse.setTitle(task.getTitle());
        taskToResponse.setDescription(task.getDescription());
        taskToResponse.setExecutionStatus(ExecutionStatus.IN_PROGRESS);
        taskToResponse.setExpirationDate(entity.getExpirationDate());
        taskRepository.save(taskToResponse);
        return mapper.toResponse(taskToResponse);
    }

    @Override
    public void deleteTaskByTitle(TaskRequest taskRequest) {
        String title = taskRequest.getTitle();
        taskRepository.deleteTaskByTitle(title);
    }

    @Override
    public TaskResponse findTaskByTitle(String title) {
        User currentUser = userService.getCurrentUser();
        Task currentTask = taskRepository.findTaskByTitle(title).orElseThrow();
        if (currentTask.getUser().getId() == currentUser.getId()) {
            return mapper.toResponse(currentTask);
        } else {
            // change to log
        }
        return null;
    }

    @Override
    public TaskResponse findTaskByUserId() {
        User entity = userService.getCurrentUser();
        return mapper.toResponse(taskRepository.findTaskByUserId(entity.getId()).
                orElseThrow(null));
    }

    @Override
    public List<Task> findAllTaskByUserId() {
        User user = userService.getCurrentUser();
        return taskRepository.findAllTaskByUserId(user.getId());
    }

    @Override
    public List<TaskResponse> findAll() {
        User currentUser = userService.getCurrentUser();
        return taskRepository.findAll()
                .stream()
                .filter(task -> task.getUser().getId() == currentUser.getId())
                .map(mapper::toResponse).toList();
    }
}
