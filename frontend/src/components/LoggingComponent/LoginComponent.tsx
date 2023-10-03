import { useAuth0 } from "@auth0/auth0-react";
import { Button } from "primereact/button";
import styles from "./LoginComponent.module.scss"

const LoginComponent = () => {
  const { loginWithRedirect } = useAuth0();

  return (
    <div className={styles.loginDiv}>
      <Button className={styles.loginButton} label="Log in" onClick={() => loginWithRedirect()} />
    </div>
  );
};

export default LoginComponent;
