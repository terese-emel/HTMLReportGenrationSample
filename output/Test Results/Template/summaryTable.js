
function drawChart() {
        data = new google.visualization.DataTable();
        data.addColumn('string', 'Status');
        data.addColumn('number', 'Count');
 var table = document.getElementById("summary");
for (var i=2;i<table.rows.length;i++){

 var arrayTable = {Status: table.rows[i].cells.item(0).innerHTML,Count : table.rows[i].cells.item(2).innerHTML};

        // Create our data table.
        data.addRows([
          [table.rows[i].cells.item(0).innerHTML,table.rows[i].cells.item(2).innerHTML],
        ]);
        }
       // Set chart options
            var options = {'title':'Execution Summary',
                           'height':345,
                           colors: ['#05A80C','#F71004','#053FA8','#F7BF04']};

        // Instantiate and draw our chart, passing in some options.
        chart = new google.visualization.PieChart(document.getElementById('chart_div'));

       // google.visualization.events.addListener(chart, 'select', selectHandler);
       chart.draw(data, options);

      }

      function selectHandler() {
        var selectedItem = chart.getSelection()[0];
        var value = data.getValue(selectedItem.row, 0);
        alert('The user selected ' + value);
      }