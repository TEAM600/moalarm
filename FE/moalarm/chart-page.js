function createProgress(max, current) {
    const progressBar = document.createElement("div");
    progressBar.setAttribute("class", "progress");
    const bar = document.createElement("div");
    bar.setAttribute("class", "progress-bar");
    bar.setAttribute("role", "progressbar");
    bar.setAttribute("aria-valuemin", "0");
    bar.setAttribute("aria-valuemax", max);
    bar.setAttribute("aria-valuenow", current);
    const ratio =  Math.round(current / (max === 0 ? 1 : max) * 100);
    bar.setAttribute("style", `width:${ratio}%`);
    bar.textContent = ratio;
    progressBar.append(bar);

    return progressBar;
}

function createTableBody(item) {
    const body = document.createElement("tbody");
    for (let i of item) {
        const tr = document.createElement("tr");
        
        const alarmCnt = document.createElement("td");
        const doneCnt = document.createElement("td");
        const dateTime = document.createElement("td");
        const progressBar = document.createElement("td");

        alarmCnt.textContent = i.alarmCnt;
        doneCnt.textContent = i.doneCnt;
        dateTime.textContent = i.dateTime;
        progressBar.append(createProgress(i.alarmCnt, i.doneCnt));
        
        tr.append(alarmCnt);
        tr.append(doneCnt);
        tr.append(dateTime);
        tr.append(progressBar);

        tr.style.cursor = "pointer";
        
        tr.onclick = () => {
            console.log(i.alarmRequestId);
            localStorage.setItem("alarmRequestId", i.alarmRequestId);
            window.location.href = "history-detail.html";
        }
        body.append(tr);
    }
    
    return body;
}

/* globals Chart:false, feather:false */
(async () => {
    'use strict'
  
    feather.replace({ 'aria-hidden': 'true' })
  
    // 히스토리 정보 요청
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
  
    const $table = document.getElementById("alarm-request");

    const alarmRequest = await getHistory();

    $table.append(createTableBody(alarmRequest));
  })()
  