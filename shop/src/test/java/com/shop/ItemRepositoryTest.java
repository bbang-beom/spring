package com.shop;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.shop.constant.ItemSellStatus;
import com.shop.entity.Item;
import com.shop.repository.ItemRepository;

@SpringBootTest
public class ItemRepositoryTest {
	@Autowired
	ItemRepository itemRepository;
	
	@Test
	public void createItemTest() {
		// 상품 저장 테스트
		Item item = new Item();
		item.setItemNm("테스트 상품");
		item.setPrice(1000);
		item.setItemDetail("테스트 상품 상세 설명");
		item.setItemSellStatus(ItemSellStatus.SELL);
		item.setStockNumber(100);
		item.setRegTime(LocalDateTime.now());
		item.setUpdateTime(LocalDateTime.now());
		// 데이터 베이스에 저장
		itemRepository.save(item);
		System.out.println("저장 완료");
//		assertEquals(itemRepository.count(), 8);
	}
	
	// 전체 조회 테스트
	@Test
	public void getItemsTest() {
		List<Item> items=itemRepository.findAll();
		for(Item item:items) {
			System.out.println(item);
		}
	}
	
//	// 수정 테스트
//	@Test
//	public void putItemTest() {
//		Item item = new Item();
//		item.setId(1L);
//		item.setItemNm("수정된 상품");
//		item.setPrice(1000);
//		item.setItemDetail("테스트 상품 상세 설명");
//		item.setItemSellStatus(ItemSellStatus.SELL);
//		item.setStockNumber(100);
//		item.setRegTime(LocalDateTime.now());
//		item.setUpdateTime(LocalDateTime.now());
//		// 저장
//		itemRepository.save(item);
//	}
	
	// 삭제 테스트
	
	
	// 데이터 하나 가져오기
	@Test
	public void getItemTest() {
		Optional<Item> item = itemRepository.findById(1L);
		if(item.isPresent()) {
			System.out.println(item.get());
		}else {
			System.out.println("해당 아이디가 없음");
		}
	}
	
	// 상품 이름 검색 테스트
	@Test
	public void getByNmTest() {
		List<Item> items =  itemRepository.findByItemNm("테스트");
		for(Item item: items) {
			System.out.println(item);
		}
	}
	
	// 10개 상품 저장
	@Test
	public void crfeateItemList() {
		for(int i = 0; i < 10; i++) {
			Item item = new Item();
			item.setItemNm("테스트 상품" + i);
			item.setPrice(100+i);
			item.setItemDetail("테스트 상품 상세 설명" + i);
			item.setItemSellStatus(ItemSellStatus.SELL);
			item.setStockNumber(100);
			item.setRegTime(LocalDateTime.now());
			item.setUpdateTime(LocalDateTime.now());
			itemRepository.save(item);
		}
	}
	
	@Test
	@DisplayName("상품명, 상품상세설명 or테스트")
	public void findByItemNmOrItemDetailTest() {
		List<Item> itemList = itemRepository.findByItemNmOrItemDetail("테스트 상품1", "테스트 상세 설명5");
		for(Item item:itemList) {
			System.out.println(item);
		}
	}
	
	@Test
	@DisplayName("LessThan 테스트")
	public void findByPriceLessThanTest() {
		List<Item> items = itemRepository.findByPriceLessThan(1005);
		for(Item item:items) {
			System.out.println(item);
		}
	}
	
//	@Test
//	@DisplayName("가격 내림차순 조회 테스트")
//	public void findByOrderPriceDest() {
//		List<Item> items = itemRepository.findByPriceLessThanOrderByPiceDesc(1005);
//		for(Item item:items) {
//			System.out.println(item);
//		}
//	}
}
