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
			<a href="principal.jsp"> 
				<img src="img/home.jpg" style="width: 2.2rem;">
			</a>
			<h3 class="mx-3">MIS PRESTAMOS</h3>
		</div>

		<hr>

		<!-- Body -->

		<div class="d-flex flex-row mt-5">
			<a href="PrestamoController?accion=form-prestar" data-bs-toggle="tooltip" data-bs-placement="top" title="Prestar">
				<img src="img/create.png" style="width: 1.5rem;">
			</a>
			<p class="mx-2">Nuevo</p>
		</div>

		<div class="row">
			<table class="table table-bordered border-warning table-hover">
				<thead class="table-secondary">
					<tr>
						<th scope="col">Codigo</th>
						<th scope="col">Fecha Prestamo</th>
						<th scope="col">Fecha Devolucion</th>
						<th scope="col">Mora</th>
						<th scope="col">Acciones</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${prestamos}" var="prestamo"> 
						<tr>
							<td>${prestamo.codigo}</td> 
							<td>${prestamo.fechaPrestamoStr}</td> 
							<td>${prestamo.fechaDevolucionStr}</td> 
							<td>${prestamo.mora}</td>
							<td>
								<div class="d-flex flex-row">
									<!-- PAGAR MORA -->
									<c:if test="${prestamo.mora != 0}">
										<div>
											<a href="PrestamoController?accion=form-pagarmora&idPrestamo=${prestamo.idPrestamo}&codigo=${prestamo.codigo}" data-bs-toggle="tooltip" data-bs-placement="top" title="Pagar Mora">
												<img src="img/pagarmora.png" style="width: 1.5rem;">
											</a>
										</div>
									</c:if>
									<!-- DEVOLVER -->
									<c:if test="${prestamo.mora == 0}">
										<div>
											<a href="PrestamoController?accion=devolver&idPrestamo=${prestamo.idPrestamo}&codigo=${prestamo.codigo}" data-bs-toggle="tooltip" data-bs-placement="top" title="Devolver">
												<img src="img/devolver.png" style="width: 1.5rem;">
											</a>
										</div>
									</c:if>
								</div>
							</td>								
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>