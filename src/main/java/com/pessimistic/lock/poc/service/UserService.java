package com.pessimistic.lock.poc.service;

import com.pessimistic.lock.poc.dto.UserPathRequestDto;
import com.pessimistic.lock.poc.entitiy.User;

public interface UserService {
    User get(long id);
    User patch(UserPathRequestDto userPathRequestDto);
    User create(User user);
}
