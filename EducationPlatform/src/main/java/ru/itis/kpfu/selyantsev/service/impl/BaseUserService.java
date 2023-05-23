package ru.itis.kpfu.selyantsev.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.kpfu.selyantsev.dto.request.UserRequest;
import ru.itis.kpfu.selyantsev.dto.response.UserResponse;
import ru.itis.kpfu.selyantsev.model.Subscription;
import ru.itis.kpfu.selyantsev.model.Role;
import ru.itis.kpfu.selyantsev.model.Task;
import ru.itis.kpfu.selyantsev.model.User;
import ru.itis.kpfu.selyantsev.repository.UserRepository;
import ru.itis.kpfu.selyantsev.service.UserService;
import ru.itis.kpfu.selyantsev.utils.mappers.UserMapper;

import java.util.*;

@Service
@RequiredArgsConstructor
public class BaseUserService implements UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;
    private final UserMapper mapper;

    @Override
    public void createUser(UserRequest userRequest) {
        String encodedPassword = encoder.encode(userRequest.getPassword());
        List<Task> tasks = new ArrayList<>();
        Set<Role> roles = new HashSet<>();
        List<Subscription> followers = new ArrayList<>();
        Role role = Role.builder()
                .name("USER")
                .build();
        roles.add(role);
        User entityToSave = mapper.toEntity(userRequest);
        entityToSave.setPassword(encodedPassword);
        entityToSave.setTasks(tasks);
        entityToSave.setRoles(roles);
        entityToSave.setFollowers(followers);
        userRepository.save(entityToSave);
    }

    @Override
    public UserResponse updateUser(UserRequest entity) {
        User currentUser = getCurrentUser();
        User entityToResponse = userRepository.findUserByEmail(currentUser.getEmail())
                .orElseThrow();
        User user = mapper.toEntity(entity);
        entityToResponse.setUsername(user.getUsername());
        userRepository.save(entityToResponse);
        return mapper.toResponse(entityToResponse);
    }

    @Override
    public void deleteUserById(UUID id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserResponse findUserById(UUID id) {
        return mapper.toResponse(userRepository.findById(id).orElseThrow());
    }

    @Override
    public UserResponse findUserByUsername(String username) {
        return mapper.toResponse(userRepository.findUserByUsername(username).orElseThrow());
    }

    @Override
    public List<UserResponse> findAll() {
        return userRepository.findAll()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUserEmail = authentication.getName();
            return userRepository.findUserByEmail(currentUserEmail).orElseThrow();
        }
        return null;
    }
}
