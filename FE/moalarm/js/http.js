const baseURL = "https://k8a407.p.ssafy.io/api/v1";

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

function get(url) {
    return fetch(baseURL + url, {
        method: "GET",
        headers: defaultOption()
    }).then(async response => {
        if (response.status === 204) return;
        if (!response.ok) {
            return Promise.reject(await response.json());
        }
        return await response.json();
    }).catch(error => {
        alert(error.message)
        throw Error(error);
    });
}

function post(url, data) {
    return fetch(baseURL + url, {
        method: "POST",
        headers: defaultOption(),
        body: JSON.stringify(data)
    }).then(async response => {
        if (response.status === 204) return;
        if (!response.ok) {
            return Promise.reject(await response.json());
        }
        return await response.json();
    }).catch(error => {
        alert(error.message)
        throw Error(error);
    });
}
