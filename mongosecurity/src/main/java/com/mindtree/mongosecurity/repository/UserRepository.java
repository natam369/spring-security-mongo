package com.mindtree.mongosecurity.repository;

import java.math.BigInteger;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.mindtree.mongosecurity.model.Users;

@Repository
public interface UserRepository extends MongoRepository<Users, BigInteger>{
	Users findByUsername(String username);
}
