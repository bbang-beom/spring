package com.todo.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.todo.dto.TodoDto;

@Controller
@RequestMapping(value="/thymeleaf")
public class thymeleafController {
	@GetMapping(value = "/list")
	public String thymeleafTodo(Model model) {
		List<TodoDto> todoList = new ArrayList<>();
		for(int i = 1; i <= 3; i++) {
			TodoDto todoDto = new TodoDto();
			todoDto.setId(i);
			todoDto.setContent("mock" + i);
			todoDto.setDate(LocalDateTime.now());
			todoDto.setEmotionId(i);
			todoList.add(todoDto);
		}
		model.addAttribute("todoList", todoList);
		return "thymeleaf/thymeleafTodo";
	}
}
