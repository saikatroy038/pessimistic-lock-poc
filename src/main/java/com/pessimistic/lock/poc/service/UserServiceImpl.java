package com.pessimistic.lock.poc.service;

import com.pessimistic.lock.poc.dto.UserPathRequestDto;
import com.pessimistic.lock.poc.entitiy.User;
import com.pessimistic.lock.poc.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User get(long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            return userOptional.get();
        } else {
            return null;
        }
    }

    @Transactional
    @Override
    public User patch(UserPathRequestDto userPathRequestDto) {
        User user = userRepository.findByIdWithLock(userPathRequestDto.getId());

        if (userPathRequestDto.getAddress() != null) {
            user.setName(userPathRequestDto.getAddress());
        }

        if (userPathRequestDto.getName() != null) {
            user.setName(userPathRequestDto.getName());
        }

        // Making the thread wait for testing purpose
        try {
            Thread.sleep(600000); // 1hr
        } catch (InterruptedException e) {}

        return userRepository.save(user);
    }

    @Override
    public User create(User user) {
        return userRepository.save(user);
    }
}
