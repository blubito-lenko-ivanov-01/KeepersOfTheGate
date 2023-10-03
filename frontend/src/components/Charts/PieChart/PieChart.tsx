import { Chart as ChartJS, ArcElement, Tooltip, Legend } from "chart.js";
import { Pie } from "react-chartjs-2";
import styles from "./PieChart.module.scss";

ChartJS.register(ArcElement, Tooltip, Legend);

const data = {
  labels: ["Open", "Closed"],
  datasets: [
    {
      data: [12, 19],
      backgroundColor: ["rgba(255, 99, 132, 0.2)", "rgba(54, 162, 235, 0.2)"],
      borderColor: ["rgba(255, 99, 132, 1)", "rgba(54, 162, 235, 1)"],
      borderWidth: 1,
    },
  ],
};

const PieChart = () => {
  return (
    <div className={styles.pieChart}>
      <Pie data={data} />
    </div>
  );
};

export default PieChart;
