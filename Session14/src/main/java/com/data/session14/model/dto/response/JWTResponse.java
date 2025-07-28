package com.data.session14.model.dto.response;

import com.data.session14.model.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JWTResponse {
    private String username;
    private String password;
    private String email;
    private Status status;
    private String token;
    private Collection<? extends GrantedAuthority> authorities;
}
