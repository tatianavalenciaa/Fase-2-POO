package sv.edu.cdb.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sv.edu.cdb.dao.DocumentoDao;
import sv.edu.cdb.model.Documento;
import sv.edu.cdb.utilidades.Utilidades;

@WebServlet("/DocumentoController")
public class DocumentoController extends HttpServlet {
    
	private static final long serialVersionUID = 1L;
	private DocumentoDao documentoDao;
	
	public DocumentoController() {
		super();
		documentoDao = new DocumentoDao();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String accion = request.getParameter("accion");
		String codigo = request.getParameter("codigo");
		
		try {
		
			if (accion.equals("listar")) {
				List<Documento> documentos = documentoDao.obtenerDocumentos();
				request.setAttribute("documentos", documentos); 
				request.getRequestDispatcher("documento-listar.jsp").forward(request, response); 
			} else if (accion.equals("form-registrar")) {
				request.getRequestDispatcher("documento-registrar.jsp").forward(request, response); 
			} else if (accion.equals("form-modificar")) {
				Documento documento = documentoDao.obtenerDocumento(codigo);
				request.setAttribute("documento", documento);
				request.getRequestDispatcher("documento-modificar.jsp").forward(request, response); 
			} else if (accion.equals("eliminar")) {
				documentoDao.eliminarDocumento(codigo);
				response.sendRedirect("DocumentoController?accion=listar");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String accion = request.getParameter("accion");
		try {
			if (accion.equals("registrar")) {
				Documento documento = obtenerDocumentoDesdeFormulario(request);
				documentoDao.registrarDocumento(documento);
				Utilidades.registrarMaterial(request);
				response.sendRedirect("DocumentoController?accion=listar");
			} else if (accion.equals("modificar")) {
				Documento documento = obtenerDocumentoDesdeFormulario(request);
				documentoDao.modificarDocumento(documento);
				response.sendRedirect("DocumentoController?accion=listar");
			} 
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private Documento obtenerDocumentoDesdeFormulario(HttpServletRequest request) {
		Documento documento = new Documento();
		
		documento.setCodigo(request.getParameter("codigo"));
		documento.setTitulo(request.getParameter("titulo"));
		documento.setAutor(request.getParameter("autor"));
		documento.setEditorial(request.getParameter("editorial"));
		documento.setTematica(request.getParameter("tematica"));
		documento.setUnidadesDisp(Integer.valueOf(request.getParameter("unidadesDisp")));
		documento.setUbicacion(request.getParameter("ubicacion"));
		
		return documento;
	}

}
