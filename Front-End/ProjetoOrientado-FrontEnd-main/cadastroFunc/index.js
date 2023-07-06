window.addEventListener('load', function () {
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            document.getElementById('barra-de-navegacao').innerHTML = this.responseText;
        }
    };
    xhr.open('GET', '../navbar/navbar.shtml', true);
    xhr.send();
});

window.addEventListener('load', function () {
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            document.getElementById('footer').innerHTML = this.responseText;
        }
    };
    xhr.open('GET', '../footer/footer.shtml', true);
    xhr.send();
});