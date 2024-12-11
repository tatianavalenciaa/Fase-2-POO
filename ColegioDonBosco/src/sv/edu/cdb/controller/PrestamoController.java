package sv.edu.cdb.controller;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import sv.edu.cdb.dao.CdDao;
import sv.edu.cdb.dao.DocumentoDao;
import sv.edu.cdb.dao.DvdDao;
import sv.edu.cdb.dao.LibroDao;
import sv.edu.cdb.dao.MaterialDao;
import sv.edu.cdb.dao.ObraDao;
import sv.edu.cdb.dao.ParametroSistemaDao;
import sv.edu.cdb.dao.PrestamoDao;
import sv.edu.cdb.dao.RevistaDao;
import sv.edu.cdb.dao.TesisDao;
import sv.edu.cdb.dao.UsuarioDao;
import sv.edu.cdb.model.Cd;
import sv.edu.cdb.model.Documento;
import sv.edu.cdb.model.Dvd;
import sv.edu.cdb.model.Libro;
import sv.edu.cdb.model.Material;
import sv.edu.cdb.model.Obra;
import sv.edu.cdb.model.ParametroSistema;
import sv.edu.cdb.model.Prestamo;
import sv.edu.cdb.model.Revista;
import sv.edu.cdb.model.Tesis;
import sv.edu.cdb.model.Usuario;
import sv.edu.cdb.utilidades.Utilidades;

@WebServlet("/PrestamoController")
public class PrestamoController extends HttpServlet {
    
	private static final long serialVersionUID = 1L;
	private PrestamoDao prestamoDao;
	private LibroDao libroDao;
	private RevistaDao revistaDao;
	private CdDao cdDao;
	private DvdDao dvdDao;
	private ObraDao obraDao;
	private TesisDao tesisDao;
	private DocumentoDao documentoDao;
	private UsuarioDao usuarioDao;
	private MaterialDao materialDao;
	
	public PrestamoController() {
		super();
		prestamoDao = new PrestamoDao();
		libroDao = new LibroDao();
		revistaDao = new RevistaDao();
		cdDao = new CdDao();
		dvdDao = new DvdDao();
		obraDao = new ObraDao();
		tesisDao = new TesisDao();
		documentoDao = new DocumentoDao();
		usuarioDao = new UsuarioDao();
		materialDao = new MaterialDao();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		Usuario usuario = (Usuario) session.getAttribute("usuario");
		String accion = request.getParameter("accion");
		
		try {
		
			if (accion.equals("listar")) {
				List<Prestamo> prestamos = prestamoDao.obtenerPrestamosPorCarnet(usuario.getCarnet());
				establecerValoresFaltantes(prestamos);
				request.setAttribute("prestamos", prestamos); 
				request.getRequestDispatcher("prestamo-listar.jsp").forward(request, response); 
			} else if (accion.equals("form-prestar")) {
				request.getRequestDispatcher("prestamo-prestar.jsp").forward(request, response); 
			} else if (accion.equals("form-pagarmora")) {	
				String codigo = request.getParameter("codigo");
				Prestamo prestamo = prestamoDao.obtenerPrestamo(codigo, usuario.getIdUsuario());
				prestamo.setFechaDevolucion(new Date());
				prestamoDao.modificarPrestamo(prestamo);
				response.sendRedirect("PrestamoController?accion=listar"); 
			} else if (accion.equals("devolver")) {
				Integer idPrestamo = Integer.valueOf(request.getParameter("idPrestamo"));
				String codigo = request.getParameter("codigo");
				prestamoDao.eliminarPrestamo(idPrestamo, usuario.getIdUsuario());
				modificarDisponibilidad(codigo, 1);
				response.sendRedirect("PrestamoController?accion=listar"); 
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Usuario usuario = (Usuario) session.getAttribute("usuario");
		String accion = request.getParameter("accion");
		
		try {
			if (accion.equals("prestar")) {
				String codigo = request.getParameter("codigo");
				Prestamo prestamo = obtenerPrestamoDesdeFormulario(request, codigo, usuario);
				prestamoDao.registrarPrestamo(prestamo);
				modificarDisponibilidad(codigo, -1);
				response.sendRedirect("PrestamoController?accion=listar");
			} 
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void establecerValoresFaltantes(List<Prestamo> prestamos) {
        ParametroSistemaDao parametroSistemaDao = new ParametroSistemaDao();
        long diasEnMora;
        Double mora = 0.0;
        
        try {
            // Obtenemos el precio de mora diaria parametrizada en la Base de Datos
            ParametroSistema parametroSistema = parametroSistemaDao.obtenerParametroSistemaXNombre("MORA");
            Double parametroMora = Double.valueOf(parametroSistema.getValor());

            for (Prestamo prestamo : prestamos) {
            	Date fechaHoy = new Date();
                Calendar fechaHoyCal = Calendar.getInstance();
                fechaHoyCal.setTime(fechaHoy);
                Calendar fechaDevolucionCal = Calendar.getInstance();
                fechaDevolucionCal.setTime(prestamo.getFechaDevolucion()); 

                long diffMiliSegundos = fechaHoyCal.getTimeInMillis() - fechaDevolucionCal.getTimeInMillis();
                if(diffMiliSegundos < 0) {
                    mora = 0.0;                
                } else {
                    diasEnMora = Math.round(diffMiliSegundos / (1000 * 60 * 60 * 24.0)) - 1;
                    mora = diasEnMora * parametroMora;
                }
                
                prestamo.setMora(mora);
                prestamo.setFechaPrestamoStr(Utilidades.convertirFechaAString(prestamo.getFechaPrestamo()));
                prestamo.setFechaDevolucionStr(Utilidades.convertirFechaAString(prestamo.getFechaDevolucion()));
                
			}

        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
	
	private void modificarDisponibilidad(String codigo, Integer valor) {
        
        try {
            if(codigo.startsWith("LIB")) {
                Libro libro = libroDao.obtenerLibro(codigo);
                libro.setUnidadesDisp(libro.getUnidadesDisp() + valor);
                libroDao.modificarLibro(libro);
            } else if(codigo.startsWith("REV")) {
                Revista revista = revistaDao.obtenerRevista(codigo);
                revista.setUnidadesDisp(revista.getUnidadesDisp() + valor);
                revistaDao.modificarRevista(revista);
            } else if(codigo.startsWith("CD")) {
                Cd cd = cdDao.obtenerCd(codigo);
                cd.setUnidadesDisp(cd.getUnidadesDisp() + valor);
                cdDao.modificarCd(cd);
            } else if(codigo.startsWith("DVD")) {
                Dvd dvd = dvdDao.obtenerDvd(codigo);
                dvd.setUnidadesDisp(dvd.getUnidadesDisp() + valor);
                dvdDao.modificarDvd(dvd);
            } else if(codigo.startsWith("OBR")) {
                Obra obra = obraDao.obtenerObra(codigo);
                obra.setUnidadesDisp(obra.getUnidadesDisp() + valor);
                obraDao.modificarObra(obra);
            } else if(codigo.startsWith("TES")) {
                Tesis tesis = tesisDao.obtenerTesis(codigo);
                tesis.setUnidadesDisp(tesis.getUnidadesDisp() + valor);
                tesisDao.modificarTesis(tesis);
            }  else if(codigo.startsWith("DOC")) {
                Documento documento = documentoDao.obtenerDocumento(codigo);
                documento.setUnidadesDisp(documento.getUnidadesDisp() + valor);
                documentoDao.modificarDocumento(documento);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
	
	private Prestamo obtenerPrestamoDesdeFormulario(HttpServletRequest request, String codigo, Usuario usuario) {
		Prestamo prestamo = new Prestamo();
		try {
			Material material = materialDao.obtenerMaterial(codigo);
			prestamo.setCodigo(codigo);
			prestamo.setIdMaterial(material.getIdMaterial());
			prestamo.setIdUsuario(usuario.getIdUsuario());
			prestamo.setFechaPrestamo(Utilidades.convertirStringAFecha(request.getParameter("fechaPrestamo")));
			prestamo.setFechaDevolucion(Utilidades.convertirStringAFecha(request.getParameter("fechaDevolucion")));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return prestamo;
		
	}

}
