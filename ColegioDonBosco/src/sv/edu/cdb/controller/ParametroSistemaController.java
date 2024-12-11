package sv.edu.cdb.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sv.edu.cdb.dao.ParametroSistemaDao;
import sv.edu.cdb.model.ParametroSistema;

@WebServlet("/ParametroSistemaController")
public class ParametroSistemaController extends HttpServlet {
    
	private static final long serialVersionUID = 1L;
	private ParametroSistemaDao parametroSistemaDao;
	
	public ParametroSistemaController() {
		super();
		parametroSistemaDao = new ParametroSistemaDao();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String accion = request.getParameter("accion");
		
		try {
		
			if (accion.equals("listar")) {
				List<ParametroSistema> parametrosSistema = parametroSistemaDao.obtenerParametroSistemas();
				request.setAttribute("parametrosSistema", parametrosSistema); 
				request.getRequestDispatcher("parametrosistema-listar.jsp").forward(request, response); 
			} else if (accion.equals("form-registrar")) {
				request.getRequestDispatcher("parametrosistema-registrar.jsp").forward(request, response); 
			} else if (accion.equals("form-modificar")) {
				Integer idParametroSistema = Integer.valueOf(request.getParameter("idParametroSistema"));
				ParametroSistema parametroSistema = parametroSistemaDao.obtenerParametroSistema(idParametroSistema);
				request.setAttribute("parametroSistema", parametroSistema);
				request.getRequestDispatcher("parametrosistema-modificar.jsp").forward(request, response); 
			} else if (accion.equals("eliminar")) {
				Integer idParametroSistema = Integer.valueOf(request.getParameter("idParametroSistema"));
				parametroSistemaDao.eliminarParametroSistema(idParametroSistema);
				response.sendRedirect("ParametroSistemaController?accion=listar");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String accion = request.getParameter("accion");
		try {
			if (accion.equals("registrar")) {
				ParametroSistema parametroSistema = obtenerParametroSistemaDesdeFormulario(request);
				parametroSistemaDao.registrarParametroSistema(parametroSistema);
				response.sendRedirect("ParametroSistemaController?accion=listar");
			} else if (accion.equals("modificar")) {
				ParametroSistema parametroSistema = obtenerParametroSistemaDesdeFormulario(request);
				parametroSistemaDao.modificarParametroSistema(parametroSistema);
				response.sendRedirect("ParametroSistemaController?accion=listar");
			} 
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private ParametroSistema obtenerParametroSistemaDesdeFormulario(HttpServletRequest request) {
		ParametroSistema parametroSistema = new ParametroSistema();
		
		if (request.getParameter("idParametroSistema") != null) {
			parametroSistema.setIdParametroSistema(Integer.valueOf(request.getParameter("idParametroSistema")));
		}
		parametroSistema.setNombre(request.getParameter("nombre"));
		parametroSistema.setDescripcion(request.getParameter("descripcion"));
		parametroSistema.setValor(request.getParameter("valor"));
		
		return parametroSistema;
	}

}
