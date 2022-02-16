$.ajax({
    type: 'GET',
    crossdomain: true,
    url: 'http://localhost:8080/job4j_todo/auth.do',
    dataType: 'json'
}).done(function (data) {
    $('#header ul:last').append(`<li style="list-style-type: none;  "><a class="nav-link">${data.name}</a></li>`);
    $('#header ul:last').append(`<li style="list-style-type: none; ">
        <a class="nav-link" href="logout.do"><button class="btn btn-primary">Выйти</button></a></li>`);
}).fail(function () {
    $('#header ul:last').append(`<li style="list-style-type: none;">
        <a class="nav-link" href="login.html"><button class="btn btn-primary">Войти</button></a></li>`);
});