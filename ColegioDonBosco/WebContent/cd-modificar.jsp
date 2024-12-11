<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="sv.edu.cdb.model.Cd"%>

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
			<a href="CdController?accion=listar"> 
				<img src="img/regresar.png" style="width: 2.2rem;">
			</a>
			<h3 class="mx-3">MODIFICAR CD</h3>
		</div>

		<hr>

		<!-- Body -->

		<div class="row mt-5">
			<div class="col-4">
				<form action="CdController?accion=modificar" method="post">
					<div class="mb-3">
						<label for="codigo" class="form-label">Codigo:</label><br>
						<span>${cd.codigo}</span>
						<input type="text" class="form-control" id="codigo" name="codigo" hidden="true" value="${cd.codigo}">
					</div>
					<div class="mb-3">
						<label for="titulo" class="form-label">Titulo:</label>
						<input type="text" class="form-control" id="titulo" name="titulo" value="${cd.titulo}">
					</div>
					<div class="mb-3">
						<label for="artista" class="form-label">Artista:</label>
						<input type="text" class="form-control" id="artista" name="artista" value="${cd.artista}">
					</div>
					<div class="mb-3">
						<label for="genero" class="form-label">Genero:</label>
						<input type="text" class="form-control" id="genero" name="genero" value="${cd.genero}">
					</div>
					<div class="mb-3">
						<label for="duracion" class="form-label">Duracion:</label>
						<input type="text" class="form-control" id="duracion" name="duracion" value="${cd.duracion}">
					</div>
					<div class="mb-3">
						<label for="numCanciones" class="form-label">Num Canciones:</label>
						<input type="text" class="form-control" id="numCanciones" name="numCanciones" value="${cd.numCanciones}">
					</div>
					<div class="mb-3">
						<label for="unidadesDisp" class="form-label">Unidades Disp:</label>
						<input type="text" class="form-control" id="unidadesDisp" name="unidadesDisp" value="${cd.unidadesDisp}">
					</div>
					<div class="mb-3">
						<label for="ubicacion" class="form-label">Ubicacion:</label>
						<input type="text" class="form-control" id="ubicacion" name="ubicacion" value="${cd.ubicacion}">
					</div>	
					<button type="submit" class="btn btn-warning w-100">MODIFICAR</button>
				</form>
			</div>
			<div class="col-8"></div>
		</div>

	</div>
</body>
</html>