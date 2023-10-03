import { useAuth0 } from "@auth0/auth0-react";
// import LineChart from "../Charts/LineChart/LineChart";
import styles from "./InfoPanel.module.scss";
import PieChart from "../Charts/PieChart/PieChart";
import { Device } from "../../types/Device";
import LineChart from "../Charts/LineChart/LineChart";

const enum deviceType {
  door = "Current door status: ",
  thermometer = "Current office temperature: ",
  lightDiod = "Current light intensity in office: ",
}

export interface InfoPanelProps {
  deviceInfo: Device;
}

const InfoPanel = (props: InfoPanelProps) => {
  const { deviceInfo } = props;
  const { isAuthenticated } = useAuth0();

  const getChartType = (): JSX.Element => {
    switch (deviceInfo.deviceType) {
      case "thermometer":
        return <LineChart />;
      case "door":
        return (
          <>
            <span>
              <b>Door states in the last 75 minutes</b>
            </span>
            <PieChart />
          </>
        );
      default:
        return <></>;
    }
  };

  const getMessage = (): JSX.Element => {
    switch (deviceInfo.deviceType) {
      case "thermometer":
        return (
          <span>
            <b>{deviceType["thermometer"]}</b>: 26.7
          </span>
        );
      case "door":
        return (
          <span>
            <b>{deviceType["door"]}</b>: Opened
          </span>
        );
      default:
        return <></>;
    }
  };

  return (
    <div className={styles.infoPanel}>
      {deviceInfo.id !== -1 ? (
        <>
          <div className={styles.textInformation}>
            <h3>Device information</h3>
            <div>
              <b>Device name:</b> {deviceInfo.deviceName}
            </div>
            <div>
              <b>Date</b>: {Date()}
            </div>
            <div>{getMessage()}</div>
          </div>
          {isAuthenticated && <div className={styles.chartContainer}>{getChartType()}</div>}
        </>
      ) : (
        <div className={styles.emptyMsg}>Please select a device to view its information!</div>
      )}
    </div>
  );
};

export default InfoPanel;
