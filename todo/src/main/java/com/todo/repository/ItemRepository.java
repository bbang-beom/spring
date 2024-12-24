package com.todo.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.todo.entity.Item;

public interface ItemRepository extends JpaRepository<Item, Long>{
	
	// 특정 날짜 이후 조회
	List<Item> findByDateAfter(LocalDateTime date);
	
	// 문자열 조회
	List<Item> findByContentStartingWith(String content);
	
	// Like 조회
	List<Item> findByContentLike(String content);
	
	// Count 출력
	long countByContent(String content);
	
	// 최상단 조회
	List<Item> findFirstByContent(String content);
}
