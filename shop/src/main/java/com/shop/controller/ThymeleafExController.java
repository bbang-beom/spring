package com.shop.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shop.dto.ItemDto;

@Controller
// url에 "/thymeleaf"경로로 오는 요청을 thymeleafExController가 처리하도록 한다.
@RequestMapping(value="/thymeleaf")
public class ThymeleafExController {
	
	@GetMapping(value = "/ex01")
	public String thymeleafExample01(Model model) {
		// model객체를 이용해 뷰에 전달한 데이터를 key, value 구조로 넣는다.
		model.addAttribute("data", "타임리프 예제");
		// templates폴더를 기준으로 뷰의 위치와 이름을 반환
		return "thymeleafEx/thymeleafEx01.html";
	}
	
	@GetMapping(value="/ex02")
	public String thymeleafExample02(Model model) {
		model.addAttribute("say", "Hello Thymeleaf");
		ItemDto itemDto = new ItemDto();
		itemDto.setItemNm("수정된 상품1");
		itemDto.setPrice(10000);
		itemDto.setItemDetail("테스트 상품 상세 설명");
		itemDto.setRegTime(LocalDateTime.now());
		model.addAttribute("itemDto", itemDto);
		return "thymeleafEx/thymeleafEx02";
	}
	
	@GetMapping(value="/ex03")
	public String thymeleafExample03(Model model) {
		List<ItemDto> itemDtoList = new ArrayList<>();
		for(int i = 1; i <= 10; i++) {
			ItemDto itemDto = new ItemDto();
			itemDto.setItemNm("테스트 상품" + i);
			itemDto.setItemDetail("테스트 상품 상세 설명" + i);
			itemDto.setPrice(10000 * i);
			itemDto.setRegTime(LocalDateTime.now());
			itemDtoList.add(itemDto);
		}
		model.addAttribute("itemDtoList", itemDtoList);
		return "thymeleafEx/thymeleafEx03";
	}
	
	@GetMapping(value="/ex04")
	public String thymeleafExample04(Model model) {
		List<ItemDto> itemDtoList = new ArrayList<>();
		for(int i = 1; i <= 10; i++) {
			ItemDto itemDto = new ItemDto();
			itemDto.setItemNm("테스트 상품" + i);
			itemDto.setItemDetail("테스트 상품 상세 설명" + i);
			itemDto.setPrice(10000 * i);
			itemDto.setRegTime(LocalDateTime.now());
			itemDtoList.add(itemDto);
		}
		model.addAttribute("itemDtoList", itemDtoList);
		return "thymeleafEx/thymeleafEx04";
	}
}
