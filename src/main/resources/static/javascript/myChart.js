var chartDataStr = decodeHtml(chartData);
var charJsonArray = JSON.parse(chartDataStr);

console.log('DataStr', chartDataStr);
console.log('Array', charJsonArray);

var arrayLength = charJsonArray.length;
var numericData = [];
var labelData = [];

for (var i=0; i<arrayLength; i++) {
    numericData[i] = charJsonArray[i].value;
    labelData[i] = charJsonArray[i].label;
}

// For a pie chart
new Chart(document.getElementById("myPieChart"), {
    type: 'pie',
    // The data for our dataset
    data: {
        labels: labelData,
        datasets: [{
            label: 'My First dataset',
            backgroundColor: ["#3e95cd", "#8e5ea2", "#3cba9f"],
            borderColor: 'rgb(255, 99, 132)',
            data: numericData
        }]
    },

    // Configuration options go here
    options: {
        title: {
            display: true,
            text: 'Project Statuses'
        }
    }
});

function decodeHtml(html) {
    var txt = document.createElement('textarea');
    txt.innerHTML = html;
    return txt.value;
}