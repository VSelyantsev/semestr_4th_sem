package ru.itis.kpfu.selyantsev.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itis.kpfu.selyantsev.dto.request.UserRequest;
import ru.itis.kpfu.selyantsev.model.LogEntity;
import ru.itis.kpfu.selyantsev.model.User;
import ru.itis.kpfu.selyantsev.repository.LogRepository;
import ru.itis.kpfu.selyantsev.repository.UserRepository;
import ru.itis.kpfu.selyantsev.service.LogService;
import ru.itis.kpfu.selyantsev.service.UserService;
import ru.itis.kpfu.selyantsev.utils.mappers.UserMapper;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BaseLogService implements LogService {
    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final LogRepository logRepository;

    @Override
    public void createLogEntity(LogEntity logEntity) {
        logRepository.save(logEntity);
    }

    @Override
    public List<LogEntity> historyRequest(String email) {
        User userFromDB = userRepository.findUserByEmail(email).orElseThrow();
        return logRepository.findAll()
                .stream()
                .filter(logEntity -> logEntity.getUser().getId() == userFromDB.getId())
                .toList();
    }
}
