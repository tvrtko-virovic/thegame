package com.thegame.account.service;

import com.thegame.account.entity.User;
import com.thegame.account.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Service class for User-related business logic.
 * Handles user operations like registration, authentication, and profile management.
 */
@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Create a new user
     */
    public User createUser(String username, String email, String passwordHash) {
        // Check if username already exists
        if (userRepository.existsByUsernameIgnoreCase(username)) {
            throw new IllegalArgumentException("Username already exists: " + username);
        }

        // Check if email already exists
        if (userRepository.existsByEmailIgnoreCase(email)) {
            throw new IllegalArgumentException("Email already exists: " + email);
        }

        User user = new User(username, email, passwordHash);
        return userRepository.save(user);
    }

    /**
     * Find user by ID
     */
    @Transactional(readOnly = true)
    public Optional<User> findById(UUID id) {
        return userRepository.findById(id);
    }

    /**
     * Find user by username
     */
    @Transactional(readOnly = true)
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsernameIgnoreCase(username);
    }

    /**
     * Find user by email
     */
    @Transactional(readOnly = true)
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmailIgnoreCase(email);
    }

    /**
     * Get all users
     */
    @Transactional(readOnly = true)
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    /**
     * Get all active users
     */
    @Transactional(readOnly = true)
    public List<User> getActiveUsers() {
        return (List<User>) userRepository.findActiveUsers();
    }

    /**
     * Get all verified users
     */
    @Transactional(readOnly = true)
    public List<User> getVerifiedUsers() {
        return (List<User>) userRepository.findVerifiedUsers();
    }

    /**
     * Update user information
     */
    public User updateUser(User user) {
        if (user.getId() == null) {
            throw new IllegalArgumentException("User ID cannot be null for update");
        }
        
        if (!userRepository.existsById(user.getId())) {
            throw new IllegalArgumentException("User not found with ID: " + user.getId());
        }

        return userRepository.save(user);
    }

    /**
     * Activate user account
     */
    public User activateUser(UUID userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + userId));
        
        user.activate();
        return userRepository.save(user);
    }

    /**
     * Deactivate user account
     */
    public User deactivateUser(UUID userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + userId));
        
        user.deactivate();
        return userRepository.save(user);
    }

    /**
     * Verify user email
     */
    public User verifyUserEmail(UUID userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + userId));
        
        user.verifyEmail();
        return userRepository.save(user);
    }

    /**
     * Delete user by ID
     */
    public void deleteUser(UUID userId) {
        if (!userRepository.existsById(userId)) {
            throw new IllegalArgumentException("User not found with ID: " + userId);
        }
        userRepository.deleteById(userId);
    }

    /**
     * Check if username exists
     */
    @Transactional(readOnly = true)
    public boolean usernameExists(String username) {
        return userRepository.existsByUsernameIgnoreCase(username);
    }

    /**
     * Check if email exists
     */
    @Transactional(readOnly = true)
    public boolean emailExists(String email) {
        return userRepository.existsByEmailIgnoreCase(email);
    }

    /**
     * Get user count
     */
    @Transactional(readOnly = true)
    public long getUserCount() {
        return userRepository.count();
    }

    /**
     * Get active user count
     */
    @Transactional(readOnly = true)
    public long getActiveUserCount() {
        return userRepository.countActiveUsers();
    }

    /**
     * Get verified user count
     */
    @Transactional(readOnly = true)
    public long getVerifiedUserCount() {
        return userRepository.countVerifiedUsers();
    }
}
