package br.com.api.mongoDB.service;

import br.com.api.mongoDB.model.StudentRequest;
import br.com.api.mongoDB.model.StudentResponse;
import org.springframework.stereotype.Service;

import java.util.List;

//@Service
public interface StudentService {

   StudentResponse create(StudentRequest request);

   List<StudentResponse> getAll();

   StudentResponse getId(String id);

   public void delete(String id);

}
