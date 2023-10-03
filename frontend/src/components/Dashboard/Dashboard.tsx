import { useQuery } from "react-query";
import DevicePanel from "../DevicePanel/DevicePanel";
import InfoPanel from "../InfoPanel/InfoPanel";
import NotificationPanel from "../NotificationPanel/NotificationPanel";
import styles from "./Dashboard.module.scss";
import { getAllDevices } from "../../services/DeviceService";
import { useEffect, useState } from "react";
import { Device } from "../../types/Device";

// const device1: Device = {
//   id: 1,
//   deviceName: "Door",
//   deviceType: "door",
//   values: [
//     { timestamp: "9.00", value: 1 },
//     { timestamp: "9.10", value: 0 },
//     { timestamp: "9.20", value: 1 },
//     { timestamp: "9.30", value: 0 },
//   ],
// };

// const device2: Device = {
//   id: 2,
//   deviceName: "Thermometer",
//   deviceType: "thermometer",
//   values: [
//     { timestamp: "9.00", value: 25 },
//     { timestamp: "9.10", value: 25.4 },
//     { timestamp: "9.20", value: 26 },
//     { timestamp: "9.30", value: 24.9 },
//   ],
// };
// const devices: Device[] = [device1, device2];

const Dashboard = () => {
  const [selectedDevice, setSelectedDevice] = useState(-1);
  const [deviceArr, setDeviceArr] = useState<Device[]>([]);

  const deviceQuery = useQuery({
    queryKey: ["devices"],
    queryFn: getAllDevices,
  });

  useEffect(() => {
    if (deviceQuery.status === "success") {
      setDeviceArr(deviceQuery.data.devices);
    }
  }, [deviceQuery]);

  const getSelectedInfo = (): Device => {
    if (selectedDevice === -1) return { id: -1, deviceName: "", deviceType: "", values: [] };
    return deviceArr[deviceArr.findIndex((dev) => selectedDevice === dev.id)];
  };

  console.log(selectedDevice);
  return (
    <div className={styles.dashboard}>
      <div className={styles.leftSection}>
        <div className={styles.devicesSection}>
          {deviceArr.map((device) => (
            <DevicePanel name={device.deviceName} id={device.id} handleClick={setSelectedDevice} />
          ))}
        </div>
        <InfoPanel deviceInfo={getSelectedInfo()} />
      </div>
      <div className={styles.rightSection}>
        <NotificationPanel deviceInfo={getSelectedInfo()} />
      </div>
    </div>
  );
};

export default Dashboard;
