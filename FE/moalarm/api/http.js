const baseURL = "http://localhost:8080";

const defaultOption = {headers: {
    'Content-type': 'application/json; charset=UTF-8',
  }
};
async function get(url) {
    return await fetch(baseURL + url, defaultOption)
            .then((response) => response.json());
}

async function post(url, data) {
    return await fetch(baseURL + url, {
        method: "POST",
        headers: {
            'Content-type': 'application/json; charset=UTF-8',
          },
        body: JSON.stringify(data)
    }).then(response => {
        if (response.status === 204) return;
        return response.json()
    });
}
