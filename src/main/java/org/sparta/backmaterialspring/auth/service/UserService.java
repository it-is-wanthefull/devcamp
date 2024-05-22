package org.sparta.backmaterialspring.auth.service;

import org.sparta.backmaterialspring.auth.dto.CreateUserDto;
import org.sparta.backmaterialspring.auth.dto.SignupResponseDto;

public interface UserService {
    /**
     * 회원가입
     * @param createUserDto : email, password
     * @return SignupResponseDto
     */
    SignupResponseDto signup(CreateUserDto createUserDto);
}
