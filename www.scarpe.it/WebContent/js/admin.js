function showStuff(selectFunction){
	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange=function() {
	    if (xhttp.readyState == 4 && xhttp.status == 200) {
	     alert("tuto");
	    }
	  };
	  xhttp.open("GET", "management.jsp?funzione="+selectFunction, true);
	  xhttp.send();
}