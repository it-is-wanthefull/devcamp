package org.sparta.backmaterialspring.auth.service.impl;

import lombok.RequiredArgsConstructor;
import org.sparta.backmaterialspring.auth.dto.CreateUserDto;
import org.sparta.backmaterialspring.auth.dto.SignupResponseDto;
import org.sparta.backmaterialspring.auth.entity.User;
import org.sparta.backmaterialspring.auth.repository.UserRepository;
import org.sparta.backmaterialspring.auth.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public SignupResponseDto signup(CreateUserDto dto) {
        Optional<User> userByEmail = userRepository.findByEmail(dto.getEmail());
        if(userByEmail.isPresent()) {
            throw new RuntimeException(dto.getEmail() + "already exist");
        }

        User createUser = new User(
                dto.getName(),
                dto.getEmail(),
                passwordEncoder.encode(dto.getPassword()),
                dto.getPhone(),
                dto.getUserRole()
        );
        userRepository.save(createUser);

        return new SignupResponseDto(
                createUser.getId(),
                createUser.getName(),
                createUser.getEmail(),
                createUser.getPhone()
        );
    }
}
