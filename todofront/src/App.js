import { Typography } from "@mui/material";
import AppBar from "@mui/material/AppBar";
import ToolBar from "@mui/material/Toolbar";
import './App.css';
import TodoList from "./components/TodoList";

function App() {
  return (
    <div className="App">
      <AppBar position="static">
        <ToolBar>
          <Typography variant="h6">
            Todo
          </Typography>
        </ToolBar>
      </AppBar>
      <TodoList/>
    </div>
  );
}

export default App;
