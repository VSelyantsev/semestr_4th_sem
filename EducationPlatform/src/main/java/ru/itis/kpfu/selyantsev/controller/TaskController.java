package ru.itis.kpfu.selyantsev.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import ru.itis.kpfu.selyantsev.api.TaskApi;
import ru.itis.kpfu.selyantsev.aspect.annotation.CollectData;
import ru.itis.kpfu.selyantsev.dto.request.TaskRequest;
import ru.itis.kpfu.selyantsev.dto.response.TaskResponse;
import ru.itis.kpfu.selyantsev.service.TaskService;

import javax.transaction.Transactional;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class TaskController implements TaskApi {

    private final TaskService taskService;

    @CollectData
    @Override
    public ResponseEntity<Void> createTask(@ModelAttribute("taskRequest") TaskRequest taskRequest) {
        taskService.createTask(taskRequest);
        return ResponseEntity.ok().build();
    }

    @CollectData
    @Override
    public String updateTask(@ModelAttribute("taskRequest") TaskRequest entity) {
        taskService.updateTask(entity);
        return "dashboard";
    }

    @CollectData
    @Override
    @Transactional
    public String deleteTaskByTitle(@ModelAttribute("taskRequest") TaskRequest taskRequest) {
        taskService.deleteTaskByTitle(taskRequest);
        return "dashboard";
    }

    @CollectData
    @Override
    public ResponseEntity<TaskResponse> findTaskByTitle(String title) {
        return ResponseEntity.ok(taskService.findTaskByTitle(title));
    }

    @CollectData
    @Override
    public List<TaskResponse> findAll() {
        return taskService.findAll();
    }
}
