/* globals Chart:false, feather:false */
(async () => {
  'use strict'

  feather.replace({ 'aria-hidden': 'true' })
  const alarmRequestId = localStorage.getItem("alarmRequestId");

  // 히스토리 정보 요청
  const history = await getHistoryByRequestId(alarmRequestId);
  console.log(alarmRequestId);
  console.log(history);
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
