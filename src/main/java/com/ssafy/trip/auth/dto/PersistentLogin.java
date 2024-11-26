package com.ssafy.trip.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;

import java.util.Date;

@Builder
@Data
public class PersistentLogin {
    private String userName;
    private String series;
    private String token;
    private Date lastUsed;

    public PersistentLogin(PersistentRememberMeToken token) {
        this.token = String.valueOf(token);
    }
}
