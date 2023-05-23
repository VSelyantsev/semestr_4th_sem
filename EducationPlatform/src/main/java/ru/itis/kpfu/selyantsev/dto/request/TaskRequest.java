package ru.itis.kpfu.selyantsev.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import ru.itis.kpfu.selyantsev.model.ExecutionStatus;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskRequest {

    @NotBlank(message = "Title shouldn't be blank")
    private String title;

    @NotBlank(message = "Description shouldn't be blank")
    private String description;
    @Enumerated(EnumType.STRING)
    private ExecutionStatus executionStatus;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate expirationDate;
}
