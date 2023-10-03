import { Device } from "../types/Device";

// eslint-disable-next-line @typescript-eslint/no-explicit-any
export const generateDeviceInfo = (data: any): Device[] => {
  const arr = Object.values(data);
  console.log(arr);
  return arr.map(
    // eslint-disable-next-line @typescript-eslint/no-explicit-any
    (unit: any): Device => ({ id: unit.id, deviceName: unit.name, deviceType: unit.type, values: unit.values })
  );
};
