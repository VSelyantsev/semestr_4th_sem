package ru.itis.kpfu.selyantsev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.kpfu.selyantsev.model.LogEntity;

import java.util.UUID;

public interface LogRepository extends JpaRepository<LogEntity, UUID> {
}
