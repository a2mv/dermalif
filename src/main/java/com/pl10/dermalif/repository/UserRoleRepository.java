package com.pl10.dermalif.repository;

import java.io.Serializable;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pl10.dermalif.entity.User;
import com.pl10.dermalif.entity.UserRole;

@Repository("userRoleRepository")
public interface UserRoleRepository extends JpaRepository<UserRole, Serializable>{

	public abstract Set<UserRole> findByUser(User user); 
	
	public abstract UserRole findByUserAndRole(User user, String role);
}
