import styles from "./NotificationPanel.module.scss";
import { ScrollPanel } from "primereact/scrollpanel";

const NotificationPanel = () => {
  return (
    <>
      <ScrollPanel className={styles.notificationPanel}>
        <h1>Notifications</h1>
        <h1>Msg1</h1>
        <h1>Msg2</h1>
      </ScrollPanel>
    </>
  );
};

export default NotificationPanel;
