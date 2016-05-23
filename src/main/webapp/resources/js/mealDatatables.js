var ajaxUrl = 'ajax/profile/meals/';
var datatableApi;

function updateTable() {
    $.get(ajaxUrl, function (data) {
        updateTableByData(data);
    });
}

$(function () {
    datatableApi = $('#datatable').DataTable(
        {
            "ajax": {
                "url": ajaxUrl,
                "dataSrc": ""
            },
            "paging": false,
            "info": true,
            "columns": [
                {
                    "data": "dateTime",
                    "render": function(date, type, row) {
                        if (type == 'display') {
                            return '<span>' + moment.utc(date).format('YYYY-MM-DD HH:mm') + '</span>';
                        }
                        return date;
                    }
                },
                {
                    "data": "description"
                },
                {
                    "data": "calories"
                },
                {
                    "orderable": false,
                    "defaultContent": "",
                    "render": renderEditBtn
                },
                {
                    "orderable": false,
                    "defaultContent": "",
                    "render": renderDeleteBtn
                }
            ],
            "order": [
                [
                    0,
                    "desc"
                ]
            ],
            "createdRow": function (row, data, dataIndex) {
                if (data.exceed) {
                    $(row).addClass("danger");
                }
            },
            "initComplete": makeEditable
        });

    $('#filter').submit(function () {
        updateTable();
        return false;
    });

});
