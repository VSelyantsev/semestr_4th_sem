package ru.itis.kpfu.selyantsev.utils.mappers;

import org.mapstruct.Mapper;
import ru.itis.kpfu.selyantsev.dto.request.TaskRequest;
import ru.itis.kpfu.selyantsev.dto.response.TaskResponse;
import ru.itis.kpfu.selyantsev.model.Task;

@Mapper(componentModel = "spring")
public interface TaskMapper {

    Task toEntity(TaskRequest taskRequest);

    TaskResponse toResponse(Task entity);

    TaskRequest toRequest(TaskResponse response);
}
