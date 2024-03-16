package com.example.parking.repository;

import com.example.parking.models.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

@NoRepositoryBean
public interface RepositoryBase <T extends BaseEntity<ID>, ID extends Serializable> extends JpaRepository<T, ID> {
}
