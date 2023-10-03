import DevicePanel from "../DevicePanel/DevicePanel";
import InfoPanel from "../InfoPanel/InfoPanel";
import NotificationPanel from "../NotificationPanel/NotificationPanel";
import styles from "./Dashboard.module.scss";

const Dashboard = () => {
  return (
    <div className={styles.dashboard}>
      <div className={styles.leftSection}>
        <div className={styles.devicesSection}>
          <DevicePanel name="Device 1" key="1" />
          <DevicePanel name="Device 2" key="2" />
        </div>
        <InfoPanel selectedDevice="Fourth floor left door" timestamp="Now" currentValue="opened" isSelected={true} />
      </div>
      <div className={styles.rightSection}>
        <NotificationPanel />
      </div>
    </div>
  );
};

export default Dashboard;
