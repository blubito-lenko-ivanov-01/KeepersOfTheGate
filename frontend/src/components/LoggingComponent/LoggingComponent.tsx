import { useAuth0 } from "@auth0/auth0-react";
import { Button } from "primereact/button";

const LoggingComponent = () => {
  const { loginWithRedirect } = useAuth0();

  return (
    <div>
      <Button label="Log in" onClick={() => loginWithRedirect()} />
    </div>
  );
};

export default LoggingComponent;
