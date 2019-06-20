function getFileName() {
        var path = window.location.pathname;
        var ary = path.split("/");
        var file= ary[ary.length - 1];
        var fileName=file.split('.').shift();
    return fileName;
}
document.title = (getFileName());

function alterTable(testReport) {

var Cells = document.getElementsByClassName("result");
var i=0;
for(i=0;i<Cells.length;i++)
	{
		if(Cells[i].innerText=="Pass")
        {
		Cells[i].style.color="Green";
		}else if(Cells[i].innerText=="Fail"){
        Cells[i].style.color="Red";
        }
        else if(Cells[i].innerText=="Skipped"){
        Cells[i].style.color="Yellow";
        }
    }
}

  // Set the modal hidden while loading
function setModal(){
// Get the modal
var modal = document.getElementById("myModal");
modal.style.display = "none";
}

function setImage(clickedID,path,caption){
// Get the button that opens the modal
var btn= document.getElementById("clickedID");
// When the user clicks on the button, open the modal
// Get the modal
var modal = document.getElementById("myModal");
  modal.style.display = "block";
var popupImage = document.getElementById("popup");
  popupImage.src = path;
var heading = document.getElementById("modal-heading");
  heading.innerHTML= caption;
// Get the <span> element that closes the modal
var span = document.getElementsByClassName("close")[0];
// When the user clicks on <span> (x), close the modal
span.onclick = function() {
  modal.style.display = "none";
}

// When the user clicks anywhere outside of the modal, close it
window.onclick = function(event) {
  if (event.target == modal) {
    modal.style.display = "none";
  }
}
}
