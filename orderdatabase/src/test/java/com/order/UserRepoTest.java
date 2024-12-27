package com.order;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.order.domain.Order_Item;
import com.order.domain.Order_Item_Repo;
import com.order.domain.User;
import com.order.domain.UserRepo;

@SpringBootTest
public class UserRepoTest {
	@Autowired
	private UserRepo userRepository;
	
	@Autowired
	private Order_Item_Repo orderItemRepository;
	
	@Test
	public void insertTest() {
		User user1 = new User("user1");
		User user2 = new User("user2");
		userRepository.saveAll(Arrays.asList(user1,user2));
		
		Order_Item item1 = new Order_Item(user1);
		Order_Item item2 = new Order_Item(user1);
		Order_Item item3 = new Order_Item(user2);
		orderItemRepository.saveAll(Arrays.asList(item1,item2,item3));
	}
}
