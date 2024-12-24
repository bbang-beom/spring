package com.todo;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.todo.entity.Item;
import com.todo.repository.ItemRepository;

@SpringBootTest
public class ItemRepositoryTest {
	@Autowired
	ItemRepository itemRepository;

//	@Test
//	public void createItemTest() {
//		Item item1 = new Item();
//		Item item2 = new Item();
//		Item item3 = new Item();
//		
//		item1.setDate(LocalDateTime.now());
//		item1.setContent("mock1");
//		item1.setEmotionId(1);
//		
//		item2.setDate(LocalDateTime.now());
//		item2.setContent("mock2");
//		item2.setEmotionId(2);
//		
//		item3.setDate(LocalDateTime.now());
//		item3.setContent("mock3");
//		item3.setEmotionId(3);
//		
//		itemRepository.save(item1);
//		itemRepository.save(item2);
//		itemRepository.save(item3);
//	}
	
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
		long updateId = 2;
		for(Item item:items) {
			if(item.getEmotionId() == updateId) {
				item.setContent("update mock");
				itemRepository.save(item);
			}
		}
	}
	
	// 삭제 테스트
	@Test
	public void deleteItem() {
		List<Item> items = itemRepository.findAll();
		long deleteId = 3;
		for(Item item:items) {
			if(item.getEmotionId() == deleteId) {
				itemRepository.delete(item);
			}
		}
	}
	
	// 12월 20일 이후에 생성된 할일 조회
	@Test
	@DisplayName("Date 테스트")
	public void getDateList() {
		List<Item> items = itemRepository.findByDateAfter(LocalDateTime.of(2024, 12, 20, 0, 0));
		for(Item item:items) {
			System.out.println(item);
		}
	}
	
	// content가 u로 시작하는 할일 조회
	@Test
	@DisplayName("Content 테스트")
	public void getContent() {
		List<Item> items = itemRepository.findByContentStartingWith("u");
		for(Item item:items) {
			System.out.println(item);
		}
	}
	
	// Like 조회
	@Test
	@DisplayName("Like 테스트")
	public void getLikeContent() {
		List<Item> items = itemRepository.findByContentLike("update%");
		for(Item item:items) {
			System.out.println(item);
		}
	}
	
	// Count 조회
	@Test
	@DisplayName("Count 테스트")
	public void getCount() {
		long count = itemRepository.countByContent("update mock");
		System.out.println("count: " + count);
	}
	
	// 최상단 조회
	@Test
	@DisplayName("최상단 조회 테스트")
	public void getTop() {
		List<Item> items = itemRepository.findFirstByContent("mock1");
			System.out.println(items);
	}
}
