package ru.itis.kpfu.selyantsev.utils.mappers;

import org.mapstruct.Mapper;
import ru.itis.kpfu.selyantsev.dto.request.UserRequest;
import ru.itis.kpfu.selyantsev.dto.response.UserResponse;
import ru.itis.kpfu.selyantsev.model.User;

@Mapper(componentModel = "spring", imports = {TaskMapper.class, BlogMapper.class})
public interface UserMapper {
    User toEntity(UserRequest userRequest);
    UserResponse toResponse(User entity);
    User toEntityFromResponse(UserResponse userResponse);
    UserRequest toRequest(UserResponse userResponse);

    UserRequest toRequestFromEntity(User user);
}
