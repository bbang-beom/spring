import { DataGrid } from "@mui/x-data-grid";
import { useEffect, useState } from "react";
import { SEVER_URL } from "./Constants";

function TodoList() {
	const [todos, setTodos] = useState([])

	useEffect(() => {
		fetch(SEVER_URL+"todos")
		.then(response => response.json())
		.then(data => setTodos(data._embedded.todos))
		.catch(err => console.log(err))
	}, [])

	const columns = [
		{field: "content", headerName: "Content", width:200},
		{field: "create_date", headerName: "Date", width:200},
		{field: "is_done", headerName: "Done", width:200},
	]
	return(
		<div style={{height: 500, width: "100%"}}>
			<DataGrid
				rows={todos}
				columns={columns}
				getRowId={row=>row.links.self.href}
			/>
		</div>
	)
}

export default TodoList