function signup(email, password, confirmPassword) {
    if (password !== confirmPassword) {
        alert("check your password input");
        return;
    }

    return post(`/member`,{
        "email": email,
        "password": password,
        "confirmPassword": confirmPassword
    });
}

onload = () => {
    const $signupForm = document.getElementById("signup-form");
    const $emailInput = document.getElementById("floatingInput");
    const $passwordInput = document.getElementById("floatingPassword");
    const $confirmPasswordInput = document.getElementById("floatingPasswordAgain");

    $signupForm.onsubmit = async (event) => {
        event.preventDefault();
        try {
            signup($emailInput.value, $passwordInput.value, $confirmPasswordInput.value);
            window.location.href = "signin.html";
        } catch(error) {
            alert(error);
        }
    }
}