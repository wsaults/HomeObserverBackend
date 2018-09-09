package com.saults.HomeObserverBackend.Repositories;

import com.saults.HomeObserverBackend.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface UserRepository extends JpaRepository<User, Long> {
    Collection<User> findByName(String name);
}
