import axios from "axios";

const customAxios = axios.create();
customAxios.interceptors.request.use(
  (config) => {
    const token = sessionStorage.getItem("accessToken");
    if (!token) return config;
    config.headers["Authorization"] = `Bearer ${token}`;
    return config;
  },
  (error) => {
    console.log(error);
  }
);
export default customAxios;
