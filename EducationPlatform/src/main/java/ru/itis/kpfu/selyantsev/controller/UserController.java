package ru.itis.kpfu.selyantsev.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import ru.itis.kpfu.selyantsev.api.UserApi;
import ru.itis.kpfu.selyantsev.aspect.annotation.CollectData;
import ru.itis.kpfu.selyantsev.dto.request.UserRequest;
import ru.itis.kpfu.selyantsev.dto.response.UserResponse;
import ru.itis.kpfu.selyantsev.service.UserService;

import java.util.List;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class UserController implements UserApi {

    private final UserService userService;

    @Override
    public String createUser(@ModelAttribute("userRequest") UserRequest userRequest) {
         userService.createUser(userRequest);
         return "login";
    }

    @CollectData
    @Override
    public String updateUser(@ModelAttribute("user") UserRequest entity) {
        userService.updateUser(entity);
        return "profile";
    }

    @CollectData
    @Override
    public void deleteUserById(UUID userId) {
        userService.deleteUserById(userId);
    }

    @CollectData
    @Override
    public UserResponse findUserById(UUID userId) {
        return userService.findUserById(userId);
    }

    @CollectData
    @Override
    public List<UserResponse> findAll() {
        return userService.findAll();
    }
}
