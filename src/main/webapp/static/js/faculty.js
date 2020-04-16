let facultyForm = document.getElementById("facultyForm");

facultyForm.onsubmit = function (event) {
    // stop the regular form submission
    event.preventDefault();

    // collect the form data while iterating over the inputs
    let data = {};
    for (let i = 0, ii = facultyForm.length; i < ii; ++i) {
        let input = facultyForm[i];
        if (input.name) {
            data[input.name] = input.value;
        }
    }

    let xhr = new XMLHttpRequest();
    xhr.open("POST", "../faculties", true);
    xhr.setRequestHeader('Content-Type', 'application/json; charset=UTF-8');

    // send the collected data as JSON
    xhr.send(JSON.stringify(data));

    xhr.onloadend = function () {
        alert("end");
        // done
    };
};

