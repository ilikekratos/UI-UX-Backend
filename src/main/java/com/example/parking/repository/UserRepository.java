package com.example.parking.repository;

import com.example.parking.models.User;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends RepositoryBase<User,Long>{
    User findByPasswordIs(String password);
    Boolean existsUserByUsername(String username);
}
