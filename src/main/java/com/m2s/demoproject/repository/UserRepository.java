package com.m2s.demoproject.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.m2s.demoproject.entity.User;

@Repository
@Transactional(value = "mysqlTransactionManager")
public interface UserRepository extends JpaRepository<User, Long> {

	List<User> findAllByNameLike(String search);

}
