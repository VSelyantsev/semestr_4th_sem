package ru.itis.kpfu.selyantsev.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.kpfu.selyantsev.model.ExecutionStatus;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskResponse {
    private UUID id;
    private String title;
    private String description;
    @Enumerated(EnumType.STRING)
    private ExecutionStatus executionStatus;
    private Date expirationDate;
}
