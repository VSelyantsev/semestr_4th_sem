package ru.itis.kpfu.selyantsev.model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.UUID;

@Entity(name = "t_role")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    private String name;

    @Override
    public String getAuthority() {
        return name;
    }
}
