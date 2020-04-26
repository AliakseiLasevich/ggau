"use strict";

let facultyForm = document.getElementById("facultyForm"),
    statusMessage = document.createElement("div"),
    request = new XMLHttpRequest();

let message = {
    loading: "Loading...",
    success: "Success!",
    failure: "Oops. Something goes wrong."
};

facultyForm.addEventListener("submit", function (event) {
    event.preventDefault(); //Не перегружает страницу при сабмите
    facultyForm.appendChild(statusMessage); //Добавляет под формой результат операции
    // let request = new XMLHttpRequest(); //объект request-а
    request.open("POST", "../rest/faculties", true);
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
fetch('../rest/faculties', {
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
    let col = [];
    for (let i = 0; i < json.length; i++) {
        for (let key in json[i]) {
            if (col.indexOf(key) === -1) {
                col.push(key);
            }
        }

    }

    //additional columns:
    // col.push("status");
    // col.push("action");
    // col.push("action");
    // col.push("action");
    col.push("action");


    // CREATE DYNAMIC TABLE.
    let table = document.createElement("table");

    // CREATE HTML TABLE HEADER ROW USING THE EXTRACTED HEADERS ABOVE.

    let tr = table.insertRow(-1);                   // TABLE ROW.

    for (let i = 0; i < col.length; i++) {
        let th = document.createElement("th");      // TABLE HEADER.
        th.innerHTML = col[i];
        tr.appendChild(th);
    }

    // ADD JSON DATA TO THE TABLE AS ROWS.
    for (let i = 0; i < json.length; i++) {

        tr = table.insertRow(-1);

        for (let j = 0; j < col.length-1; j++) {

            let tabCell = tr.insertCell(-1);
            console.log(json[i][col[j]]);
            tabCell.innerHTML = json[i][col[j]];
        }

        //Колонка редактирования
        let tabCell2 = tr.insertCell(-1);
        tabCell2.innerHTML="<a href=\"faculties_edit.html?id="+json[i][col[0]]+"\">Редактировать</a>";

    }

    // FINALLY ADD THE NEWLY CREATED TABLE WITH JSON DATA TO A CONTAINER.
    let divContainer = document.getElementById("showData");
    divContainer.innerHTML = "";
    divContainer.appendChild(table);
}
//////////////////////////////////////////////



const queryString = window.location.search;
const urlParams = new URLSearchParams(queryString);
const id = urlParams.get('id');
console.log(id);

let editFaculty = document.getElementById("editFaculty");
editFaculty.setAttribute("value",id);