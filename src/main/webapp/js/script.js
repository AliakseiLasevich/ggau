













let z = f();

function f() {
    fetch('/faculties').then((response) => {
        return response.json();
    })
        .then((data) => {
            // return data;
            console.log(data);
        });
}

// function getData() {
//     let xhr = new XMLHttpRequest();
//     let url = '/faculties';
//     // return JSON.parse(xhr.responseText);
//     xhr.onreadystatechange = function () {
//         if (xhr.readyState === 4 && xhr.status === 200) {
//
//             console.log(jsonData);
//
//             callback(jsonData);
//     }


// getData(console.log);

