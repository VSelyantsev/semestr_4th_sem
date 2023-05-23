package ru.itis.kpfu.selyantsev.aspect;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import ru.itis.kpfu.selyantsev.aspect.constants.Message;
import ru.itis.kpfu.selyantsev.model.LogEntity;
import ru.itis.kpfu.selyantsev.model.User;
import ru.itis.kpfu.selyantsev.service.LogService;
import ru.itis.kpfu.selyantsev.service.UserService;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class SystemArchitecture {

    private final UserService userService;
    private final LogService logService;

    @Pointcut("@annotation(ru.itis.kpfu.selyantsev.aspect.annotation.CollectData)")
    public void onAllMethods() {}

    @Around("onAllMethods()")
    public Object logToCollectData(ProceedingJoinPoint point) throws Throwable {
        User currentUser = userService.getCurrentUser();

        MethodSignature methodSignature = (MethodSignature) point.getSignature();
        String methodName = methodSignature.getName();

        Object result = point.proceed();

        log.info(Message.AFTER, currentUser.getEmail(), methodName);
        LogEntity logEntityToSave = LogEntity.builder()
                .executionDate(LocalDate.now())
                .userEmail(currentUser.getEmail())
                .methodName(methodName)
                .severity("INFO")
                .user(currentUser)
                .build();
        logService.createLogEntity(logEntityToSave);
        return result;
    }

    @AfterThrowing(pointcut = "execution(* ru.itis.kpfu.selyantsev..*())", throwing = "exception")
    public void logException(JoinPoint point, Exception exception) {
        log.error(Message.AFTER_THROWING, point.getSignature().getDeclaringTypeName(),
                point.getSignature().getName(), exception.getMessage());
    }
}
