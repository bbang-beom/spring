package com.example;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

// Lombok 라이브러리
// 반복적인 Getter/Setter, ToString과 같은 자바 코들르 컴파일할 때 자동으로 생성해주는 라이브러리

@Getter
@Setter
@ToString
public class UserDtoLombok {
	private String name;
	private Integer age;                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                             
}
