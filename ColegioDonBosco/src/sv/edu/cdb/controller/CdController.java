package sv.edu.cdb.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sv.edu.cdb.dao.CdDao;
import sv.edu.cdb.model.Cd;
import sv.edu.cdb.utilidades.Utilidades;

@WebServlet("/CdController")
public class CdController extends HttpServlet {
    
	private static final long serialVersionUID = 1L;
	private CdDao cdDao;
	
	public CdController() {
		super();
		cdDao = new CdDao();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String accion = request.getParameter("accion");
		String codigo = request.getParameter("codigo");
		
		try {
		
			if (accion.equals("listar")) {
				List<Cd> cds = cdDao.obtenerCds();
				request.setAttribute("cds", cds); 
				request.getRequestDispatcher("cd-listar.jsp").forward(request, response); 
			} else if (accion.equals("form-registrar")) {
				request.getRequestDispatcher("cd-registrar.jsp").forward(request, response); 
			} else if (accion.equals("form-modificar")) {
				Cd cd = cdDao.obtenerCd(codigo);
				request.setAttribute("cd", cd);
				request.getRequestDispatcher("cd-modificar.jsp").forward(request, response); 
			} else if (accion.equals("eliminar")) {
				cdDao.eliminarCd(codigo);
				response.sendRedirect("CdController?accion=listar");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String accion = request.getParameter("accion");
		try {
			if (accion.equals("registrar")) {
				Cd cd = obtenerCdDesdeFormulario(request);
				cdDao.registrarCd(cd);
				Utilidades.registrarMaterial(request);
				response.sendRedirect("CdController?accion=listar");
			} else if (accion.equals("modificar")) {
				Cd cd = obtenerCdDesdeFormulario(request);
				cdDao.modificarCd(cd);
				response.sendRedirect("CdController?accion=listar");
			} 
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private Cd obtenerCdDesdeFormulario(HttpServletRequest request) {
		Cd cd = new Cd();
		
		cd.setCodigo(request.getParameter("codigo"));
		cd.setTitulo(request.getParameter("titulo"));
		cd.setArtista(request.getParameter("artista"));
		cd.setGenero(request.getParameter("genero"));
		cd.setDuracion(request.getParameter("duracion"));
		cd.setNumCanciones(Integer.valueOf(request.getParameter("numCanciones")));
		cd.setUnidadesDisp(Integer.valueOf(request.getParameter("unidadesDisp")));
		cd.setUbicacion(request.getParameter("ubicacion"));
		
		return cd;
	}

}
