package com.exercise.userservice.repository;




import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.exercise.userservice.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	User finById(Integer id);
}
