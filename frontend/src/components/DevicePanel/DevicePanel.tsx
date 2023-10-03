import { Card } from "primereact/card";
import styles from "./DevicePanel.module.scss";

export interface DevicePanelProps {
  id: number;
  name: string;
  handleClick: (id: number) => void;
}

const DevicePanel = (props: DevicePanelProps) => {
  const { name, id, handleClick } = props;
  return (
    <>
      <Card className={styles.card} onClick={() => handleClick(id)}>
        <h2>{name}</h2>
      </Card>
    </>
  );
};

export default DevicePanel;
