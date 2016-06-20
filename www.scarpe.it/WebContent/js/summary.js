var target = document.getElementById("payMethodExtra");
window.onload = loaded();

function loaded(){
  //alert("creating page");

  if(typeof(Storage) !== "undefined") {
    var last_username = sessionStorage.getItem("last_username");

    if(last_username !== null){
      //alert("Last Username: " + last_username);

      //GLOBAL
      last_account = JSON.parse(localStorage.getItem(last_username));

      //alert("stringify_again: " + JSON.stringify(last_account));
      createPage();
    }
    else{
      alert("last_account is NULL");
    }
  } else {
    alert("Sorry, your browser does not support web storage...");
  }
}

function modeBonificoBancario(){
  target.innerHTML = '<p class="contact"><label for="iban">IBAN</label></p><input type = "text" id = "iban" placeholder="iban" readonly required></input>';
}

function modeCartaDiCredito(){
  target.innerHTML = '<p class="contact"><label for="card-number">Numero Carta</label></p><input type = "text" id = "card-number" placeholder="Numero Carta" readonly required></input><p class="contact"><label for="expiration-date">Data di Scadenza</label></p><input type = "date" id = "expiration-date" readonly required></input>';
}

function createPage(){
  document.getElementById("name").value = last_account.name;
  document.getElementById("surname").value = last_account.surname;
  document.getElementById("birthday").value = last_account.birthday;
  document.getElementById("cf").value = last_account.cf;

  document.getElementById("email").value = last_account.email;
  document.getElementById("username").value = last_account.username;
  document.getElementById("password").value = last_account.password;
  document.getElementById("repassword").value = last_account.password;

  document.getElementById("home-street").value = last_account.homeStreet;
  document.getElementById("home-street-number").value = last_account.homeStreetNumber;
  document.getElementById("home-city").value = last_account.homeCity;
  document.getElementById("home-province").value = last_account.homeProvince;
  document.getElementById("home-cap").value = last_account.homeCap;

  document.getElementById("shipping-street").value = last_account.shippingStreet;
  document.getElementById("shipping-street-number").value = last_account.shippingStreetNumber;
  document.getElementById("shipping-city").value = last_account.shippingCity;
  document.getElementById("shipping-province").value = last_account.shippingProvince;
  document.getElementById("shipping-cap").value = last_account.shippingCap;

  if(last_account.pay_method == "Bonifico Bancario"){
    //alert(last_account.pay_method);
    document.getElementById("bonifico").checked = "true";
    modeBonificoBancario();
    document.getElementById("iban").value = last_account.iban;
  }else if(last_account.pay_method == "Carta di Credito"){
    //alert(last_account.pay_method);

    document.getElementById("carta-di-credito").checked = "true";
    modeCartaDiCredito();
    document.getElementById("card-number").value = last_account.card_number;
    document.getElementById("expiration-date").value = last_account.expiration_date;
  }
}

function home(){
  /*replace() removes the URL of the current document from the document history,
  meaning that it is not possible to use the "back" button to navigate back to
  the original document.
  */
  window.location.replace("index.html");
}
