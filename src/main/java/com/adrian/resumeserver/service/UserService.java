package com.adrian.resumeserver.service;

import com.adrian.resumeserver.dto.user.UserRequestDto;
import com.adrian.resumeserver.dto.user.UserResponseDto;
import com.adrian.resumeserver.model.User;
import com.adrian.resumeserver.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponseDto createUser(UserRequestDto dto) {
        User user = new User();
        user.setEmail(dto.getEmail());
        user.setFullName(dto.getFullName());
        user.setTitle(dto.getTitle());
        user.setLocation(dto.getLocation());
        user.setSummary(dto.getSummary());
        User saved = userRepository.save(user);
        return toResponseDto(saved);
    }

    public Optional<UserResponseDto> getUserById(Long id) {
        return userRepository.findById(id).map(this::toResponseDto);
    }

    public Optional<UserResponseDto> updateUser(Long id, UserRequestDto dto) {
        return userRepository.findById(id).map(user -> {
            user.setEmail(dto.getEmail());
            user.setFullName(dto.getFullName());
            user.setTitle(dto.getTitle());
            user.setLocation(dto.getLocation());
            user.setSummary(dto.getSummary());
            return toResponseDto(userRepository.save(user));
        });
    }

    private UserResponseDto toResponseDto(User user) {
        UserResponseDto dto = new UserResponseDto();
        dto.setId(user.getId());
        dto.setEmail(user.getEmail());
        dto.setFullName(user.getFullName());
        dto.setTitle(user.getTitle());
        dto.setLocation(user.getLocation());
        dto.setSummary(user.getSummary());
        dto.setCreatedAt(user.getCreatedAt());
        return dto;
    }
}