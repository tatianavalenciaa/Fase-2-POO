package sv.edu.cdb.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import sv.edu.cdb.dao.MaterialDao;
import sv.edu.cdb.dao.RevistaDao;
import sv.edu.cdb.model.Material;
import sv.edu.cdb.model.Revista;
import sv.edu.cdb.model.Usuario;
import sv.edu.cdb.utilidades.Utilidades;

@WebServlet("/RevistaController")
public class RevistaController extends HttpServlet {
    
	private static final long serialVersionUID = 1L;
	private RevistaDao revistaDao;
	
	public RevistaController() {
		super();
		revistaDao = new RevistaDao();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String accion = request.getParameter("accion");
		String codigo = request.getParameter("codigo");
		
		try {
		
			if (accion.equals("listar")) {
				List<Revista> revistas = revistaDao.obtenerRevistas();
				establecerValoresFaltantes(revistas);
				request.setAttribute("revistas", revistas); 
				request.getRequestDispatcher("revista-listar.jsp").forward(request, response); 
			} else if (accion.equals("form-registrar")) {
				request.getRequestDispatcher("revista-registrar.jsp").forward(request, response); 
			} else if (accion.equals("form-modificar")) {
				Revista revista = revistaDao.obtenerRevista(codigo);
				revista.setFechaPubStr(Utilidades.convertirFechaAString(revista.getFechaPub()));
				request.setAttribute("revista", revista);
				request.getRequestDispatcher("revista-modificar.jsp").forward(request, response); 
			} else if (accion.equals("eliminar")) {
				revistaDao.eliminarRevista(codigo);
				response.sendRedirect("RevistaController?accion=listar");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String accion = request.getParameter("accion");
		try {
			if (accion.equals("registrar")) {
				Revista revista = obtenerRevistaDesdeFormulario(request);
				revistaDao.registrarRevista(revista);
				Utilidades.registrarMaterial(request);
				response.sendRedirect("RevistaController?accion=listar");
			} else if (accion.equals("modificar")) {
				Revista revista = obtenerRevistaDesdeFormulario(request);
				revistaDao.modificarRevista(revista);
				response.sendRedirect("RevistaController?accion=listar");
			} 
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void establecerValoresFaltantes(List<Revista> revistas) {
		for (Revista revista : revistas) {
			revista.setFechaPubStr(Utilidades.convertirFechaAString(revista.getFechaPub()));
		}
    }

	private Revista obtenerRevistaDesdeFormulario(HttpServletRequest request) {
		Revista revista = new Revista();
		
		try {
			revista.setCodigo(request.getParameter("codigo"));
			revista.setTitulo(request.getParameter("titulo"));
			revista.setEditorial(request.getParameter("editorial"));
			revista.setPeriocidad(request.getParameter("periocidad"));
			revista.setFechaPub(Utilidades.convertirStringAFecha(request.getParameter("fechaPub")));
			revista.setUnidadesDisp(Integer.valueOf(request.getParameter("unidadesDisp")));
			revista.setUbicacion(request.getParameter("ubicacion"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return revista;
	}
	
}
