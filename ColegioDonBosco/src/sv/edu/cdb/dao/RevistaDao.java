package sv.edu.cdb.dao;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import sv.edu.cdb.conexion.Conexion;
import sv.edu.cdb.model.Revista;

public class RevistaDao extends Conexion {
    
    private static final Logger logger = LogManager.getLogger(RevistaDao.class);
    
    public List<Revista> obtenerRevistas() throws SQLException {
        
        List<Revista> revistas = new ArrayList<>();
        
        sql =
            """
            select 
                *
            from 
                revista
            order by 
                codigo asc
            """;
        
        try {
    
            logger.info("sql:\n" + sql);
            
            abrirConexion();
            stmt = conexion.prepareStatement(sql);
            rs = stmt.executeQuery(); 
            
            while (rs.next()) {
                Revista revista = new Revista();
                revista.setCodigo(rs.getString("codigo"));
                revista.setTitulo(rs.getString("titulo"));
                revista.setEditorial(rs.getString("editorial"));
                revista.setPeriocidad(rs.getString("periocidad"));
                revista.setFechaPub(new Date(rs.getDate("fecha_pub").getTime()));
                revista.setUnidadesDisp(rs.getInt("unidades_disp"));
                revista.setUbicacion(rs.getString("ubicacion"));
                revistas.add(revista);
            }
            
        } catch (SQLException e) {
            logger.error(e.getMessage());
        } finally {
            cerrarConexion();
        }
       
        return revistas;
        
    }
    
    public Revista obtenerRevista(String codigo) throws SQLException {
        
        Revista revista = null;
        
        sql = 
            """
            select 
                *
            from 
                revista
            where
                codigo = ?
            """;
        
        try {
    
            logger.info("sql:\n" + sql);
            
            abrirConexion();
            stmt = conexion.prepareStatement(sql);
            int i = 0;
            stmt.setString(++i, codigo);
            rs = stmt.executeQuery(); 
            
            while (rs.next()) {
                revista = new Revista();
                revista.setCodigo(rs.getString("codigo"));
                revista.setTitulo(rs.getString("titulo"));
                revista.setEditorial(rs.getString("editorial"));
                revista.setPeriocidad(rs.getString("periocidad"));
                revista.setFechaPub(new Date(rs.getDate("fecha_pub").getTime()));
                revista.setUnidadesDisp(rs.getInt("unidades_disp"));
                revista.setUbicacion(rs.getString("ubicacion"));
            }
            
        } catch (SQLException e) {
            logger.error(e.getMessage());
        } finally {
            cerrarConexion();
        }
       
        return revista;
        
    }
    
    public void registrarRevista(Revista revista) throws SQLException {
        
        sql = 
            """
            insert into revista (
                codigo, titulo, editorial, periocidad, fecha_pub, unidades_disp, ubicacion
            )
            values (
                ?, ?, ?, ?, ?, ?, ?
            )
            """;
        
        try {
    
            logger.info("sql:\n" + sql);
            
            abrirConexion();
            stmt = conexion.prepareStatement(sql);
            int i = 0;
            stmt.setString(++i, revista.getCodigo());
            stmt.setString(++i, revista.getTitulo());
            stmt.setString(++i, revista.getEditorial());
            stmt.setString(++i, revista.getPeriocidad());
            stmt.setTimestamp(++i, new Timestamp(revista.getFechaPub().getTime()));
            stmt.setInt(++i, revista.getUnidadesDisp());
            stmt.setString(++i, revista.getUbicacion());
            
            stmt.executeUpdate(); 
            
        } catch (SQLException e) {
            logger.error(e.getMessage());
        } finally {
            cerrarConexion();
        }
        
    }
    
    public void modificarRevista(Revista revista) throws SQLException {
        sql =
            """
            update 
                revista
            set
                titulo = ?, 
                editorial = ?, 
                periocidad = ?, 
                fecha_pub = ?, 
                unidades_disp = ?,
                ubicacion = ?
            where 
                codigo = ?
            """;
        
        try {
    
            logger.info("sql:\n" + sql);
            
            abrirConexion();
            stmt = conexion.prepareStatement(sql);
            int i = 0;
            stmt.setString(++i, revista.getTitulo());
            stmt.setString(++i, revista.getEditorial());
            stmt.setString(++i, revista.getPeriocidad());
            stmt.setTimestamp(++i, new Timestamp(revista.getFechaPub().getTime()));
            stmt.setInt(++i, revista.getUnidadesDisp());
            stmt.setString(++i, revista.getUbicacion());
            stmt.setString(++i, revista.getCodigo());
            
            stmt.executeUpdate(); 
            
        } catch (SQLException e) {
            logger.error(e.getMessage());
        } finally {
            cerrarConexion();
        }
    }
    
    public void eliminarRevista(String codigo) throws SQLException {
        
        sql = 
            """
            delete from
                revista
            where
                codigo = ?
            """;
        
        try {
    
            logger.info("sql:\n" + sql);
            
            abrirConexion();
            stmt = conexion.prepareStatement(sql);
            int i = 0;
            stmt.setString(++i, codigo);
            
            stmt.executeUpdate(); 
            
        } catch (SQLException e) {
            logger.error(e.getMessage());
        } finally {
            cerrarConexion();
        }
    }
}
