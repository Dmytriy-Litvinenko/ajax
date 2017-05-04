/*

    showAllDepartments = function () {
        $.ajax({
            url: '/departments',
            type: 'GET'
        }).then((response) => {
            displayDepartments(response)});
    };

    deleteDepartment = function (event) {
        $.ajax({
            url: '/delete',
            data: {id: event.target.name},
            type: 'POST'
        }).then((response) => {
            displayDepartments(response);
        });
    };

    updateDepartment = function (event) {
        $.ajax({
            url: '/update',
            data: {id: event.target.name},
            type: 'POST',
            dataType: 'json'
        }).then((response) => {
            displayDepartmentDetails(response);
        });
    };

    addDepartment = function (event) {
        let departmentId = event.target.name;
        $.ajax({
            url: '/update',
            data: {
                departmentId: departmentId
            },
            type: 'POST'
        }).then((response) => {
            displayDepartmentDetails(response);
        });
    };

    saveDepartment = function () {
        let depId = $('#id').val();
        let depName = $('#name').val();
        $.ajax({
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            data: JSON.stringify({
                id: depId,
                name: depName
            }),
            url: '/saveDep',
            type: "POST"
        }).then(() => {
            showAllDepartments();
        });
    };


    */