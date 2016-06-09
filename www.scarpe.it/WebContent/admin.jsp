<%@ page import="administration.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Amministrazione</title>
<meta name="keywords"
	content="Scarpe, Calcio, Campo, Erba, Partita, Mercurial, Nike, Carrello" />
<meta name="description" content="Carrello acquisti scarpe da calcio" />
<meta name="author" content="Maurizio Casciano" />
<!-- <link rel="stylesheet" href="css/main.css" /> -->
<!--  <link rel="stylesheet" href="css/login.css" /> -->
<link rel="stylesheet" href="css/admin.css">
<link rel="stylesheet"
	href="http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.4.0/css/font-awesome.min.css" />
<!-- <link rel="stylesheet" href="css/search.css" /> -->
<!--[if lt IE 9]>
  <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script>
  <![endif]-->
</head>
<body>
<header>
<h1>Gestione</h1>
</header>
<div id="content">
<div id="formAccessoAdmin">
<form action="AdminAccess" method="post">
<fieldset>
<legend>Inserimento Credenziali</legend>
<label>Nome Utente</label> <br />
<input type="text" name="adminUsername" placeholder="nome utente" 
	value="<%= request.getAttribute("loggedAdmin")!= null ? (String) ((Admin)request.getAttribute("loggedAdmin")).getUsername() : "" %>"> <br />

<label>Password</label> <br />
<input type="password" name="adminPassword" placeholder="password" 
	value="<%= request.getAttribute("loggedAdmin")!= null ? (String) ((Admin)request.getAttribute("loggedAdmin")).getPassword() : ""%>"> <br/>
<input type="submit" name="submit" value="Accedi">
</fieldset>
</form>
<a href="localhost:8080/www.scarpe.it">scarpe.it</a>
</div>
</div>
<footer>
		<p>Copyright &copy; Maurizio Casciano - Gaetano Antonucci</p>
</footer>
</body>
</html>