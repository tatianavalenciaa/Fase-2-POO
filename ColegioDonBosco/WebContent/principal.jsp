<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="sv.edu.cdb.model.Usuario"%>

<!DOCTYPE html>
<html>
<head>
<jsp:include page="librerias.jsp" />
</head>
<body>
	<jsp:include page="navbar.jsp"></jsp:include>
	<div class="container">

		<div class="row mt-5">
			
			<!-- LIBROS -->
		
			<div class="col-3">
				<div class="card" style="width: 10rem;">
					<img src="img/libro.png" class="card-img-top" alt="...">
					<div class="card-body">
						<a href="LibroController?accion=listar" class="btn btn-warning w-100">LIBROS</a>
					</div>
				</div>
			</div>
			
			<!-- REVISTAS -->
			
			<div class="col-3">
				<div class="card" style="width: 10rem;">
					<img src="img/revista.png" class="card-img-top" alt="...">
					<div class="card-body">
						<a href="RevistaController?accion=listar" class="btn btn-warning w-100">REVISTAS</a>
					</div>
				</div>
			</div>
			
			<!-- CDS -->
			
			<div class="col-3">
				<div class="card" style="width: 10rem;">
					<img src="img/cd.png" class="card-img-top" alt="...">
					<div class="card-body">
						<a href="CdController?accion=listar" class="btn btn-warning w-100">CDS</a>
					</div>
				</div>
			</div>
			
			<!-- DVDS -->
			
			<div class="col-3">
				<div class="card" style="width: 10rem;">
					<img src="img/dvd.png" class="card-img-top" alt="...">
					<div class="card-body">
						<a href="DvdController?accion=listar" class="btn btn-warning w-100">DVDS</a>
					</div>
				</div>
			</div>
		</div>
		
		<div class="row mt-5">
			
			<!-- OBRAS-->
		
			<div class="col-3">
				<div class="card" style="width: 10rem;">
					<img src="img/obra.png" class="card-img-top" alt="...">
					<div class="card-body">
						<a href="ObraController?accion=listar" class="btn btn-warning w-100">OBRAS</a>
					</div>
				</div>
			</div>
			
			<!-- TESIS -->
			
			<div class="col-3">
				<div class="card" style="width: 10rem;">
					<img src="img/tesis.png" class="card-img-top" alt="...">
					<div class="card-body">
						<a href="TesisController?accion=listar" class="btn btn-warning w-100">TESIS</a>
					</div>
				</div>
			</div>
			
			<!-- DOCUMENTOS -->
			
			<div class="col-3">
				<div class="card" style="width: 10rem;">
					<img src="img/documento.png" class="card-img-top" alt="...">
					<div class="card-body">
						<a href="DocumentoController?accion=listar" class="btn btn-warning w-100">DOCS</a>
					</div>
				</div>
			</div>

			<!-- PARAMETROS (SI ES ADMIN)-->

			<c:if test="${not empty usuario && usuario.rol == 'ADMINISTRADOR'}">
				<div class="col-3">
					<div class="card" style="width: 10rem;">
						<img src="img/config.png" class="card-img-top" alt="...">
						<div class="card-body">
							<a href="ParametroSistemaController?accion=listar" class="btn btn-warning w-100">PARAMETROS</a>
						</div>
					</div>
				</div>
			</c:if>
			
			<!-- MIS PRESTAMOS (SI ES PROFESOR O ALUMNO)-->

			<c:if test="${not empty usuario && usuario.rol != 'ADMINISTRADOR'}">
				<div class="col-3">
					<div class="card" style="width: 10rem;">
						<img src="img/prestamo.png" class="card-img-top" alt="...">
						<div class="card-body">
							<a href="PrestamoController?accion=listar" class="btn btn-warning w-100">PRESTAMOS</a>
						</div>
					</div>
				</div>
			</c:if>
			
			
		</div>
	</div>
</body>
</html>