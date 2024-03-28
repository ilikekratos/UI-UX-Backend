package com.example.parking.repository;

import com.example.parking.models.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends RepositoryBase<User,Long>{
    User findByPasswordIs(String password);
    Boolean existsUserByUsername(String username);
    Optional<User> findByUsername(String username);

}
