$(document).ready(function () {
    $.ajax({
        type: 'GET',
        crossdomain: true,
        url: 'http://localhost:8080/job4j_todo/auth.do',
        dataType: 'json'
    }).done(function (data) {
        $('#header li:last').append(`<li style="display:block; float:left;"><a class="nav-link">${data.name}</a></li>`);
        $('#header li:last').append(`<li style="display:block; float:left;"><a class="nav-link" href="logout.do" >Выйти</a></li>`);
    }).fail(function (err) {
        $('#header li:last').append(`<li style="display:block; float:left;"><a class="nav-link" href="login.html">Войти</a></li>`);
    });
});