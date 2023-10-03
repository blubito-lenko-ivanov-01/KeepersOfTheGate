import "./App.css";
import { useAuth0 } from "@auth0/auth0-react";
import LoggingComponent from "./components/LoggingComponent/LoggingComponent";

function App() {
  const { isAuthenticated } = useAuth0();

  return (
    <>
      {!isAuthenticated ? <LoggingComponent /> : <div>Main Page</div>}
    </>
  );
}

export default App;
