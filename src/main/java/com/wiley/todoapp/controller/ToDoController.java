package com.wiley.todoapp.controller;

import com.wiley.todoapp.model.ToDo;
import com.wiley.todoapp.service.ToDoService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/v1/todo")
@Validated
public class ToDoController {

    @Autowired
    private ToDoService service;

    @GetMapping
    public List<ToDo> getAllToDoItems(){
        return service.getAllToDoItems();
    }

    @PostMapping
    public ResponseEntity<?> saveToDoItem(@Valid @RequestBody ToDo toDo) {
        service.saveOrUpdateToDoItem(toDo);
        return ResponseEntity.status(HttpStatus.CREATED).body(service.getToDoItemsById(toDo.getId()));
    }

    @GetMapping(value="/search/{id}")
    public ToDo getToDoItemByID(@PathVariable(name="id") Long id){
        return service.getToDoItemsById(id);
    }
    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteToDoItem(@PathVariable(name="id") Long id){
        service.deleteToDoItem(id);
    }

    @PutMapping("/update/{id}")
    public ToDo updateToDoItem(@PathVariable(name="id") Long id,@Valid @RequestBody ToDo toDo){
        return service.updateToDoItemById(id,toDo);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        StringBuilder errors = new StringBuilder();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.append(error.getField()).append(": ").append(error.getDefaultMessage()).append("; ")
        );
        return new ResponseEntity<>(errors.toString(), HttpStatus.BAD_REQUEST);
    }
}
