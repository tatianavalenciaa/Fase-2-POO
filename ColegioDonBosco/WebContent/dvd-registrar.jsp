<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="sv.edu.cdb.model.Dvd"%>

<!DOCTYPE html>
<html>
<head>
<jsp:include page="librerias.jsp" />
</head>
<body>
	<jsp:include page="navbar.jsp"></jsp:include>
	<div class="container">

		<!-- Header -->

		<div class="d-flex flex-row mt-5">
			<a href="DvdController?accion=listar"> 
				<img src="img/regresar.png" style="width: 2.2rem;">
			</a>
			<h3 class="mx-3">REGISTRAR DVD</h3>
		</div>

		<hr>

		<!-- Body -->

		<div class="row mt-5">
			<div class="col-4">
				<form action="DvdController?accion=registrar" method="post">
					<div class="mb-3">
						<label for="codigo" class="form-label">Codigo:</label>
						<input type="text" class="form-control" id="codigo" name="codigo">
					</div>
					<div class="mb-3">
						<label for="titulo" class="form-label">Titulo:</label>
						<input type="text" class="form-control" id="titulo" name="titulo">
					</div>
					<div class="mb-3">
						<label for="director" class="form-label">Director:</label>
						<input type="text" class="form-control" id="director" name="director">
					</div>
					<div class="mb-3">
						<label for="duracion" class="form-label">Duracion:</label>
						<input type="text" class="form-control" id="duracion" name="duracion">
					</div>
					<div class="mb-3">
						<label for="genero" class="form-label">Genero:</label>
						<input type="text" class="form-control" id="genero" name="genero">
					</div>
					<div class="mb-3">
						<label for="unidadesDisp" class="form-label">Unidades Disp:</label>
						<input type="text" class="form-control" id="unidadesDisp" name="unidadesDisp">
					</div>
					<div class="mb-3">
						<label for="ubicacion" class="form-label">Ubicacion:</label>
						<input type="text" class="form-control" id="ubicacion" name="ubicacion">
					</div>	
					<button type="submit" class="btn btn-warning w-100">REGISTRAR</button>
				</form>
			</div>
			<div class="col-8"></div>
		</div>

	</div>
</body>
</html>