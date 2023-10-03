import styles from "./NotificationPanel.module.scss";
import { ScrollPanel } from "primereact/scrollpanel";
import { Message } from 'primereact/message';
        
const NotificationPanel = () => {
  return (
    <>
      <ScrollPanel className={styles.notificationPanel}>
        <Message className={styles.message} severity="info" text="Info Message" />
      </ScrollPanel>
    </>
  );
};

export default NotificationPanel;
