package com.ssafy.trip.auth;

import com.ssafy.trip.auth.dto.PersistentLogin;
import com.ssafy.trip.auth.mapper.PersistentLoginMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class MyBatisPersistentTokenRepository implements PersistentTokenRepository {

    @Autowired
    private PersistentLoginMapper persistentLoginMapper;

    @Override
    public void createNewToken(PersistentRememberMeToken token) {
        PersistentLogin persistentLogin = new PersistentLogin(token);
        persistentLoginMapper.createNewToken(persistentLogin);
    }

    @Override
    public PersistentRememberMeToken getTokenForSeries(String seriesId) {
        PersistentLogin persistentLogin = persistentLoginMapper.getTokenForSeries(seriesId);
        if (persistentLogin != null) {
            return new PersistentRememberMeToken(persistentLogin.getUserName(),
                    persistentLogin.getSeries(), persistentLogin.getToken(),
                    persistentLogin.getLastUsed());
        }
        return null;
    }

    @Override
    public void updateToken(String series, String tokenValue, Date lastUsed) {
        persistentLoginMapper.updateToken(series, tokenValue, lastUsed);
    }

    @Override
    public void removeUserTokens(String username) {
        persistentLoginMapper.removeUserTokens(username);
    }
}