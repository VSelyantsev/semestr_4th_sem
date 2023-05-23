package ru.itis.kpfu.selyantsev.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.kpfu.selyantsev.api.AdminApi;
import ru.itis.kpfu.selyantsev.dto.response.UserResponse;
import ru.itis.kpfu.selyantsev.model.LogEntity;
import ru.itis.kpfu.selyantsev.service.AdminService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AdminController implements AdminApi {

    private final AdminService adminService;

    @Override
    public List<UserResponse> findAllUsers() {
        return adminService.findAllUsers();
    }

    @Override
    public List<LogEntity> getHistoryRequest(String email) {
        return adminService.getHistoryRequests(email);
    }
}
