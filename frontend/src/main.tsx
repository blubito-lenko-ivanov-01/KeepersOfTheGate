import React from "react";
import ReactDOM from "react-dom/client";
import App from "./App.tsx";
import "./index.css";
import "primereact/resources/themes/saga-blue/theme.css";
import "primereact/resources/primereact.min.css";
import { Auth0Provider } from "@auth0/auth0-react";
// import "primeicons/primeicons.css";

ReactDOM.createRoot(document.getElementById("root")!).render(
  <React.StrictMode>
    <Auth0Provider
      domain={`${import.meta.env.VITE_DOMAIN}`}
      clientId={`${import.meta.env.VITE_CLIENTID}`}
      authorizationParams={{
        audience: import.meta.env.VITE_AUDIENCE,
        redirect_uri: window.location.origin,
      }}
    >
      <App />
    </Auth0Provider>
  </React.StrictMode>
);
