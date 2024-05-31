package com.wiley.todoapp.service;

import com.wiley.todoapp.model.ToDo;
import com.wiley.todoapp.repository.ToDoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ToDoService {

    @Autowired
    ToDoRepository toDoRepository;

    public List<ToDo> getAllToDoItems() {
        List<ToDo> todoList = new ArrayList<>();
        toDoRepository.findAll().forEach(todoList::add);
        return todoList;
    }

    public ToDo getToDoItemsById(Long id) {
        Optional<ToDo> optionalToDo = toDoRepository.findById(id);
        return optionalToDo.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "To-Do item not found"));
    }


    public ToDo saveOrUpdateToDoItem(ToDo toDo) {
        return toDoRepository.save(toDo);
    }
    public void deleteToDoItem(Long id){
        toDoRepository.deleteById(id);
    }
    public ToDo updateToDoItemById(Long id, ToDo newToDoDetails) {
        return toDoRepository.findById(id).map(existingToDo -> {
            if(newToDoDetails.getTitle()!=null)
            existingToDo.setTitle(newToDoDetails.getTitle());
            existingToDo.setDate(newToDoDetails.getDate());
            existingToDo.setStatus(newToDoDetails.getStatus());
            return toDoRepository.save(existingToDo);
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "To-Do item not found"));
    }

}
