<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="librerias.jsp" />
</head>
<body>
	<div class="container text-center">
		<div class="row">
			<div class="col-4"></div>
			<div class="col-4 mt-5">
				<img src="img/donbosco.png" width="100">
				<br/><br/>
				<h3>COLEGIO DON BOSCO</h3>
				
				<form class="mt-5" action="LoginController" method="post">
					<div class="mb-3 text-start">
						<label for="usuario" class="form-label">Usuario:</label>
						<input type="text" class="form-control" id="usuario" name="usuario">
					</div>
					<div class="mb-3 text-start">
						<label for="password" class="form-label">Password:</label>
						<input type="password" class="form-control" id="password" name="password">
					</div>
					<button type="submit" class="btn btn-warning w-100">ENTRAR</button>
				</form>
			</div>
			<div class="col-4"></div>
		</div>
	</div>
</body>
</html>