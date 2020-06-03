google.charts.load('current', {'packages':['timeline']});
google.charts.setOnLoadCallback(drawChart);

var timelineArray = JSON.parse(decodeHtml(timelineData));
var timelineData = timelineArray.map(function (project) {
    return [project.name, new Date(project.startDate), new Date(project.endDate)]
})

function drawChart() {
    var container = document.getElementById('timeline');
    container.style.height = timelineArray.length * 60 + 'px'
    var chart = new google.visualization.Timeline(container);
    var dataTable = new google.visualization.DataTable();

    dataTable.addColumn({ type: 'string', id: 'Project Name' });
    dataTable.addColumn({ type: 'date', id: 'Start' });
    dataTable.addColumn({ type: 'date', id: 'End' });
    dataTable.addRows(timelineData);

    chart.draw(dataTable);
}

function decodeHtml(html) {
    var txt = document.createElement('textarea');
    txt.innerHTML = html;
    return txt.value;
}