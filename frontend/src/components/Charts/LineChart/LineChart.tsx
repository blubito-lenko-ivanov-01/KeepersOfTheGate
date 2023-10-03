import { Line } from "react-chartjs-2";
import {
  Chart as ChartJS,
  LineElement,
  CategoryScale,
  LinearScale,
  PointElement,
  Tooltip,
  Legend,
  Title,
} from "chart.js";
import styles from "./LineChart.module.scss";

ChartJS.register(LineElement, CategoryScale, LinearScale, PointElement, Tooltip, Legend, Title);

const mockData = [
  { timestamp: "9:00", value: 25 },
  { timestamp: "9:05", value: 25.8 },
  { timestamp: "9:10", value: 24.3 },
  { timestamp: "9:15", value: 26 },
  { timestamp: "9:20", value: 25.5 },
];

const options = {
  responsive: true,
  plugins: {
    legend: {
      position: "top" as const,
    },
    title: {
      display: false,
      text: "Office temperature",
    },
    maintainAspectRatio: false,
    aspectRatio: 1,
  },
};

const data = {
  labels: mockData.map((row) => row.timestamp),
  datasets: [
    {
      label: "Temperature in Celsius",
      data: mockData.map((row) => row.value),
      borderColor: "rgb(255, 99, 132)",
      backgroundColor: "rgba(255, 99, 132, 0.5)",
    },
  ],
};

const LineChart = () => {
  return (
    <div className={styles.lineChart}>
      <Line data={data} options={options} />
    </div>
  );
};

export default LineChart;
