package com.sleep.studyboot.core.user;

import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUser(UserAuth userAuth) {
        return userRepository.findByUserAuth(userAuth)
                .orElseGet(() -> userRepository.save(User.signUp(userAuth)));
    }
}
