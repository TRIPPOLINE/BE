package com.ssafy.trip.auth.service;

import com.ssafy.trip.auth.dto.CustomerUserDetails;
import com.ssafy.trip.user.dto.UserDto;
import com.ssafy.trip.user.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerUserDetailService implements UserDetailsService {
    private final UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDto userData = userMapper.selectUser(username);
        if(username!=null){
            return new CustomerUserDetails(userData);
        }
        throw new UsernameNotFoundException(username+"사용자를 찾을 수 없습니다"); //TODO : 리팩터링
    }
}
