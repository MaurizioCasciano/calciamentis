<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<nav class="menu">
	<ul>
		<li class="left"><a class="fa fa-home" href="index.jsp">&nbsp;Home</a>
		</li>

		<c:if test="${sessionScope.loggedAdmin != null}">
			<li id="welcome" class="right"><span class="fa fa-user">&nbsp;${sessionScope.loggedAdmin.nome}</span>
				<div id="profile">
					<form id="logoutForm" action="LogoutAdmin" method="post">
						<button id="logout-btn" class="fa fa-sign-out" form="logoutForm"
							type="submit" value="Submit">&nbsp;Exit&nbsp;</button>
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