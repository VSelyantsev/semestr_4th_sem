package ru.itis.kpfu.selyantsev.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itis.kpfu.selyantsev.dto.response.UserResponse;
import ru.itis.kpfu.selyantsev.model.LogEntity;
import ru.itis.kpfu.selyantsev.model.Role;
import ru.itis.kpfu.selyantsev.model.User;
import ru.itis.kpfu.selyantsev.repository.LogRepository;
import ru.itis.kpfu.selyantsev.repository.UserRepository;
import ru.itis.kpfu.selyantsev.service.AdminService;
import ru.itis.kpfu.selyantsev.service.LogService;
import ru.itis.kpfu.selyantsev.service.UserService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BaseAdminService implements AdminService {

    private final UserService userService;
    private final LogService logService;


    @Override
    public List<UserResponse> findAllUsers() {
        return userService.findAll();
    }

    @Override
    public List<LogEntity> getHistoryRequests(String email) {
        return logService.historyRequest(email);
    }
}
