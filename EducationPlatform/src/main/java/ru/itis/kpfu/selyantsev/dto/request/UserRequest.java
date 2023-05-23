package ru.itis.kpfu.selyantsev.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.kpfu.selyantsev.model.Role;
import ru.itis.kpfu.selyantsev.model.Task;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRequest {
    @NotBlank(message = "Username shouldn't be blank")
    @Size(min = 6, max = 12, message = "Must be in range of 6 to 12 characters")
    private String username;

    @NotBlank(message = "Email shouldn't be blank")
    @Email
    private String email;

    @NotBlank(message = "Password shouldn't be blank")
    @Pattern(regexp = "^(?=.*\\d)(?=.*[A-Z]).+$",
            message = "Password must contain at least one capital letter and at least one number")
    private String password;
    private Set<Role> roles;
    private List<Task> tasks;
}
