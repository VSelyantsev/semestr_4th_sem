package ru.itis.kpfu.selyantsev.api;

import org.springframework.web.bind.annotation.*;
import ru.itis.kpfu.selyantsev.dto.response.UserResponse;
import ru.itis.kpfu.selyantsev.model.LogEntity;

import java.util.List;

@RequestMapping("/api/admin")
public interface AdminApi {

    @GetMapping("/all")
    @ResponseBody
    List<UserResponse> findAllUsers();

    @GetMapping("/history/{email}")
    @ResponseBody
    List<LogEntity> getHistoryRequest(@PathVariable("email") String email);
}
