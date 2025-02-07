import { Typography } from "@mui/material";
import AppBar from "@mui/material/AppBar";
import ToolBar from "@mui/material/Toolbar";
import CarList from "./components/CarList";
import './App.css';

function App() {
  return (
    <div className="App">
      <AppBar position="static">
        <ToolBar>
          <Typography variant="h6">
            Carshop
          </Typography>
        </ToolBar>
      </AppBar>
      <CarList/>
    </div>
  );
}

export default App;
