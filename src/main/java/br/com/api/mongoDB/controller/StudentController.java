package br.com.api.mongoDB.controller;

import br.com.api.mongoDB.model.StudentRequest;
import br.com.api.mongoDB.model.StudentResponse;
import br.com.api.mongoDB.service.StudentServiceImpl;
import br.com.api.mongoDB.service.exception.StudentNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentServiceImpl service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED )
    public StudentResponse create(@RequestBody StudentRequest req){
        return service.create(req);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<StudentResponse> getAll(){
        return service.getAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public StudentResponse getId(@PathVariable String id) throws StudentNotFoundException {
        return service.getId(id);
    }

    @PutMapping("/{id}")
    public StudentResponse updateById(@PathVariable String id,@RequestBody StudentRequest request) throws StudentNotFoundException {
        return service.UpdateById(id, request);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable String id){
        service.deleteById(id);
    }

}
