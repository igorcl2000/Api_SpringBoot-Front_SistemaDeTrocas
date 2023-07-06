    function loginF() {
    var login = document.getElementById("login").value;
    var senha = document.getElementById("senha").value;

    var apiUrl = "http://localhost:8080/login";

    var requestOptions = {
        mode: "no-cors",
        dataType: 'json',
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            login: login,
            senha: senha
        })
    };

    fetch(apiUrl, requestOptions)
        .then(response => {
            if (response.ok) {
                return response.json();
            } else {
                throw new Error("Erro ao fazer login");
            }
        })
        .then(data => {
            localStorage.setItem("token", data.token);
            console.log("Token armazenado com sucesso!");

            // Redirecionar para a próxima página após o login
            window.location.href = "pagina-seguinte.html";
        })
        .catch(error => {
            console.log(error);
            alert("Erro ao fazer login");
        });
}