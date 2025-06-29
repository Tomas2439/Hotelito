public class habitacion {
    private int numero;
    private String categoria;
    private String detalle;
    private double costoPorNoche;
    private Boolean disponible;
    private String fechaIngreso;
    private String dniPasajeroH;

    public habitacion(int numero, String categoria, String detalle, double costoPorNoche, String fechaIngreso, String dniPasajeroH){
        this.numero = numero;
        this.categoria = categoria;
        this.detalle = detalle;
        this.costoPorNoche = costoPorNoche;
        this.disponible = false;
        this.fechaIngreso = null;
        this.dniPasajeroH = null;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public double getCostoPorNoche() {
        return costoPorNoche;
    }

    public void setCostoPorNoche(double costoPorNoche) {
        this.costoPorNoche = costoPorNoche;
    }

    public Boolean getDisponible() {
        return disponible;
    }

    public void setDisponible(Boolean disponible) {
        this.disponible = disponible;
    }

    public String getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(String fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public String getDniPasajeroH() {
        return dniPasajeroH;
    }

    public void setDniPasajeroH(String dniPasajeroH) {
        this.dniPasajeroH = dniPasajeroH;
    }
    
    @Override 
    public String toString(){
        String estado = disponible ? "Ocupado" : "Libre";
        String infoOcupacion = "";
        if(disponible){
            infoOcupacion = "\n, Pasajero Dni" + dniPasajeroH + "\n Ingreso: "+ fechaIngreso; 
        }
        return "Habitacion: "+ numero + "(" + categoria + "):"+ detalle + ", Costo por noche: $"+ costoPorNoche + "\n Estado: "+ estado + infoOcupacion;
    }
}
