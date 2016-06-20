function loadProducts(){
var xhttp = new XMLHttpRequest();
xhttp.onreadystatechange=function() {
    if (xhttp.readyState == 4 && xhttp.status == 200) {
      document.getElementById("main-section").innerHTML = xhttp.responseText;
    }
  };
  xhttp.open("GET", "getProduct", true);
  xhttp.send();
}