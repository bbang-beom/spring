package com.todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.todo.entity.Item;

public interface ItemRepository extends JpaRepository<Item, String>{

}
