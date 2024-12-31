package com.todo2;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
@CrossOrigin
public class TodoCont {
	
	@Autowired
	private TodoRepo todoRepo;
	
	@Autowired
	TodoSer todoSer;
	
	@GetMapping("/create")
	public String putToto(Todo2 todo) {
		todo.setCreatedDate(LocalDateTime.now());
		todoSer.putTodo(todo);
		return "create";
	}
	
	@PostMapping
	public String putTotoPost(Todo2 todo) {
		todo.setCreatedDate(LocalDateTime.now());
		System.out.println(todo);
		return "create";
	}
	
	@GetMapping("/todos")
	public String getList(Todo2 todo, Model model) {
		List<Todo2> todoList = new ArrayList<>();
		List<Todo2> lists = todoSer.getList();
		for(Todo2 list:lists) {
			Todo2 item = new Todo2();
			item.setId(list.getId());
			item.setContent(list.getContent());
			item.setDone(false);
			item.setCreatedDate(list.getCreatedDate());
			todoList.add(item);
		}
		model.addAttribute("todoList", todoList);
		return "index";
	}
	
	@GetMapping("/updateList")
	public String update(Todo2 todo) {
		List<Todo2> todoList = todoSer.getList();
		for(Todo2 list:todoList) {
			if(list.getId()==todo.getId()) {
				list.setContent(todo.getContent());
				todoSer.putTodo(list);
			}
		}
		return "updateList";
	}
	
	@GetMapping("/delete")
	public String delete(Todo2 todo) {
		List<Todo2> todoList = todoSer.getList();
		for(Todo2 list:todoList) {
			if(list.getId()==todo.getId()) {
				todoSer.deleteTodo(list);
			}
		}
		return "delete";
	}
	
	@RequestMapping("/todos")
	public Iterable<Todo2> getCars() {
		return todoRepo.findAll();
	}
}
