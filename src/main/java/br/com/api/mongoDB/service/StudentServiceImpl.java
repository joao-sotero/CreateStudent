package br.com.api.mongoDB.service;

import br.com.api.mongoDB.model.StudentRequest;
import br.com.api.mongoDB.model.StudentResponse;
import br.com.api.mongoDB.persistence.entities.Student;
import br.com.api.mongoDB.persistence.repository.StudentRepository;
import br.com.api.mongoDB.service.exception.StudentNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentServiceImpl {

    @Autowired
    private StudentRepository repository;

//    @Override
    public StudentResponse create(StudentRequest request) {
        Student student = new Student();
        student.setName(request.getName());
        student.setCurso(request.getCurso());
        student.setBirthDate(request.getBirthDate());

        repository.save(student);

        return createResponse(student);
    }

//    @Override
    public List<StudentResponse> getAll() {
       List<StudentResponse> responses = new ArrayList<>();

        List<Student> students = repository.findAll();

        if(!students.isEmpty()){
            students.forEach(student -> responses.add(createResponse(student)));
        }
        return responses;
    }

//    @Override
    public StudentResponse getId(String id) throws StudentNotFoundException {
        Student student = verifyIfExists(id);
        return createResponse(student);
    }

    public StudentResponse UpdateById(String id, StudentRequest request) throws StudentNotFoundException {
        StudentResponse response = getId(id);
        response.setName(request.getName());
        response.setCurso(request.getCurso());
        response.setBirthDate(request.getBirthDate());
        Student student = createStudent(response);
        repository.save(student);
        return response;
    }

//    @Override
    public void deleteById(String id) {
        try {
            StudentResponse response = getId(id);
            repository.delete(createStudent(response));
        } catch (StudentNotFoundException e) {
            e.printStackTrace();
        }

    }

    private StudentResponse createResponse(Student student) {
        StudentResponse res = new StudentResponse();
        res.setId(student.getId());
        res.setCurso(student.getCurso());
        res.setName(student.getName());
        res.setBirthDate(student.getBirthDate());
        return res;
    }

    private Student createStudent(StudentResponse response) {
        Student student = new Student();
        student.setId(response.getId());
        student.setCurso(response.getCurso());
        student.setName(response.getName());
        student.setBirthDate(response.getBirthDate());
        return student;
    }

    private Student verifyIfExists(String id) throws StudentNotFoundException {
        return repository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException(id));
    }
}
