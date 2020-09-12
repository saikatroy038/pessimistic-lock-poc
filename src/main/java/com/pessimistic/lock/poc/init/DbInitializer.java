package com.pessimistic.lock.poc.init;

import com.pessimistic.lock.poc.entitiy.User;
import com.pessimistic.lock.poc.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * This class initialises the db with dummy data
 */
@Component
public class DbInitializer {

    @Autowired
    private UserRepository userRepository;

    @PostConstruct
    public void setup() {

        userRepository.deleteAll();

        User user1 = new User();
        user1.setName("Ravi");
        user1.setAddress("Bangalore");

        User user2 = new User();
        user2.setName("Jenny");
        user2.setAddress("Mumbai");

        User user3 = new User();
        user3.setName("Bembem");
        user3.setAddress("Singapore");

        User user4 = new User();
        user4.setName("Bob");
        user4.setAddress("NY");

        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);
        userRepository.save(user4);
    }
}
