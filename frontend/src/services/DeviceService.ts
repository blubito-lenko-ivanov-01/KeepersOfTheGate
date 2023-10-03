import customAxios from "./axiosInstance";

const url = "http://localhost:1914/api/devices";

export const getAllDevices = () => {
  return customAxios
    .get(url)
    .then((response) => console.log("resp: ", response))
    .catch((err) => console.log(err));
};
