package com.shop.repository;

import java.util.List;

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
// Optional <T> findById(Long id)	| 아이디 조회
public interface ItemRepository extends JpaRepository<Item, Long>{
	// 쿼리 메서드
	// 스프링 데이터 JPA에서 제공하는 핵심 기능 중 하나
	// Repository 인터페이스에 간단한 네이밍 룰을 이용하여 메서드를 작성하면 원하는 쿼리를 실행할 수 있다.
	// 네이밍 룰 중 find를 사용
	// find + (엔티티 이름) + By + 변수이름
	// 상품의 이름을 이용하여 데이터를 조회하는 메서드 추가
	List<Item> findByItemNm(String itemNm);
	
	// OR조건 처리
	// 상품명과 상품 상세 설명을 Or조건을 이용하여 조회
	List<Item> findByItemNmOrItemDetail(String itemNm, String itemDetail);
	
	
	//LessThan 조건 처리
	//파라미터로 넘어온 price변수보다 값이 작은 상품 데이터를 조회
	List<Item> findByPriceLessThan(Integer price);
	
	// OrderBy로 정렬 처리
	// 상품의 가격이 높은 순으로 조회
//	List<Item> findByPriceLessThanOrderByPriceDesc(Integer price);
}
