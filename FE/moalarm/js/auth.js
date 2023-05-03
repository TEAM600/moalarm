function signin(email, password) {
    return post(`/auth/signin`, {
        "email": email,
        "password": password
    });
}


onload = () => {
    const $signinForm = document.getElementById("signin-form");
    const $emailInput = document.getElementById("floatingInput");
    const $passwordInput = document.getElementById("floatingPassword");

    $signinForm.onsubmit = (event) => {
        event.preventDefault();
        signin($emailInput.value, $passwordInput.value)
            .then(response => {
                window.localStorage.setItem("token", response.token);
                window.location.replace(`main-page.html`);
            })
            .catch(error => console.log(error));
    };

}