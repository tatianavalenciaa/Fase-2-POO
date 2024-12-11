package sv.edu.cdb.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import sv.edu.cdb.dao.LibroDao;
import sv.edu.cdb.dao.MaterialDao;
import sv.edu.cdb.model.Libro;
import sv.edu.cdb.model.Material;
import sv.edu.cdb.model.Usuario;
import sv.edu.cdb.utilidades.Utilidades;

@WebServlet("/LibroController")
public class LibroController extends HttpServlet {
    
	private static final long serialVersionUID = 1L;
	private LibroDao libroDao;
	
	public LibroController() {
		super();
		libroDao = new LibroDao();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String accion = request.getParameter("accion");
		String codigo = request.getParameter("codigo");
		
		try {
		
			if (accion.equals("listar")) {
				List<Libro> libros = libroDao.obtenerLibros();
				request.setAttribute("libros", libros); 
				request.getRequestDispatcher("libro-listar.jsp").forward(request, response); 
			} else if (accion.equals("form-registrar")) {
				request.getRequestDispatcher("libro-registrar.jsp").forward(request, response); 
			} else if (accion.equals("form-modificar")) {
				Libro libro = libroDao.obtenerLibro(codigo);
				request.setAttribute("libro", libro);
				request.getRequestDispatcher("libro-modificar.jsp").forward(request, response); 
			} else if (accion.equals("eliminar")) {
				libroDao.eliminarLibro(codigo);
				response.sendRedirect("LibroController?accion=listar");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String accion = request.getParameter("accion");
		try {
			if (accion.equals("registrar")) {
				Libro libro = obtenerLibroDesdeFormulario(request);
				libroDao.registrarLibro(libro);
				Utilidades.registrarMaterial(request);
				response.sendRedirect("LibroController?accion=listar");
			} else if (accion.equals("modificar")) {
				Libro libro = obtenerLibroDesdeFormulario(request);
				libroDao.modificarLibro(libro);
				response.sendRedirect("LibroController?accion=listar");
			} 
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private Libro obtenerLibroDesdeFormulario(HttpServletRequest request) {
		Libro libro = new Libro();
		
		libro.setCodigo(request.getParameter("codigo"));
		libro.setTitulo(request.getParameter("titulo"));
		libro.setAutor(request.getParameter("autor"));
		libro.setNumPag(Integer.valueOf(request.getParameter("numPag")));
		libro.setEditorial(request.getParameter("editorial"));
		libro.setIsbn(request.getParameter("isbn"));
		libro.setAnioPub(Integer.valueOf(request.getParameter("anioPub")));
		libro.setUnidadesDisp(Integer.valueOf(request.getParameter("unidadesDisp")));
		libro.setUbicacion(request.getParameter("ubicacion"));
		
		return libro;
	}

}
