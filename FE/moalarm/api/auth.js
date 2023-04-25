function signin(email, password) {
    return post(`/auth/signin`, {
        "email":email,
        "password":password
    });
}


onload = () => {
    const $signinForm = document.getElementById("signin-form");
    const $emailInput = document.getElementById("floatingInput");
    const $passwordInput = document.getElementById("floatingPassword");
    console.log("onload");
    $signinForm.onsubmit = async (event)=>{
        event.preventDefault();
        try {
            const response = await signin($emailInput.value, $passwordInput.value);
            window.localStorage.setItem("token", response.token);
            console.log("로그인 성공");
        } catch(error) {
            alert(error);
        }
    };

}