<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="sv.edu.cdb.model.ParametroSistema"%>

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
			<a href="ParametroSistemaController?accion=listar"> 
				<img src="img/regresar.png" style="width: 2.2rem;">
			</a>
			<h3 class="mx-3">REGISTRAR PARAMETRO SISTEMA</h3>
		</div>

		<hr>

		<!-- Body -->

		<div class="row mt-5">
			<div class="col-4">
				<form action="ParametroSistemaController?accion=registrar" method="post">
					<div class="mb-3">
						<label for="idParametroSistema" class="form-label">Id:</label>
						<input type="text" class="form-control" id="idParametroSistema" name="idParametroSistema" disabled="disabled">
						<div id="fechaPrestamo" class="form-text">AUTOGENERADO</div>
					</div>
					<div class="mb-3">
						<label for="nombre" class="form-label">Nombre:</label>
						<input type="text" class="form-control" id="nombre" name="nombre">
					</div>
					<div class="mb-3">
						<label for="descripcion" class="form-label">Descripcion:</label>
						<input type="text" class="form-control" id="descripcion" name="descripcion">
					</div>
					<div class="mb-3">
						<label for="valor" class="form-label">Valor:</label>
						<input type="text" class="form-control" id="valor" name="valor">
					</div>
					<button type="submit" class="btn btn-warning w-100">REGISTRAR</button>
				</form>
			</div>
			<div class="col-8"></div>
		</div>

	</div>
</body>
</html>