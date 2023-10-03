import styles from "./InfoPanel.module.scss";

const enum deviceType {
  door = "Current door status: ",
  thermometer = "Current office temperature: ",
  lightDiod = "Current light intensity in office: ",
}

export interface InfoPanelProps {
  selectedDevice: string;
  timestamp: string;
  currentValue: string;
  isSelected: boolean;
}

const InfoPanel = (props: InfoPanelProps) => {
  const { timestamp, currentValue, selectedDevice, isSelected } = props;
  return (
    <div className={styles.infoPanel}>
      {isSelected ? (
        <>
          <div className={styles.textInformation}>
            <h3>Device information</h3>
            <div>
              <b>Device name:</b> {selectedDevice}
            </div>
            <div>
              <b>Date:</b> {timestamp}
            </div>
            <div>
              <b>{deviceType["door"]}</b> <span>{currentValue}</span>
            </div>
          </div>
          <div>Graph section</div>
        </>
      ) : (
        <div className={styles.emptyMsg}>Please select a device to view its information!</div>
      )}
    </div>
  );
};

export default InfoPanel;
