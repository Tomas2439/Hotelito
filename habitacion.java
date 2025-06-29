import java.text.SimpleDateFormat;
import java.util.Date;

public class Habitacion {
    private int numero;
    private Categoria categoria;
    private double precio;
    private EstadoHabitacion estado;
    private String fechaIngreso;
    private Pasajero pasajeroAsignado;
    
    public Habitacion(int numero, Categoria categoria, double precio) {
        this.numero = numero;
        this.categoria = categoria;
        this.precio = precio;
        this.estado = EstadoHabitacion.DISPONIBLE;
        this.fechaIngreso = null;
        this.pasajeroAsignado = null;
    }
    
    // Getters y Setters
    public int getNumero() { return numero; }
    public void setNumero(int numero) { this.numero = numero; }
    
    public Categoria getCategoria() { return categoria; }
    public void setCategoria(Categoria categoria) { this.categoria = categoria; }
    
    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }
    
    public EstadoHabitacion getEstado() { return estado; }
    public void setEstado(EstadoHabitacion estado) { this.estado = estado; }
    
    public String getFechaIngreso() { return fechaIngreso; }
    public void setFechaIngreso(String fechaIngreso) { this.fechaIngreso = fechaIngreso; }
    
    public Pasajero getPasajeroAsignado() { return pasajeroAsignado; }
    public void setPasajeroAsignado(Pasajero pasajeroAsignado) { this.pasajeroAsignado = pasajeroAsignado; }
    
    //Se asigna habitación
    public boolean asignarHabitacion(Pasajero pasajero) {
        if (this.estado == EstadoHabitacion.DISPONIBLE) {
            this.pasajeroAsignado = pasajero;
            this.estado = EstadoHabitacion.OCUPADA;
            
            // Generar fecha actual como String
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            this.fechaIngreso = sdf.format(new Date());
            
            return true;
        }
        return false;
    }
    
    // Método para liberar habitación
    public boolean liberarHabitacion() {
        if (this.estado == EstadoHabitacion.OCUPADA) {
            this.pasajeroAsignado = null;
            this.estado = EstadoHabitacion.DISPONIBLE;
            this.fechaIngreso = null;
            return true;
        }
        return false;
    }
    
    @Override
    public String toString() {
        String info = "Habitación " + numero + " - " + categoria + " - $" + precio + " - " + estado;
        if (pasajeroAsignado != null) {
            info += " - Ocupada por: " + pasajeroAsignado.getNombre() + " (Ingreso: " + fechaIngreso + ")";
        }
        return info;
    }
}