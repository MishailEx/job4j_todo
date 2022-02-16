$.ajax({
    type: 'GET',
    crossdomain: true,
    url: 'http://localhost:8080/job4j_todo/add.do',
    dataType: 'json'
}).done(function (data) {
    $('#h1').append('<br><p style="font-family: Arial, Helvetica, Verdana, sans-serif;' +
        ' font-size: 150%; font-weight: lighter;">Список дел: </p>');
    for (var item of data) {
        var task = JSON.stringify(item);
        var category = '| ';
        for (i in item.category) {
            category += item.category[i].name + ' | '
        }
        if (item.done === true) {
            $('#allItem').append(`<tr><td><input class="checkbox"
                onclick="fun2(this.value)" type="checkbox" name="update" value='${task}' checked> 
                ${item.description} <br> Категория: ${category} <br><br></td></tr>`);
        } else {
            $('#allItem').append(`<tr><td><input class="checkbox" onclick="fun2(this.value)"
                type="checkbox" name="update" value='${task}'> 
                ${item.description} <br> Категория: ${category}<br><br></td></tr>`);
        }
    }
}).fail(function () {
    $('#allItem').append(`<div style="display: flex; justify-content: center; 
        align-items: center;"><h1 style="font-family: Arial, Helvetica, Verdana, sans-serif; font-size: 150%;
        font-weight: lighter; color: crimson" ">Войдите в профиль</h1></div>`);
});
