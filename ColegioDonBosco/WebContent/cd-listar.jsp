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
			<a href="principal.jsp"> 
				<img src="img/home.jpg" style="width: 2.2rem;">
			</a>
			<h3 class="mx-3">CDS</h3>
		</div>

		<hr>

		<!-- Body -->

		<c:if test="${not empty usuario && usuario.rol == 'ADMINISTRADOR'}">
			<div class="d-flex flex-row mt-5">
				<a href="CdController?accion=form-registrar" data-bs-toggle="tooltip" data-bs-placement="top" title="Registrar">
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
						<th scope="col">Artista</th>
						<th scope="col">Genero</th>
						<th scope="col">Duracion</th>
						<th scope="col">Num Canciones</th>
						<th scope="col">Unidades Disp</th>
						<th scope="col">Ubicacion</th>
						<th scope="col">Acciones</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${cds}" var="cd"> 
						<tr>
							<td>${cd.codigo}</td> 
							<td>${cd.titulo}</td> 
							<td>${cd.artista}</td> 
							<td>${cd.genero}</td> 
							<td>${cd.duracion}</td> 
							<td>${cd.numCanciones}</td> 
							<td>${cd.unidadesDisp}</td> 
							<td>${cd.ubicacion}</td>
							<td>
								<div class="d-flex flex-row">
									
									<!-- MODIFICAR Y ELIMINAR (SI ES ADMIN)-->
									
									<c:if test="${not empty usuario && usuario.rol == 'ADMINISTRADOR'}">
										<div>
											<a href="CdController?accion=form-modificar&codigo=${cd.codigo}" data-bs-toggle="tooltip" data-bs-placement="top" title="Modificar">
												<img src="img/edit.png" style="width: 1.5rem;">
											</a>
										</div>
										<div class="mx-3">
											<a href="CdController?accion=eliminar&codigo=${cd.codigo}" data-bs-toggle="tooltip" data-bs-placement="top" title="Eliminar">
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