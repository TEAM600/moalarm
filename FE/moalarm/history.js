function createTableBody(item) {
    const body = document.createElement("tbody");
    for (let i of item) {
        const tr = document.createElement("tr");
        
        const alarmCnt = document.createElement("td");
        const doneCnt = document.createElement("td");
        const dateTime = document.createElement("td");
        
        alarmCnt.textContent = i.alarmCnt;
        doneCnt.textContent = i.doneCnt;
        dateTime.textContent = i.dateTime;

        tr.append(alarmCnt);
        tr.append(doneCnt);
        tr.append(dateTime);

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

onload = async () => {
    const $table = document.getElementById("history");

    const history = await getHistory();

    $table.append(createTableBody(history));
}