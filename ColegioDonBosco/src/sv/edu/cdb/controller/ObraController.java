package sv.edu.cdb.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sv.edu.cdb.dao.ObraDao;
import sv.edu.cdb.model.Obra;
import sv.edu.cdb.utilidades.Utilidades;

@WebServlet("/ObraController")
public class ObraController extends HttpServlet {
    
	private static final long serialVersionUID = 1L;
	private ObraDao obraDao;
	
	public ObraController() {
		super();
		obraDao = new ObraDao();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String accion = request.getParameter("accion");
		String codigo = request.getParameter("codigo");
		
		try {
		
			if (accion.equals("listar")) {
				List<Obra> obras = obraDao.obtenerObras();
				request.setAttribute("obras", obras); 
				request.getRequestDispatcher("obra-listar.jsp").forward(request, response); 
			} else if (accion.equals("form-registrar")) {
				request.getRequestDispatcher("obra-registrar.jsp").forward(request, response); 
			} else if (accion.equals("form-modificar")) {
				Obra obra = obraDao.obtenerObra(codigo);
				request.setAttribute("obra", obra);
				request.getRequestDispatcher("obra-modificar.jsp").forward(request, response); 
			} else if (accion.equals("eliminar")) {
				obraDao.eliminarObra(codigo);
				response.sendRedirect("ObraController?accion=listar");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String accion = request.getParameter("accion");
		try {
			if (accion.equals("registrar")) {
				Obra obra = obtenerObraDesdeFormulario(request);
				obraDao.registrarObra(obra);
				Utilidades.registrarMaterial(request);
				response.sendRedirect("ObraController?accion=listar");
			} else if (accion.equals("modificar")) {
				Obra obra = obtenerObraDesdeFormulario(request);
				obraDao.modificarObra(obra);
				response.sendRedirect("ObraController?accion=listar");
			} 
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private Obra obtenerObraDesdeFormulario(HttpServletRequest request) {
		Obra obra = new Obra();
		
		obra.setCodigo(request.getParameter("codigo"));
		obra.setTitulo(request.getParameter("titulo"));
		obra.setAutor(request.getParameter("autor"));
		obra.setEditorial(request.getParameter("editorial"));	
		obra.setGenero(request.getParameter("genero"));
		obra.setUnidadesDisp(Integer.valueOf(request.getParameter("unidadesDisp")));
		obra.setUbicacion(request.getParameter("ubicacion"));
		
		return obra;
	}

}
