package sv.edu.cdb.model;

public class MaterialPadre {
    
    // atributos comunes en: libro, revista, dvd y cd
    
    private String codigo;
    private String titulo;
    private Integer unidadesDisp;
    private String ubicacion;
   
    public MaterialPadre() {
    }    
    
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getUnidadesDisp() {
        return unidadesDisp;
    }

    public void setUnidadesDisp(Integer unidadesDisp) {
        this.unidadesDisp = unidadesDisp;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }
    
    

}
