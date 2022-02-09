$.ajax({
    type: 'GET',
    crossdomain: true,
    url: 'http://localhost:8080/job4j_todo/category.do',
    dataType: 'json'
}).done(function (data) {
    for (var item of data) {
        var category = JSON.stringify(item);
        $('#cIds').append(`<option value='${category}'>${item.name}</option>`)
    }
});
