function FetchAPI() {
	// fetch API 이용
	// fetch API를 이용하면 웹 요청을 쉽게 만들 수 있다.
	// fetch API는 기존의 XMLHttpRequet(AJAX)와 비슷한 개념이지만 프로미스를 지원하므로 더 직관적으로 이용할 수 있다.
	// fetch API는 호출하는 리소스의 경로를 지정하는 필수 인수를 하나 가진 fetch()메서드를 제공한다.
	// 웹 요청의 경우 이 인수는 서비스의 URL이 된다.
	// fetch()메서드는 응답을 포함하는 프로미스 객체를 반환한다.
	// 응답은 문자열 타입으로 오기 때문에 json()메서드로 json객체 형태로 읽어들인다.
	fetch('https://jsonplaceholder.typicode.com/todos/1').then(response=>response.json()).then(data=>console.log(data)).catch(error=>console.log(error))
	
	// 다른 HTTP방식을 이용하려면 fetch()메서드의 두번째 인수에 정의해야한다.
	// 두번째 인수는 여러 요청 설정을 정의할 수 있는 객체다.
	fetch('https://jsonplaceholder.typicode.com/todos/1', {
		method:"POST"
	}).then(response=>response.json()).then(data=>console.log(data)).catch(error=>console.log(error))
	
	// 두번째 인수 안에 헤더를 추가할 수도 있다.
	fetch('https://jsonplaceholder.typicode.com/todos/1', {
		method:"POST",
		headers:{"Content-Type":"application/json"},
	}).then(response=>response.json()).then(data=>console.log(data)).catch(error=>console.log(error))

	// 요청 본문에 JSON으로 인코딩된 데이터를 넣는 구문을 넣을 수 있다.
	// JSON.stringify - 객체를 JSON으로 바꿔준다.
	let data = 1
	fetch('https://jsonplaceholder.typicode.com/todos/1', {
		method:"POST",
		headers:{"Content-Type":"application/json"},
		body:JSON.stringify(data)
	}).then(response=>response.json()).then(data=>console.log(data)).catch(error=>console.log(error))
	return	<></>
}

// 요청 본문에 JSON으로 인코딩된 데이터를 넣는 구문을 넣을 수 있다.
export default FetchAPI