export interface Device {
  id: number;
  deviceName: string;
  deviceType: string;
  values: { timestamp: string; value: number }[];
}
