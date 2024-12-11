package sv.edu.cdb.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sv.edu.cdb.dao.DvdDao;
import sv.edu.cdb.model.Dvd;
import sv.edu.cdb.utilidades.Utilidades;

@WebServlet("/DvdController")
public class DvdController extends HttpServlet {
    
	private static final long serialVersionUID = 1L;
	private DvdDao dvdDao;
	
	public DvdController() {
		super();
		dvdDao = new DvdDao();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String accion = request.getParameter("accion");
		String codigo = request.getParameter("codigo");
		
		try {
		
			if (accion.equals("listar")) {
				List<Dvd> dvds = dvdDao.obtenerDvds();
				request.setAttribute("dvds", dvds); 
				request.getRequestDispatcher("dvd-listar.jsp").forward(request, response); 
			} else if (accion.equals("form-registrar")) {
				request.getRequestDispatcher("dvd-registrar.jsp").forward(request, response); 
			} else if (accion.equals("form-modificar")) {
				Dvd dvd = dvdDao.obtenerDvd(codigo);
				request.setAttribute("dvd", dvd);
				request.getRequestDispatcher("dvd-modificar.jsp").forward(request, response); 
			} else if (accion.equals("eliminar")) {
				dvdDao.eliminarDvd(codigo);
				response.sendRedirect("DvdController?accion=listar");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String accion = request.getParameter("accion");
		try {
			if (accion.equals("registrar")) {
				Dvd dvd = obtenerDvdDesdeFormulario(request);
				dvdDao.registrarDvd(dvd);
				Utilidades.registrarMaterial(request);
				response.sendRedirect("DvdController?accion=listar");
			} else if (accion.equals("modificar")) {
				Dvd dvd = obtenerDvdDesdeFormulario(request);
				dvdDao.modificarDvd(dvd);
				response.sendRedirect("DvdController?accion=listar");
			} 
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private Dvd obtenerDvdDesdeFormulario(HttpServletRequest request) {
		Dvd dvd = new Dvd();
		
		dvd.setCodigo(request.getParameter("codigo"));
		dvd.setTitulo(request.getParameter("titulo"));
		dvd.setDirector(request.getParameter("director"));
		dvd.setDuracion(request.getParameter("duracion"));
		dvd.setGenero(request.getParameter("genero"));
		dvd.setUnidadesDisp(Integer.valueOf(request.getParameter("unidadesDisp")));
		dvd.setUbicacion(request.getParameter("ubicacion"));
		
		return dvd;
	}

}
