package ru.itis.kpfu.selyantsev.service;

import ru.itis.kpfu.selyantsev.dto.request.TaskRequest;
import ru.itis.kpfu.selyantsev.dto.response.TaskResponse;
import ru.itis.kpfu.selyantsev.model.Task;

import java.util.List;

public interface TaskService {
    void createTask(TaskRequest taskRequest);

    TaskResponse updateTask(TaskRequest entity);

    void deleteTaskByTitle(TaskRequest taskRequest);

    TaskResponse findTaskByTitle(String title);

    TaskResponse findTaskByUserId();

    List<Task> findAllTaskByUserId();

    List<TaskResponse> findAll();

}
