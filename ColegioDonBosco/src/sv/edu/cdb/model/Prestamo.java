package sv.edu.cdb.model;

import java.util.Calendar;
import java.util.Date;

public class Prestamo {
    
    private Integer idPrestamo;
    private Integer idMaterial;
    private Integer idUsuario;
    private String codigo;
    private Date fechaPrestamo;
    private Date fechaDevolucion;
    private String fechaPrestamoStr;
    private String fechaDevolucionStr;
    private Double mora;

    public Prestamo() {
    }

    public Integer getIdPrestamo() {
        return idPrestamo;
    }

    public void setIdPrestamo(Integer idPrestamo) {
        this.idPrestamo = idPrestamo;
    }

    public Integer getIdMaterial() {
        return idMaterial;
    }

    public void setIdMaterial(Integer idMaterial) {
        this.idMaterial = idMaterial;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    
    public Date getFechaPrestamo() {
        return fechaPrestamo;
    }

    public void setFechaPrestamo(Date fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    public Date getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(Date fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }
    
	public String getFechaPrestamoStr() {
		return fechaPrestamoStr;
	}

	public void setFechaPrestamoStr(String fechaPrestamoStr) {
		this.fechaPrestamoStr = fechaPrestamoStr;
	}

	public String getFechaDevolucionStr() {
		return fechaDevolucionStr;
	}

	public void setFechaDevolucionStr(String fechaDevolucionStr) {
		this.fechaDevolucionStr = fechaDevolucionStr;
	}

	public Double getMora() {
		return mora;
	}

	public void setMora(Double mora) {
		this.mora = mora;
	}
    
}
