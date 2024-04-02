package com.cwa.user.service.respositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cwa.user.service.entities.User;

public interface UserRepository extends JpaRepository<User, String> {

}
