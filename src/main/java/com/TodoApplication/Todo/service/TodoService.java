package com.TodoApplication.Todo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import com.TodoApplication.Todo.model.Todo;
import com.TodoApplication.Todo.repository.TodoRepo;

@Service
public class TodoService
{
	@Autowired
	TodoRepo repo;
	
	public List<Todo> getAllTodoItems() {
		
		return repo.findAll();
	}
	
	public Todo getTodoItemById(Long id)
	{
	   return repo.findById(id).orElse(null);
	}
	
	public boolean updateStatus(Long id) {
	    Todo todo = getTodoItemById(id);
	    if (todo == null) {
	        return false; // Item not found
	    }
	    todo.setStatus("completed");
	    return saveorUpdateToDoItem(todo);
	}

	
	public boolean saveorUpdateToDoItem(Todo todo) {
	    if (todo == null) {
	        return false;
	    }
	    
	    // Save the Todo object, which will either update or create a new record
	    Todo updatedObj = repo.save(todo);
	    return true;
	}


	
	public boolean deleteTodoItem(Long id)
	{
		if(repo.existsById(id))
		{
			repo.deleteById(id);
			return true;
		}
		return false;
	}
	
	
	

}
