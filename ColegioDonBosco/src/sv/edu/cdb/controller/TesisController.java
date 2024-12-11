package sv.edu.cdb.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sv.edu.cdb.dao.TesisDao;
import sv.edu.cdb.model.Tesis;
import sv.edu.cdb.utilidades.Utilidades;

@WebServlet("/TesisController")
public class TesisController extends HttpServlet {
    
	private static final long serialVersionUID = 1L;
	private TesisDao tesisDao;
	
	public TesisController() {
		super();
		tesisDao = new TesisDao();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String accion = request.getParameter("accion");
		String codigo = request.getParameter("codigo");
		
		try {
		
			if (accion.equals("listar")) {
				List<Tesis> tesis = tesisDao.obtenerTesises();
				request.setAttribute("tesis", tesis); 
				request.getRequestDispatcher("tesis-listar.jsp").forward(request, response); 
			} else if (accion.equals("form-registrar")) {
				request.getRequestDispatcher("tesis-registrar.jsp").forward(request, response); 
			} else if (accion.equals("form-modificar")) {
				Tesis tesis = tesisDao.obtenerTesis(codigo);
				request.setAttribute("tesis", tesis);
				request.getRequestDispatcher("tesis-modificar.jsp").forward(request, response); 
			} else if (accion.equals("eliminar")) {
				tesisDao.eliminarTesis(codigo);
				response.sendRedirect("TesisController?accion=listar");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String accion = request.getParameter("accion");
		try {
			if (accion.equals("registrar")) {
				Tesis tesis = obtenerTesisDesdeFormulario(request);
				tesisDao.registrarTesis(tesis);
				Utilidades.registrarMaterial(request);
				response.sendRedirect("TesisController?accion=listar");
			} else if (accion.equals("modificar")) {
				Tesis tesis = obtenerTesisDesdeFormulario(request);
				tesisDao.modificarTesis(tesis);
				response.sendRedirect("TesisController?accion=listar");
			} 
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private Tesis obtenerTesisDesdeFormulario(HttpServletRequest request) {
		Tesis tesis = new Tesis();
		
		tesis.setCodigo(request.getParameter("codigo"));
		tesis.setTitulo(request.getParameter("titulo"));
		tesis.setAutor(request.getParameter("autor"));
		tesis.setEditorial(request.getParameter("editorial"));
		tesis.setFacultad(request.getParameter("facultad"));
		tesis.setCarrera(request.getParameter("carrera"));
		tesis.setUnidadesDisp(Integer.valueOf(request.getParameter("unidadesDisp")));
		tesis.setUbicacion(request.getParameter("ubicacion"));
		
		return tesis;
	}

}
