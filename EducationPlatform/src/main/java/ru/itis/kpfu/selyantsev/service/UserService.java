package ru.itis.kpfu.selyantsev.service;

import ru.itis.kpfu.selyantsev.dto.request.UserRequest;
import ru.itis.kpfu.selyantsev.dto.response.UserResponse;
import ru.itis.kpfu.selyantsev.model.User;

import java.util.List;
import java.util.UUID;

public interface UserService {
    void createUser(UserRequest userRequest);

    UserResponse updateUser(UserRequest entity);

    void deleteUserById(UUID id);

    UserResponse findUserById(UUID id);

    UserResponse findUserByUsername(String username);

    List<UserResponse> findAll();

    User getCurrentUser();
}
