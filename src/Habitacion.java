package src;
import java.time.LocalDateTime;
class Habitacion {
    private int numero;
    private String categoria;
    private String detalle;
    private double costoPorNoche;
    private boolean enUso;
    private LocalDateTime fechaIngreso; //Utilizacion de LocalDateTime porque se puede usar en mysql y hacerlo string es mucha vuelta.
    private String dniPasajeroActual; // DNI del pasajero que la ocupa

    public Habitacion(int numero, String categoria, String detalle, double costoPorNoche) {
        this.numero = numero;
        this.categoria = categoria;
        this.detalle = detalle;
        this.costoPorNoche = costoPorNoche;
        this.enUso = false;
        this.fechaIngreso = null;
        this.dniPasajeroActual = null;
    }

    // Getters
    public int getNumero() {
        return numero;
    }

    public String getCategoria() {
        return categoria;
    }

    public String getDetalle() {
        return detalle;
    }

    public double getCostoPorNoche() {
        return costoPorNoche;
    }

    public boolean estaEnUso() {
        return enUso;
    }

    public LocalDateTime getFechaIngreso() {
        return fechaIngreso;
    }

    public String getDniPasajeroActual() {
        return dniPasajeroActual;
    }

    // Setters 
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public void setCostoPorNoche(double costoPorNoche) {
        this.costoPorNoche = costoPorNoche;
    }

    // Metodos especificos de la habitacion
    public boolean ocupar(String dniPasajero, LocalDateTime fechaIngreso) {
        if (!this.enUso) {
            this.enUso = true;
            this.dniPasajeroActual = dniPasajero;
            this.fechaIngreso = fechaIngreso;
            return true;
        }
        return false;
    }

    public boolean liberar() {
        if (this.enUso) {
            this.enUso = false;
            this.dniPasajeroActual = null;
            this.fechaIngreso = null;
            return true;
        }
        return false;
    }

    public boolean estaLibre() {
        return !this.enUso;
    }

    @Override
    public String toString() {
        String estado = enUso ? "Ocupada" : "Libre";
        String infoOcupacion = "";
        if (enUso) {
            infoOcupacion = ", Pasajero DNI: " + dniPasajeroActual + ", Ingreso: " + fechaIngreso.format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
        }
        return "Habitacion N°" + numero + " (" + categoria + "): " + detalle + ", Costo/Noche: $" + String.format("%.2f", costoPorNoche) + ", Estado: " + estado + infoOcupacion;
    }
}
