package com.TodoApplication.Todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.TodoApplication.Todo.model.Todo;

@Repository
public interface TodoRepo extends JpaRepository<Todo, Long>
{

}
