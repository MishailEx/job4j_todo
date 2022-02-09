$.ajax({
    type: 'GET',
    crossdomain: true,
    url: 'http://localhost:8080/job4j_todo/add',
    dataType: 'json'
}).done(function (data) {
    for (var item of data) {
        var task = JSON.stringify(item);
        var category = '';
        for (i in item.category) {
            category += item.category[i].name + ' '
        }
        if (item.done === true) {
            $('#allItem').append(`<tr><td><input class="checkbox" onclick="fun2(this.value)" type="checkbox" name="update" value='${task}' checked>   ${item.description} <br> Категория: ${category} </td></tr>`);
        } else {
            $('#allItem').append(`<tr><td><input class="checkbox" onclick="fun2(this.value)" type="checkbox" name="update" value='${task}'>   ${item.description} <br> Категория: ${category}</td></tr>`);
        }
    }
}).fail(function () {
    $('#allItem').append(`<h1>Войдите в профиль</h1>`);
});
