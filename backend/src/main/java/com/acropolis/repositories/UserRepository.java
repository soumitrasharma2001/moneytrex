package com.acropolis.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.acropolis.entity.Users;

@Repository
public interface UserRepository extends JpaRepository<Users, Integer> {

	Users findByEmailAndPassword(String email, String password);
	
	List<Users> findByRole(String role);

	Users findByEmail(String username);

}
