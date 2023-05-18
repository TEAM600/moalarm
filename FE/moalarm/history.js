function createProgress(max, current) {
    const progressBar = document.createElement("div");
    progressBar.setAttribute("class", "progress");
    const bar = document.createElement("div");
    bar.setAttribute("class", "progress-bar");
    bar.setAttribute("role", "progressbar");
    bar.setAttribute("aria-valuemin", "0");
    bar.setAttribute("aria-valuemax", max);
    bar.setAttribute("aria-valuenow", current);
    bar.setAttribute("style", `width:${current}%`);
    bar.textContent = current;
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

onload = async () => {
    const $table = document.getElementById("history");

    const history = await getHistory();

    $table.append(createTableBody(history));
}