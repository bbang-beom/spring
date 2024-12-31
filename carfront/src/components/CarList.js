import { DataGrid } from "@mui/x-data-grid";
import { useEffect, useState } from "react";
import { SEVER_URL } from "./Constants";

function CarList() {
	// Rest api에서 가져온 자동차 정보를 담을 상태 객체 선언
	// 비어있는 배열을 기본값으로 cars상태를 선언
	const [cars, setCars] = useState([])
	// useEffect훅에서 fetch를 실행
	// 두번째 인수로 비어있는 배열을 전달하므로 fetch는 첫번째 렌더링 후에 한번만 실행된다.
	// JSON응답 데이터에 있는 자동차 데이터를 cars상태에 저장 전에 재렌더링 되면서 cars의 서버에서 받아온 데이터가 저장된다.
	useEffect(() => {
		fetch(SEVER_URL+"cars")
		.then(response => response.json())
		.then(data => setCars(data._embedded.cars))
		.catch(err=>console.log(err))
	}, [])
	
	// map함수로 자동차 객체를 표 행으로 변환하고 table요소를 추가
	const columns=[
		{field:"brand",headerName:"Brand",width:200},
		{field:"model",headerName:"Model",width:200},
		{field:"color",headerName:"Color",width:200},
		{field:"year",headerName:"Year",width:150},
		{field:"price",headerName:"Price",width:150},
]
	return(
		<div style={{height: 500, width: "100%"}}>
			<DataGrid
				rows={cars}
				columns={columns}
				getRowId={row=>row._links.self.href}
			/>
		</div>
	)
}

export default CarList