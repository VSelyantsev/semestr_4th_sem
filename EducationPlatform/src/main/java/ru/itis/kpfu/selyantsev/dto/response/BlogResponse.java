package ru.itis.kpfu.selyantsev.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.kpfu.selyantsev.model.User;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BlogResponse {

    private String title;
    private String content;
    private User user;
}
