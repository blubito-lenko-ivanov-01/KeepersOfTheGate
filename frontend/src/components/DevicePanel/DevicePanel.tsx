import { Card } from "primereact/card";
import styles from "./DevicePanel.module.scss";

export interface DevicePanelProps {
  name: string;
}

const DevicePanel = (props: DevicePanelProps) => {
  const { name } = props;
  return (
    <>
      <Card className={styles.card}>
        <h1>{name}</h1>
      </Card>
    </>
  );
};

export default DevicePanel;
