package com.pessimistic.lock.poc.repository;

import com.pessimistic.lock.poc.entitiy.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Uses PESSIMISTIC_WRITE lock. <br />
     * Must be called within a transaction.
     */
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @QueryHints({@QueryHint(name = "javax.persistence.lock.timeout", value = "3000")})  // value = 3000 indicates 3000ms | "-2" indicates skip locked
    @Query(value = "FROM User u WHERE u.id = ?1")
    User findByIdWithLock(Long id);
}
