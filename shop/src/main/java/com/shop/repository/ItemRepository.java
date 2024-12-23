package com.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shop.entity.Item;

// Repository 설계
// Spring Data JPA에서는 엔티티 매니저를 직접 이용해 코드를 작성하지 않아도된다.
// 그 대신 Data Access Object 의 역할을 하는 Repository 인터페이스를 설계한 후 사용하는 것만으로 충분하다.
// JpaRepository 상속, JpaRepository는 2개의 제네릭 타입을 사용한다.
// 첫번째는 엔티티 타입 클래스, 두번째는 기본키 타입을 넣어준다.

// JpaRepository에서 지원하는 메서드
// 메서드	| 기능
// <S extends T> save (S entity)	| 엔티티 저장 및 수정
// void delete(T entity)			| 엔티티 삭제
// count()							| 엔티티 총 개수 반환
// Iterable<T> findAll()			| 모든 엔티티 조회
public interface ItemRepository extends JpaRepository<Item, Long>{
	
}
