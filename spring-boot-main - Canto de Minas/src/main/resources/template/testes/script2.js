function login() {
    var login = document.getElementById("login").value;
    var senha = document.getElementById("senha").value;

    var apiUrl = "http://localhost:8080/login";

    var xhr = new XMLHttpRequest();
    xhr.open("POST", apiUrl, true);
    xhr.setRequestHeader("Content-Type", "application/json");

    xhr.onreadystatechange = function() {
        if (xhr.readyState === XMLHttpRequest.DONE) {
            if (xhr.status === 200) {
                var response = JSON.parse(xhr.responseText);
                localStorage.setItem("token", response.token);
                console.log("Token armazenado com sucesso!");

                // Redirecionar para a próxima página após o login
                window.location.href = "pagina-seguinte.html";
            } else {
                console.log("Erro ao fazer login");
                alert("Erro ao fazer login");
            }
        }
    };

    var data = JSON.stringify({
        login: login,
        senha: senha
    });

    xhr.send(data);
}