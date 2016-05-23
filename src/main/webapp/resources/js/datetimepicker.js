
$(function () {
    $.datetimepicker.setLocale('uk');
    $('#startDate,#endDate').datetimepicker({
        mask:true,
        format:'Y-m-d',
        timepicker:false
    });
    $('#startTime,#endTime').datetimepicker({
        mask:true,
        format:'H:i',
        datepicker:false
    });

    $('#dateTime').datetimepicker({
        mask:true,
        format:'Y-m-d H:i',
    });

    moment().utc();
});
