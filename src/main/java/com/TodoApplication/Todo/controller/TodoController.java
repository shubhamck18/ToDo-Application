package com.TodoApplication.Todo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.TodoApplication.Todo.model.Todo;
import com.TodoApplication.Todo.service.TodoService;

@Controller
public class TodoController {
	
	@Autowired
	TodoService service;
	
	@GetMapping("/viewTodoItems")
	public String viewTodoList(Model model, @ModelAttribute("message") String message) {
		
		model.addAttribute("list", service.getAllTodoItems());
		model.addAttribute("message",message);
		
		return "viewTodoItems";
	}
	
	@GetMapping("/updateTodoStatus/{id}")
	public String updateTodoStatus(@PathVariable Long id,RedirectAttributes redirectAttributes) {
		
		if(service.updateStatus(id))
		{
			redirectAttributes.addFlashAttribute("message","update success");
			return "redirect:/viewTodoItems";
		}
		
		redirectAttributes.addFlashAttribute("message","update failure");
		return "redirect:/viewTodoItems";
		
	}
	
	@GetMapping("/addTodoItem")
	public String addTodoItem(Model model)
	{
		model.addAttribute("todo",new Todo());
		
		return "addTodoItem";
	}
	
	@PostMapping("/saveTodoItem")
	public String saveTodoItem(Todo todo,RedirectAttributes redirectAttributes)
	{
		if(service.saveorUpdateToDoItem(todo))
		{
			redirectAttributes.addFlashAttribute("message","save success");
			return "redirect:/viewTodoItems";
		}
		redirectAttributes.addFlashAttribute("message","save failure");
		return "redirect:/viewTodoItems";
	}
	
	@GetMapping("/editToDoItem/{id}")
	public String editToDoItem(@PathVariable Long id, Model model)
	{
		Todo  todo = service.getTodoItemById(id);
		
		if(todo == null)
		{
			return "redirect:/viewTodoItems";
		}
		
		 model.addAttribute("todo", todo);
		 return "editToDoItem";
	}
	

	@PostMapping("/editSaveToDoItem")
	public String editSaveToDoItem(@ModelAttribute Todo todo, RedirectAttributes redirectAttributes) 
	{
		if(service.saveorUpdateToDoItem(todo))
		{
			redirectAttributes.addFlashAttribute("message","edit success");
			return "redirect:/viewTodoItems";
		}
		
		redirectAttributes.addFlashAttribute("message","edit failure");
		return "redirect:/editToDoItem/" + todo.getId();
	}
	
	@PostMapping("/deleteToDoItem/{id}")
	public String deleteItemById(@PathVariable Long id,RedirectAttributes redirectAttributes)
	{
		if(service.deleteTodoItem(id))
		{
			redirectAttributes.addFlashAttribute("message", "delete success");
			return "redirect:/viewTodoItems";
		}
		redirectAttributes.addFlashAttribute("message", "delete failure");
		return "redirect:/viewTodoItems";
	}
	
	
	
	
	
	
	
	

}
