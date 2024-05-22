package org.sparta.backmaterialspring.auth.dto;

import lombok.Getter;
import org.sparta.backmaterialspring.auth.entity.UserRole;

@Getter
public class CreateUserDto {
    private String name;
    private String email;
    private String password;
    private String phone;
    private UserRole userRole = UserRole.USER;
}
