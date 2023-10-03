import styles from "./NotificationPanel.module.scss";
import { Message } from 'primereact/message';
        
const NotificationPanel = () => {
  return (
    <>
      <div className={styles.notificationPanel}>
        <Message className={styles.message} severity="info" text="Info Message" />
        <Message className={styles.message} severity="info" text="Info Message" />
        <Message className={styles.message} severity="info" text="Info Message" />
        <Message className={styles.message} severity="info" text="Info Message" />
        <Message className={styles.message} severity="info" text="Info Message" />
        <Message className={styles.message} severity="info" text="Info Message" />
        <Message className={styles.message} severity="info" text="Info Message" />
        <Message className={styles.message} severity="info" text="Info Message" />
        <Message className={styles.message} severity="info" text="Info Message" />
        <Message className={styles.message} severity="info" text="Info Message" />
        <Message className={styles.message} severity="info" text="Info Message" />
        <Message className={styles.message} severity="info" text="Info Message" />
        <Message className={styles.message} severity="info" text="Info Message" />
        <Message className={styles.message} severity="info" text="Info Message" />
        <Message className={styles.message} severity="info" text="Info Message" />
        <Message className={styles.message} severity="info" text="Info Message" />
        <Message className={styles.message} severity="info" text="Info Message" />
        <Message className={styles.message} severity="info" text="Info Message" />

      </div>
    </>
  );
};

export default NotificationPanel;
