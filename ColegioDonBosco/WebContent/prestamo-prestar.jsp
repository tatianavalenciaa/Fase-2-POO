<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="sv.edu.cdb.model.Prestamo"%>

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
			<a href="PrestamoController?accion=listar"> 
				<img src="img/regresar.png" style="width: 2.2rem;">
			</a>
			<h3 class="mx-3">NUEVO PRESTAMO</h3>
		</div>

		<hr>

		<!-- Body -->

		<div class="row mt-5">
			<div class="col-4">
				<form action="PrestamoController?accion=prestar" method="post">
					<div class="mb-3">
						<label for="codigo" class="form-label">Codigo Material:</label>
						<input type="text" class="form-control" id="codigo" name="codigo">
					</div>
					<div class="mb-3">
						<label for="fechaPrestamo" class="form-label">Fecha Prestamo:</label>
						<input type="text" class="form-control" id="fechaPrestamo" name="fechaPrestamo"
							pattern="[0-9]{2}/[0-9]{2}/[0-9]{4}" 
       						title="Formato valido: DD/MM/YYYY" required>
						<div id="fechaPrestamo" class="form-text">DD/MM/YYYY</div>
					</div>
					<div class="mb-3">
						<label for="fechaDevolucion" class="form-label">Fecha Devolucion:</label>
						<input type="text" class="form-control" id="fechaDevolucion" name="fechaDevolucion"
							pattern="[0-9]{2}/[0-9]{2}/[0-9]{4}" 
       						title="Formato valido: DD/MM/YYYY" required>
						<div id="fechaDevolucion" class="form-text">DD/MM/YYYY</div>
					</div>
					<button type="submit" class="btn btn-warning w-100">PRESTAR</button>
				</form>
			</div>
			<div class="col-8"></div>
		</div>

	</div>
</body>
</html>