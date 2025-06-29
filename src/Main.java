package src;
public class Main {
    public static void main(String[] args) {
        // Inicializar el hotel
        Hotel miHotel = new Hotel("Hotel UTN Necochea", 1000.0);

        // Paso 1: Crear Archivos de Datos Simulados (esto se haría manualmente o con un script aparte)
        // Por ejemplo:
        // habitaciones.txt:
        // 101,Simple,Cama individual,50.0
        // 102,Doble,Dos camas individuales,75.0
        // 103,Suite,Cama king-size y sala,150.0
        // 201,Simple,Cama individual,50.0
        // 202,Doble,Dos camas individuales,75.0

        // pasajeros.txt:
        // 12345678,Juan Perez,juan.perez@example.com,1122334455
        // 87654321,Maria Lopez,maria.lopez@example.com,1199887766

        // Paso 2: Cargar datos desde archivos (asegúrate de que estos archivos existan en la misma ubicación que tu MainHotel.java o proporciona la ruta completa)
        miHotel.cargarHabitacionesDesdeArchivo("habitaciones.txt");
        miHotel.cargarPasajerosDesdeArchivo("pasajeros.txt");

        // Mostrar estado inicial de caja y habitaciones
        miHotel.mostrarEstadoCaja();
        miHotel.listarTodasLasHabitaciones();

        // Paso 3: Iniciar el Menú Principal para interactuar
        miHotel.menuPrincipal();
    }
}
