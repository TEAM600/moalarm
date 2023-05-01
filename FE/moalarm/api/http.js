const baseURL = "http://localhost:8080/api/v1";

const defaultOption = () => {
    headers = {
        'Content-type': 'application/json; charset=UTF-8',
    }
    const token = localStorage.getItem("token");
    if (token != null) {
        headers.Authorization = "Bearer " + token;
    }
    return headers;
};

async function get(url) {
    return await fetch(baseURL + url, {
        method: "GET",
        headers: defaultOption()
    }).then((response) => {
        if (response.status === 204) return;
        return response.json();
    }).catch(error => alert(error));
}

async function post(url, data) {
    return await fetch(baseURL + url, {
        method: "POST",
        headers: defaultOption(),
        body: JSON.stringify(data)
    }).then(response => {
        if (response.status === 204) return;
        return response.json()
    }).catch(error => alert(error));
}
