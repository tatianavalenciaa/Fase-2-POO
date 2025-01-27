package sv.edu.cdb.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import sv.edu.cdb.dao.UsuarioDao;
import sv.edu.cdb.model.Usuario;

@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UsuarioDao usuarioDao;

	public LoginController() {
		super();
		usuarioDao = new UsuarioDao();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String user = request.getParameter("usuario");
		String pass = request.getParameter("password");
		HttpSession session = request.getSession(true);
		try {
			Usuario usuario = usuarioDao.obtenerUsuarioXCarnetYPassword(user, pass);
			session.setAttribute("usuario", usuario);
		} catch (SQLException e) {
			e.printStackTrace();
		}
        response.sendRedirect("principal.jsp");
	}

}
