package ru.itis.kpfu.selyantsev.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ExecutionStatus {
    IN_PROGRESS,
    COMPLETED,
    CANCELED
}
