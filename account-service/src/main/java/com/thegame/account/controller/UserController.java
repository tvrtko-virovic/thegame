package com.thegame.account.controller;

import com.thegame.account.dto.CreateUserRequestDto;
import com.thegame.account.dto.UserResponseDto;
import com.thegame.account.entity.User;
import com.thegame.account.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * REST controller for User operations.
 * Provides endpoints for user management and information retrieval.
 */
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Register a new user (public-facing endpoint).
     * Accepts plain password which will be securely hashed server-side.
     */
    @PostMapping
    public ResponseEntity<?> createUser(@Valid @RequestBody CreateUserRequestDto createUserRequestDto) {
        try {
            User user = userService.createUser(
                createUserRequestDto.getUsername(),
                createUserRequestDto.getEmail(),
                createUserRequestDto.getPassword()
            );
            // Return safe response DTO without password hash
            UserResponseDto userResponseDto = new UserResponseDto(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(userResponseDto);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    /**
     * Get user by ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable UUID id) {
        return userService.findById(id)
                .map(user -> ResponseEntity.ok(new UserResponseDto(user)))
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Get user by username
     */
    @GetMapping("/username/{username}")
    public ResponseEntity<?> getUserByUsername(@PathVariable String username) {
        return userService.findByUsername(username)
                .map(user -> ResponseEntity.ok(new UserResponseDto(user)))
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Get user by email
     */
    @GetMapping("/email/{email}")
    public ResponseEntity<?> getUserByEmail(@PathVariable String email) {
        return userService.findByEmail(email)
                .map(user -> ResponseEntity.ok(new UserResponseDto(user)))
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Get all users
     */
    @GetMapping
    public ResponseEntity<List<UserResponseDto>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        List<UserResponseDto> userResponseDtoList = users.stream()
                .map(UserResponseDto::new)
                .toList();
        return ResponseEntity.ok(userResponseDtoList);
    }

    /**
     * Get active users only
     */
    @GetMapping("/active")
    public ResponseEntity<List<UserResponseDto>> getActiveUsers() {
        List<User> users = userService.getActiveUsers();
        List<UserResponseDto> userResponseDtoList = users.stream()
                .map(UserResponseDto::new)
                .toList();
        return ResponseEntity.ok(userResponseDtoList);
    }

    /**
     * Get verified users only
     */
    @GetMapping("/verified")
    public ResponseEntity<List<UserResponseDto>> getVerifiedUsers() {
        List<User> users = userService.getVerifiedUsers();
        List<UserResponseDto> userResponseDtoList = users.stream()
                .map(UserResponseDto::new)
                .toList();
        return ResponseEntity.ok(userResponseDtoList);
    }

    /**
     * Update user
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable UUID id, @RequestBody User user) {
        try {
            user.setId(id); // Ensure the ID matches the path variable
            User updatedUser = userService.updateUser(user);
            return ResponseEntity.ok(new UserResponseDto(updatedUser));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    /**
     * Activate user
     */
    @PutMapping("/{id}/activate")
    public ResponseEntity<?> activateUser(@PathVariable UUID id) {
        try {
            User user = userService.activateUser(id);
            return ResponseEntity.ok(new UserResponseDto(user));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    /**
     * Deactivate user
     */
    @PutMapping("/{id}/deactivate")
    public ResponseEntity<?> deactivateUser(@PathVariable UUID id) {
        try {
            User user = userService.deactivateUser(id);
            return ResponseEntity.ok(new UserResponseDto(user));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    /**
     * Verify user email
     */
    @PutMapping("/{id}/verify-email")
    public ResponseEntity<?> verifyUserEmail(@PathVariable UUID id) {
        try {
            User user = userService.verifyUserEmail(id);
            return ResponseEntity.ok(new UserResponseDto(user));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    /**
     * Delete user
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable UUID id) {
        try {
            userService.deleteUser(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    /**
     * Check if username exists
     */
    @GetMapping("/exists/username/{username}")
    public ResponseEntity<Map<String, Boolean>> checkUsernameExists(@PathVariable String username) {
        boolean exists = userService.usernameExists(username);
        return ResponseEntity.ok(Map.of("exists", exists));
    }

    /**
     * Check if email exists
     */
    @GetMapping("/exists/email/{email}")
    public ResponseEntity<Map<String, Boolean>> checkEmailExists(@PathVariable String email) {
        boolean exists = userService.emailExists(email);
        return ResponseEntity.ok(Map.of("exists", exists));
    }

    /**
     * Get user statistics
     */
    @GetMapping("/stats")
    public ResponseEntity<Map<String, Object>> getUserStats() {
        Map<String, Object> stats = Map.of(
            "totalUsers", userService.getUserCount(),
            "activeUsers", userService.getActiveUserCount(),
            "verifiedUsers", userService.getVerifiedUserCount()
        );
        return ResponseEntity.ok(stats);
    }

}
