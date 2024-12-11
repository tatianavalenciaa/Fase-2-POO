<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="sv.edu.cdb.model.Usuario"%>

<%
	Usuario usuario = (Usuario) session.getAttribute("usuario");
%>

<nav class="navbar bg-dark navbar-expand-lg bg-body-tertiary"
	data-bs-theme="dark">
	<div class="container-fluid">
		<a class="navbar-brand" href="#"> 
			<img src="img/donbosco.png" alt="Logo" style="width: 1.5rem;" class="d-inline-block align-text-top"> 
			<span style="color: yellow;">COLEGIO DON BOSCO</span>
		</a>
		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav me-auto mb-2 mb-lg-0">
			  <li class="nav-item">
			    <a class="nav-link active" aria-current="page" href="#">
			      <c:if test="${not empty usuario && usuario.rol == 'ADMINISTRADOR'}">
			        Administrador
			      </c:if>
			      <c:if test="${not empty usuario && usuario.rol == 'PROFESOR'}">
			        Profesor
			      </c:if>
			      <c:if test="${not empty usuario && usuario.rol == 'ALUMNO'}">
			        Alumno
			      </c:if>
			    </a>
			  </li>
			</ul>
			<div class="d-flex" role="search">
				<ul class="navbar-nav me-auto mb-2 mb-lg-0">
					<li class="nav-item">
						<a class="nav-link active" aria-current="page" href="#">
							Bienvenido: <span>${usuario.nombre}</span>
						</a>
					</li>
					<li class="nav-item">
						<a class="nav-link active" aria-current="page" href="index.jsp" style="color: yellow;">
							Cerrar Sesion
						</a>
					</li>
				</ul>
			</div>
		</div>
	</div>
</nav>