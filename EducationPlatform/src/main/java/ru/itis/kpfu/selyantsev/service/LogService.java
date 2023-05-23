package ru.itis.kpfu.selyantsev.service;

import ru.itis.kpfu.selyantsev.dto.request.UserRequest;
import ru.itis.kpfu.selyantsev.model.LogEntity;

import java.util.List;

public interface LogService {

    void createLogEntity(LogEntity logEntity);
    List<LogEntity> historyRequest(String email);
}
