package com.todo;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.todo.entity.Item;
import com.todo.repository.ItemRepository;

@SpringBootTest
public class ItemRepositoryTest {
	@Autowired
	ItemRepository itemRepository;

	@Test
	public void createItemTest() {
		Item item1 = new Item();
		Item item2 = new Item();
		Item item3 = new Item();
		
		item1.setId("mock1");
		item1.setDate(LocalDateTime.now());
		item1.setContent("mock1");
		item1.setEmotionId(1);
		
		item2.setId("mock2");
		item2.setDate(LocalDateTime.now());
		item2.setContent("mock2");
		item2.setEmotionId(2);
		
		item3.setId("mock3");
		item3.setDate(LocalDateTime.now());
		item3.setContent("mock3");
		item3.setEmotionId(3);
		
		itemRepository.save(item1);
		itemRepository.save(item2);
		itemRepository.save(item3);
	}
	
	@Test
	public void getItemList() {
		List<Item> items = itemRepository.findAll();
		for(Item item:items) {
			System.out.println(item);
		}
	}
	
	// 수정 테스트
	@Test
	public void updateItem() {
		List<Item> items = itemRepository.findAll();
		String updateId = "mock2";
		for(Item item:items) {
			if(item.getId() == updateId) {
				item.setContent("update mock");
				itemRepository.save(item);
			}
		}
	}
	
	// 삭제 테스트
	@Test
	public void deleteItem() {
		List<Item> items = itemRepository.findAll();
		String deleteId = "mock3";
		for(Item item:items) {
			if(item.getId() == deleteId) {
				itemRepository.delete(item);
			}
		}
	}
}
