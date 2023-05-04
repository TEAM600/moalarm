function getHistory() {
  const history = get(`/history`)
  console.log(history)
  return history;
}

function getHistoryChart() {
  const history = get(`/history/chart?period=7`)
  console.log(history)
  return history;
}

/* globals Chart:false, feather:false */
(async () => {
  'use strict'

  feather.replace({ 'aria-hidden': 'true' })

  // 히스토리 정보 요청
  const history = await getHistory();
  const historyChart = await getHistoryChart();
  
  // Graphs
  const ctx = document.getElementById('myChart')

  // eslint-disable-next-line no-unused-vars
  const myChart = new Chart(ctx, {
    type: 'line',
    data: {
      labels: historyChart.labels,
      datasets: [{
        data: historyChart.dataset.SMS,
        lineTension: 0,
        backgroundColor: 'transparent',
        borderColor: '#2F2040',
        borderWidth: 4,
        pointBackgroundColor: '#553A75'
      },
      {
        data: historyChart.dataset.MAIL,
        lineTension: 0,
        backgroundColor: 'transparent',
        borderColor: '#7953A8',
        borderWidth: 4,
        pointBackgroundColor: '#553A75'
      },
      {
        data: historyChart.dataset.FCM,
        lineTension: 0,
        backgroundColor: 'transparent',
        borderColor: '#D3C7E2',
        borderWidth: 4,
        pointBackgroundColor: '#553A75'
      }]
    },
    options: {
      scales: {
        yAxes: [{
          ticks: {
            beginAtZero: false
          }
        }]
      },
      legend: {
        display: false
      }
    }
  })

  const table = document.querySelector("#history");

  history.forEach((item) => {
    const row = document.createElement("tr");
    const datetimeCell = document.createElement("td");
    const typeCell = document.createElement("td");
    const receiverCell = document.createElement("td");
    const successCell = document.createElement("td");

    datetimeCell.textContent = item.dateTime;
    typeCell.textContent = item.type;
    receiverCell.textContent = item.receiver;
    successCell.textContent = item.success;

    row.appendChild(datetimeCell);
    row.appendChild(typeCell);
    row.appendChild(receiverCell);
    row.appendChild(successCell);

    table.appendChild(row);
  });
})()
