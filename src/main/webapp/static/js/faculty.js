"use strict";

let facultyForm = document.getElementById("facultyForm"),
    statusMessage = document.createElement("div"),
    request = new XMLHttpRequest(),
    allFaculties;

let message = {
    loading: "Loading...",
    success: "Success!",
    failure: "Oops. Something goes wrong."
};


facultyForm.addEventListener("submit", function (event) {

    event.preventDefault(); //Не перегружает страницу при сабмите
    facultyForm.appendChild(statusMessage); //Добавляет под формой результат операции
    // let request = new XMLHttpRequest(); //объект request-а
    request.open("POST", "../faculties", true);
    request.setRequestHeader('Content-Type', 'application/json; charset=UTF-8');
    let formData = new FormData(facultyForm);

    //преобразует формдату в JSON
    let obj = {};
    formData.forEach(function (value, key) {
        obj[key] = value;
    })

    let json = JSON.stringify(obj);
    request.send(json); //отправляет json

    //если сервер тупит, выдаёт сообщение о загрузке
    request.addEventListener('readystatechange', function () {
        if (request.readyState < 4) {
            statusMessage.innerHTML = message.loading;
        } else if (request.readyState === 4 && request.status == 200) {
            statusMessage.innerHTML = message.success;
        } else {
            statusMessage.innerHTML = message.failure;
        }
    })
});

///////////// GET ALL FACULTIES ARRAY ///////////////
fetch('../faculties', {
        method: 'GET',
        headers: {
            Accept: 'application/json',
        },
    },
).then(response => {
    if (response.ok) {
        response.json().then(json => {
           createTableFromJSON(json) ;
        });
    }
});
///////////TABLE FROM JSON ARRAY//////////////
function createTableFromJSON(json) {
    // EXTRACT VALUE FOR HTML HEADER.
    var col = [];
    for (var i = 0; i < json.length; i++) {
        for (var key in json[i]) {
            if (col.indexOf(key) === -1) {
                col.push(key);
            }
        }
    }
    // CREATE DYNAMIC TABLE.
    var table = document.createElement("table");

    // CREATE HTML TABLE HEADER ROW USING THE EXTRACTED HEADERS ABOVE.

    var tr = table.insertRow(-1);                   // TABLE ROW.

    for (var i = 0; i < col.length; i++) {
        var th = document.createElement("th");      // TABLE HEADER.
        th.innerHTML = col[i];
        tr.appendChild(th);
    }

    // ADD JSON DATA TO THE TABLE AS ROWS.
    for (var i = 0; i < json.length; i++) {

        tr = table.insertRow(-1);

        for (var j = 0; j < col.length; j++) {
            var tabCell = tr.insertCell(-1);
            tabCell.innerHTML = json[i][col[j]];
        }
    }

    // FINALLY ADD THE NEWLY CREATED TABLE WITH JSON DATA TO A CONTAINER.
    var divContainer = document.getElementById("showData");
    divContainer.innerHTML = "";
    divContainer.appendChild(table);
}
//////////////////////////////////////////////