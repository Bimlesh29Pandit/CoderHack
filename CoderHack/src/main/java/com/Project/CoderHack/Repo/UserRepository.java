package com.Project.CoderHack.Repo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.Project.CoderHack.Model.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    List<User> findAllByOrderByScoreDesc();
}
