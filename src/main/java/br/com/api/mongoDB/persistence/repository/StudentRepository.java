package br.com.api.mongoDB.persistence.repository;

import br.com.api.mongoDB.persistence.entities.Student;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends MongoRepository<Student, String> {
}
