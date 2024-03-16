package com.example.parking.repository;

import com.example.parking.models.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends RepositoryBase<User,Long>{
    User findByPasswordIs(String password);
}
