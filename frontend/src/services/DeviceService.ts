import { generateDeviceInfo } from "../helpers/helpers";
import { Device } from "../types/Device";
import customAxios from "./axiosInstance";

const url = "http://localhost:1914/api/devices";

export const getAllDevices = (): Promise<{ devices: Device[] }> => {
  return customAxios
    .get(url)
    .then((response) => ({ devices: generateDeviceInfo(response.data) }))
    .catch((err) => {
      console.log(err);
      return { devices: [] };
    });
};
