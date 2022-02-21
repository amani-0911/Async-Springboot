package com.ex1mcs.microser.repository;

import com.ex1mcs.microser.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
