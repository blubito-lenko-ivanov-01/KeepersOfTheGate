// import { useQuery } from "react-query";
// import { getNotifications } from "../../services/NotificationService";
import { Device } from "../../types/Device";
import styles from "./NotificationPanel.module.scss";
import { Message } from "primereact/message";

interface NotificationProps {
  deviceInfo: Device;
}

const NotificationPanel = (props: NotificationProps) => {
  const type = props.deviceInfo.deviceType;

  // const notifications = useQuery({
  //   queryKey: ["notifications", type],
  //   queryFn: () => getNotifications(type),
  // });

  const generateMessages = (): JSX.Element[] => {
    const msgs: JSX.Element[] = [];
    if (type === "door") {
      for (let j = 0; j < 10; j++) {
        msgs.push(
          <Message className={styles.message} severity="info" text={`09.${j} - Door ${j % 2 ? "closed" : "opened"}`} />
        );
      }
    } else if (type === "thermometer") {
      for (let j = 0; j < 10; j++) {
        msgs.push(
          <Message className={styles.message} severity="info" text={`09.${j} - Temp ${j % 2 ? "26.5" : "25.5"}`} />
        );
      }
    }
    return msgs;
  };

  return (
    <>
      <div className={styles.notificationPanel}>
        <h2>NOTIFICATIONS</h2>
        {generateMessages()}
      </div>
    </>
  );
};

export default NotificationPanel;
