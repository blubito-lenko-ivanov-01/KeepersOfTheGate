import "./App.css";
import { useAuth0 } from "@auth0/auth0-react";
import LoggingComponent from "./components/LoggingComponent/LoggingComponent";
import Dashboard from "./components/Dashboard/Dashboard";

function App() {
  const { isAuthenticated } = useAuth0();

  return <>{!isAuthenticated ? <LoggingComponent /> : <Dashboard />}</>;
}

export default App;
