package ru.itis.kpfu.selyantsev.service;

import ru.itis.kpfu.selyantsev.dto.response.UserResponse;
import ru.itis.kpfu.selyantsev.model.LogEntity;
import ru.itis.kpfu.selyantsev.model.User;

import java.util.List;

public interface AdminService {

    List<UserResponse> findAllUsers();
    List<LogEntity> getHistoryRequests(String email);
}
