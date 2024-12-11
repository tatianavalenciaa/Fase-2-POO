package sv.edu.cdb.model;

import java.util.Date;

public class Revista extends MaterialEscrito {
    
    private String periocidad;
    private Date fechaPub;
    private String fechaPubStr;
    
    public Revista() {
    }

    public String getPeriocidad() {
        return periocidad;
    }

    public void setPeriocidad(String periocidad) {
        this.periocidad = periocidad;
    }

    public Date getFechaPub() {
        return fechaPub;
    }

    public void setFechaPub(Date fechaPub) {
        this.fechaPub = fechaPub;
    }

	public String getFechaPubStr() {
		return fechaPubStr;
	}

	public void setFechaPubStr(String fechaPubStr) {
		this.fechaPubStr = fechaPubStr;
	}
    
}
