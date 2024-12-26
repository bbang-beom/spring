package com.todo2;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TodoSer {
	// 자동 의존성 주입
	@Autowired
	private TodoRepo todoRepo;
	
	public void putTodo(Todo2 todo) {
		// 데이터 처리
		todoRepo.save(todo);
	}
	
	public List<Todo2> getList() {
		List<Todo2> todoList = todoRepo.findAll();
		return todoList;
	}
	
	public void deleteTodo(Todo2 todo) {
		todoRepo.delete(todo);
	}
}
