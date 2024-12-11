<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="sv.edu.cdb.model.Revista"%>

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
			<a href="RevistaController?accion=listar"> 
				<img src="img/regresar.png" style="width: 2.2rem;">
			</a>
			<h3 class="mx-3">MODIFICAR REVISTA</h3>
		</div>

		<hr>

		<!-- Body -->

		<div class="row mt-5">
			<div class="col-4">
				<form action="RevistaController?accion=modificar" method="post">
					<div class="mb-3">
						<label for="codigo" class="form-label">Codigo:</label><br>
						<span>${revista.codigo}</span>
						<input type="text" class="form-control" id="codigo" name="codigo" hidden="true" value="${revista.codigo}">
					</div>
					<div class="mb-3">
						<label for="titulo" class="form-label">Titulo:</label>
						<input type="text" class="form-control" id="titulo" name="titulo" value="${revista.titulo}">
					</div>
					<div class="mb-3">
						<label for="editorial" class="form-label">Editorial:</label>
						<input type="text" class="form-control" id="editorial" name="editorial" value="${revista.editorial}">
					</div>
					<div class="mb-3">
						<label for="periocidad" class="form-label">Periocidad:</label>
						<input type="text" class="form-control" id="periocidad" name="periocidad" value="${revista.periocidad}">
					</div>
					<div class="mb-3">
						<label for="fechaPub" class="form-label">Fecha Pub:</label>
						<input type="text" class="form-control" id="fechaPub" name="fechaPub" value="${revista.fechaPubStr}"
							pattern="[0-9]{2}/[0-9]{2}/[0-9]{4}" 
       						title="Formato valido: DD/MM/YYYY" required>
						<div id="fechaPub" class="form-text">DD/MM/YYYY</div>
					</div>
					<div class="mb-3">
						<label for="unidadesDisp" class="form-label">Unidades Disp:</label>
						<input type="text" class="form-control" id="unidadesDisp" name="unidadesDisp" value="${revista.unidadesDisp}">
					</div>
					<div class="mb-3">
						<label for="ubicacion" class="form-label">Ubicacion:</label>
						<input type="text" class="form-control" id="ubicacion" name="ubicacion" value="${revista.ubicacion}">
					</div>	
					<button type="submit" class="btn btn-warning w-100">MODIFICAR</button>
				</form>
			</div>
			<div class="col-8"></div>
		</div>

	</div>
</body>
</html>