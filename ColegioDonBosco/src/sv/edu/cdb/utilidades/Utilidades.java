package sv.edu.cdb.utilidades;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import sv.edu.cdb.dao.MaterialDao;
import sv.edu.cdb.model.Material;
import sv.edu.cdb.model.Usuario;

public class Utilidades {

	public static Date convertirStringAFecha(String fechaString) throws ParseException {
        // Definimos el formato de la fecha de entrada
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        // Intentamos parsear la cadena de fecha al formato especificado
        Date fecha = formato.parse(fechaString);
        return fecha;
    }
	
	public static String convertirFechaAString(Date fecha) {
	    // Definimos el formato de la fecha de salida
	    SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
	    // Formateamos la fecha al formato especificado
	    String fechaString = formato.format(fecha);
	    return fechaString;
	}
	
	public static void registrarMaterial(HttpServletRequest request) {
		MaterialDao materialDao = new MaterialDao();
		HttpSession session = request.getSession();
		Usuario usuario = (Usuario) session.getAttribute("usuario");
		try {
            Material material = new Material();
            material.setCodigo(request.getParameter("codigo"));
            material.setIdUsuario(usuario.getIdUsuario());
            materialDao.registrarMaterial(material);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
}
