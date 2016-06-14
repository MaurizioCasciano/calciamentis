<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<nav class="menu">
	<ul>
		<li class="left"><a class="fa fa-home" href="index.jsp">&nbsp;Home</a>
		</li>
		<li class="left"><a class="fa fa-shopping-cart"
			href="carrello.jsp">&nbsp;Carrello</a></li>

		<li class="left"><span id="totale" class="fa fa-money">&nbsp;&euro;${shoppingCart == null ? 0.0 : shoppingCart.totale}</span></li>

		<li id="search" class="left">
			<div id="form-wrapper">
				<button onclick="specialSearch()" class="go-button fa fa-search"></button>
				<span class="nav-list"> <select id="dropdown">
						<option value="f0">Tutti i prezzi</option>
						<option value="f1">50&euro; - 100&euro;</option>
						<option value="f2">100&euro; - 200&euro;</option>
						<option value="f3">200&euro; - 300&euro;</option>
						<option value="f4">300&euro; - 500&euro;</option>
				</select>
				</span>
				<div class="in-wrap">
					<input type="text" name="query" id="search-box">
				</div>
			</div>
		</li>

		<c:if test="${sessionScope.loggedUser == null}">
			<li id="signup" class="right"><a class="fa fa-user-plus"
				href="registration.jsp">&nbsp;Signup</a></li>
			<li id="login" class="right"><span class="fa fa-sign-in">&nbsp;Login</span>
				<form id="login_form" action="login" method="post">
					<input id="login_username" class="login_field" name="username"
						type="text" placeholder="username" /> <br /> <input
						id="login_password" class="login_field" name="password"
						type="password" placeholder="password" /> <br />
					<div id="submit-div">
						<input type="submit" value="login" />
					</div>
				</form></li>
		</c:if>

		<c:if test="${sessionScope.loggedUser != null}">
			<li id="welcome" class="right"><span class="fa fa-user">&nbsp;${sessionScope.loggedUser.name}</span>
				<div id="profile">
					<ul>
						<li><a href="profile.jsp">Profilo</a></li>
						<li><a href="AllPurchase.jsp">Aquisti</a></li>
						<li><a href="#profilo">Impostazioni</a></li>
						<li><a href="#profilo">Altro 1</a></li>
						<li><a href="#profilo">Altro 2</a></li>
					</ul>

					<form id="logout" action="logout" method="post">
						<label for="out-btn">Exit&nbsp;</label>
						<button id="out-btn" class="fa fa-sign-out" form="logout"
							type="submit" form="nameform" value="Submit"></button>
					</form>
				</div></li>
		</c:if>

		<c:if test="${requestScope.error != null}">
			<script>
				$(document).ready(function() {
					showWarning("${requestScope.error}");
				});
			</script>
		</c:if>
	</ul>
</nav>

<div class="alert success"></div>
<div class="alert info"></div>
<div class="alert warning"></div>