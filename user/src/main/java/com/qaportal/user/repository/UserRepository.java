package com.qaportal.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qaportal.user.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

	boolean existsByemailid(String emailid);

	UserEntity findByemailid(String emailid);

	UserEntity findByuserid(int userid);

}

