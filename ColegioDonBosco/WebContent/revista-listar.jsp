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
			<a href="principal.jsp"> 
				<img src="img/home.jpg" style="width: 2.2rem;">
			</a>
			<h3 class="mx-3">REVISTAS</h3>
		</div>

		<hr>

		<!-- Body -->

		<c:if test="${not empty usuario && usuario.rol == 'ADMINISTRADOR'}">
			<div class="d-flex flex-row mt-5">
				<a href="RevistaController?accion=form-registrar" data-bs-toggle="tooltip" data-bs-placement="top" title="Registrar">
					<img src="img/create.png" style="width: 1.5rem;">
				</a>
				<p class="mx-2">Nuevo</p>
			</div>
		</c:if>

		<div class="row">
			<table class="table table-bordered border-warning table-hover">
				<thead class="table-secondary">
					<tr>
						<th scope="col">Codigo</th>
						<th scope="col">Titulo</th>
						<th scope="col">Editorial</th>
						<th scope="col">Periocidad</th>
						<th scope="col">Fecha Pub</th>
						<th scope="col">Unidades Disp</th>
						<th scope="col">Ubicacion</th>
						<th scope="col">Acciones</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${revistas}" var="revista"> 
						<tr>
							<td>${revista.codigo}</td> 
							<td>${revista.titulo}</td> 
							<td>${revista.editorial}</td> 
							<td>${revista.periocidad}</td> 
							<td>${revista.fechaPubStr}</td> 
							<td>${revista.unidadesDisp}</td> 
							<td>${revista.ubicacion}</td>
							<td>
								<div class="d-flex flex-row">
									
									<!-- MODIFICAR Y ELIMINAR (SI ES ADMIN)-->
									
									<c:if test="${not empty usuario && usuario.rol == 'ADMINISTRADOR'}">
										<div>
											<a href="RevistaController?accion=form-modificar&codigo=${revista.codigo}" data-bs-toggle="tooltip" data-bs-placement="top" title="Modificar">
												<img src="img/edit.png" style="width: 1.5rem;">
											</a>
										</div>
										<div class="mx-3">
											<a href="RevistaController?accion=eliminar&codigo=${revista.codigo}" data-bs-toggle="tooltip" data-bs-placement="top" title="Eliminar">
												<img src="img/delete.png" style="width: 1.5rem;">
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