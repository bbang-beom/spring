package com.todo.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class TodoDto {
	private long id;
	private LocalDateTime date;
	private String content;
	private int emotionId;
}
