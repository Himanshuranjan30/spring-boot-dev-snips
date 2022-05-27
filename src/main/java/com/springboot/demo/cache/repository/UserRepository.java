package com.springboot.demo.cache.repository;

import com.springboot.demo.cache.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long>  {
}
