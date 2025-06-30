package src;
public class Main {
    public static void main(String[] args) {
        
        Hotel miHotel = new Hotel("Hotelito Necochea", 1000.0);

        miHotel.cargarHabArchivo("habitaciones.txt");
        miHotel.cargarPasArchivo("pasajeros.txt");

        miHotel.mostrarEstadoCaja();
        miHotel.listarTodasLasHabitaciones();

        miHotel.menuPrincipal();
    }
}
