 function exportPDF() {
        var sTable = document.getElementsByClassName('testreport');
        var style1 =  " <link rel=\"stylesheet\" type=\"text/css\" href=\"../Template/tableStyle.css\" media=\"print\" >";
        var style2 = "<link rel=\"stylesheet\" type=\"text/css\" href=\"../Template/TestReportStyle.css\">";
    var style3 = "<link rel=\"stylesheet\" type=\"text/css\" href=\"../Template/popupImagestyle.css\">";
        // CREATE A WINDOW OBJECT.
        var win = window.open('', '', 'height=700,width=700');

        win.document.write('<html><head>');
        win.document.write('<title>Test Results Summary</title>');   // <title> FOR PDF HEADER.
        win.document.write(style1); 
win.document.write(style2);	
win.document.write(style3);	
	// ADD STYLE INSIDE THE HEAD TAG.
        win.document.write('</head>');
        win.document.write('<body>');
		win.document.write('<table class=\"testreport\" border: 1px solid #fff;>');
		
		for(var i=0;i<sTable.length;i++){			
		//deleteColumn();
		win.document.write(sTable[i].innerHTML);
		}    
	
		win.document.write('</table>');
        win.document.write('</body></html>');

        win.document.close(); 	// CLOSE THE CURRENT WINDOW.

        win.print();    // PRINT THE CONTENTS.
    }
	
	// delete last table column

function deleteColumn() {   

 var allRows = document.getElementById('testResultSummary').rows;

 for (var i=0; i< allRows.length; i++) {

  if (allRows[i].cells.length > 3) {

   allRows[i].deleteCell(-1); //delete the cell

  } else {

   alert("You can't delete more columns.");

   return;

 }}
}
