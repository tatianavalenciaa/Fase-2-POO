package sv.edu.cdb.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Conexion {
    
    private static final Logger logger = LogManager.getLogger(Conexion.class);    
    protected Connection conexion;
    protected PreparedStatement stmt;
    protected ResultSet rs;
    protected String sql;
    
    public void abrirConexion() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/colegiodonbosco";
        String username = "root";
        String password = "root"; 

        try {
			Class.forName("com.mysql.cj.jdbc.Driver");
	        conexion = DriverManager.getConnection(url, username, password);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public void cerrarConexion() throws SQLException {
        if(conexion != null ){
            conexion.close();
        }
        if(stmt != null ){
            stmt.close();
        }
        if(rs != null ){
            rs.close();
        }
    }
    
}