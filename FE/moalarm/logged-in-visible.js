const token = localStorage.getItem("token");
if (token) {
    const visibleElements = document.querySelectorAll(".logged-in-visible");
    visibleElements.forEach(visibleElement => {
        visibleElement.style.display = "block";
    })
}