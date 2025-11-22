package com.mongospring.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.mongospring.model.Student;

public interface StudentRepository extends MongoRepository<Student, String> {
}
