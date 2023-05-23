package ru.itis.kpfu.selyantsev.utils.mappers;

import org.mapstruct.Mapper;
import ru.itis.kpfu.selyantsev.dto.request.BlogRequest;
import ru.itis.kpfu.selyantsev.dto.response.BlogResponse;
import ru.itis.kpfu.selyantsev.model.Blog;

@Mapper(componentModel = "spring")
public interface BlogMapper {

    Blog toEntity(BlogRequest blogRequest);

    BlogResponse toResponse(Blog blog);
}
