package com.sleep.studyboot.core.user;

import com.sleep.studyboot.exception.UserException;
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

    public User getUser(long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserException("사용자가 존재하지 않습니다."));
    }
}
