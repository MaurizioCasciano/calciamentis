var loginMenu = document.getElementById("login");
var signupMenu = document.getElementById("signup");
var welcomeMenu = document.getElementById("welcome");
var exitMenu = document.getElementById("exit");
var loginUserName = document.getElementById("login_username");
var loginPassword = document.getElementById("login_password");

window.onload = lookForLogin();

function lookForLogin(){
  //alert("looking for login");

  if(typeof(Storage) !== "undefined") {
    oldLogin = localStorage.getItem("login");

    if(oldLogin !== null){
      hideSignupAndLogin();
      clearLoginAndPassword();
      showExit();
      sayWelcome("Benvenuto " + oldLogin);
    }
  } else {
    //alert("Sorry, your browser does not support web storage...");
  }
}

function login(){
  var username = loginUserName.value;
  var password = loginPassword.value;

  if(typeof(Storage) !== "undefined") {
    var account = localStorage.getItem(username);

    if(account !== null){
      account = JSON.parse(account);

      if(account.password === password){
        localStorage.setItem("login", account.name);
        hideSignupAndLogin();
        clearLoginAndPassword();
        showExit();
        sayWelcome("Benvenuto " + account.name);
      }else{
        alert("Sorry, wrong password!!!");
      }

    }else{
      alert("Sorry, username NOT found!!!");
    }

  } else {
    alert("Sorry, your browser does not support web storage...");
  }
}



function hideSignupAndLogin(){
  signupMenu.style.display = "none";
  loginMenu.style.display = "none";
}

function clearLoginAndPassword(){
  loginUserName.value = "";
  loginPassword.value = "";
}

function sayWelcome(welcomeMessage){
  welcomeMenu.innerHTML = welcomeMessage;
  welcomeMenu.style.color = "white";
}

function showExit(){
  //alert("showing exit");
  exitMenu.innerHTML = "<button style = 'color:white;background-color:blue;' type='button' onclick='exit();'>Exit</button>";
}

function exit(){
  //alert("exit");
  localStorage.removeItem("login");
  location.reload(); //refreshes the page
}
