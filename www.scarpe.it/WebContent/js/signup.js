var target = document.getElementById("payMethodExtra");

//anagrafica
var a_name = document.getElementById("name");
var surname = document.getElementById("surname");
var birthday = document.getElementById("birthday");
var cf = document.getElementById("cf");

//access data
var email = document.getElementById("email");
var username = document.getElementById("username");
var password = document.getElementById("password");

//home-address
var homeStreet = document.getElementById("home-street");
var homeStreetNumber = document.getElementById("home-street-number");
var homeCity = document.getElementById("home-city");
var homeProvince = document.getElementById("home-province");
var homeCap = document.getElementById("home-cap");

//shipping-address
var shippingStreet = document.getElementById("shipping-street");
var shippingStreetNumber = document.getElementById("shipping-street-number");
var shippingCity = document.getElementById("shipping-city");
var shippingProvince = document.getElementById("shipping-province");
var shippingCap = document.getElementById("shipping-cap");

//pay-method
var pay_method;

function modeBonificoBancario(){
  target.innerHTML = '<p class="contact"><label for="iban">IBAN</label></p><input form = "signup-form" type = "text" name = "iban" id = "iban" placeholder="iban" required></input>';
}

function modeCartaDiCredito(){
  target.innerHTML = '<p class="contact"><label for="card-number">Numero Carta</label></p><input form = "signup-form" type = "text" name = "card-number" id = "card-number" placeholder="Numero Carta" required></input><p class="contact"><label for="expiration-date">Data di Scadenza</label></p><input form = "signup-form" type = "date" name = "expiration-date" id = "expiration-date" required></input>';
}

function getPayMethod(){
  var pay_methods = document.getElementsByName("pay_method");

  for(i = 0; i < pay_methods.length; i++){
    if(pay_methods[i].checked){
      pay_method = pay_methods[i];
      //alert("PAY METHOD: " + pay_method.value);
      break;
    }
  }

  return pay_method.value;
}

function registration(){
  //alert("Ciao " + a_name.value + " " +  surname.value + " " + cf.value);

  var account = {
    name : a_name.value,
    surname : surname.value,
    birthday : birthday.value,
    cf : cf.value,
    email : email.value,
    username : username.value,
    password : password.value,
    homeStreet : homeStreet.value,
    homeStreetNumber : homeStreetNumber.value,
    homeCity : homeCity.value,
    homeProvince : homeProvince.value,
    homeCap : homeCap.value,
    shippingStreet : shippingStreet.value,
    shippingStreetNumber : shippingStreetNumber.value,
    shippingCity : shippingCity.value,
    shippingProvince : shippingProvince.value,
    shippingCap : shippingCap.value,
    pay_method : getPayMethod()
  };

  if(account.pay_method == "Bonifico Bancario"){
    account.iban = document.getElementById("iban").value;
    //alert("IBAN: " + account.iban);
  }else if(account.pay_method == "Carta di Credito"){
    account.card_number = document.getElementById("card-number").value;
    account.expiration_date = document.getElementById("expiration-date").value;
    //alert("card number: " + account.card_number + " " + "expiration_date: " + account.expiration_date);
  }

  //alert("Username: " + username.value);
  //alert("Stringify: " + JSON.stringify(account));

  if(typeof(Storage) !== "undefined") {
    localStorage.setItem(username.value, JSON.stringify(account));
    sessionStorage.setItem("last_username", username.value);

    //alert(sessionStorage.getItem("last_username"));

  } else {
    alert("Sorry, your browser does not support web storage...");
  }

  //GLOBAL VARIABLE
  //summaryWindow = window.open("summary.html", "_self");
}
