package src;
import java.io.FileNotFoundException;
import java.io.File;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

class Hotel {
    private String nombre;
    private double montoActualCaja;
    private double cajaInicial;
    private ArrayList <Habitacion> habitaciones;
    private HashMap <String, Pasajero> pasajeros;
    /*
     * Preferi optar por hasmMap para incorporar los datos desde un txt. en vez de una base de datos.
     * Mucho lio sino. 
     */
    private Scanner teclado;

    public Hotel(String nombre, double cajaInicial) {
        this.nombre = nombre;
        this.montoActualCaja = cajaInicial;
        this.cajaInicial = cajaInicial;
        this.habitaciones = new ArrayList<>();
        this.pasajeros = new HashMap<>();
        this.teclado = new Scanner(System.in);
    }

    public boolean agregarHabitacion(Habitacion habitacion) {
        for (Habitacion h : habitaciones) {
            if (h.getNumero() == habitacion.getNumero()) {
                System.out.println("Error: La habitación con el número " + habitacion.getNumero() + " ya existe.");
                return false;
            }
        }
        habitaciones.add(habitacion);
        System.out.println("Habitación " + habitacion.getNumero() + " agregada correctamente.");
        return true;
    }

    public Habitacion buscarHabitacion(int numeroHabitacion) {
        for (Habitacion habitacion : habitaciones) {
            if (habitacion.getNumero() == numeroHabitacion) {
                return habitacion;
            }
        }
        return null;
    }

    public boolean actualizarHabitacion(int numeroHabitacion, String nuevaCategoria, String nuevoDetalle, Double nuevoCosto) {
        Habitacion habitacion = buscarHabitacion(numeroHabitacion);
        if (habitacion != null) {
            if (nuevaCategoria != null && !nuevaCategoria.isEmpty()) {
                habitacion.setCategoria(nuevaCategoria);
            }
            if (nuevoDetalle != null && !nuevoDetalle.isEmpty()) {
                habitacion.setDetalle(nuevoDetalle);
            }
            if (nuevoCosto != null) {
                habitacion.setCostoPorNoche(nuevoCosto);
            }
            System.out.println("Habitación " + numeroHabitacion + " actualizada correctamente.");
            return true;
        }
        System.out.println("Error: Habitación " + numeroHabitacion + " no encontrada para actualizar.");
        return false;
    }

    public boolean eliminarHabitacion(int numeroHabitacion) {
        Habitacion habitacion = buscarHabitacion(numeroHabitacion);
        if (habitacion != null) {
            if (habitacion.estaEnUso()) {
                System.out.println("Error: No se puede eliminar la habitación " + numeroHabitacion + " porque está ocupada.");
                return false;
            }
            habitaciones.remove(habitacion);
            System.out.println("Habitación " + numeroHabitacion + " eliminada correctamente.");
            return true;
        }
        System.out.println("Error: Habitación " + numeroHabitacion + " no encontrada para eliminar.");
        return false;
    }

    public boolean agregarPasajero(Pasajero pasajero) {
        if (pasajeros.containsKey(pasajero.getDni())) {
            System.out.println("Error: El pasajero con DNI " + pasajero.getDni() + " ya existe.");
            return false;
        }
        pasajeros.put(pasajero.getDni(), pasajero);
        System.out.println("Pasajero " + pasajero.getNombre() + " (DNI: " + pasajero.getDni() + ") agregado correctamente.");
        return true;
    }

    public Pasajero buscarPasajero(String dniPasajero) {
        return pasajeros.get(dniPasajero);
    }

    public boolean actualizarPasajero(String dniPasajero, String nuevoNombre, String nuevoEmail, String nuevoTelefono) {
        Pasajero pasajero = buscarPasajero(dniPasajero);
        if (pasajero != null) {
            if (nuevoNombre != null && !nuevoNombre.isEmpty()) {
                pasajero.setNombre(nuevoNombre);
            }
            if (nuevoEmail != null && !nuevoEmail.isEmpty()) {
                pasajero.setEmail(nuevoEmail);
            }
            if (nuevoTelefono != null && !nuevoTelefono.isEmpty()) {
                pasajero.setTelefono(nuevoTelefono);
            }
            System.out.println("Pasajero con DNI " + dniPasajero + " actualizado correctamente.");
            return true;
        }
        System.out.println("Error: Pasajero con DNI " + dniPasajero + " no encontrado para actualizar.");
        return false;
    }

    public boolean eliminarPasajero(String dniPasajero) {
        if (pasajeros.containsKey(dniPasajero)) {
            // Verificar si el pasajero está ocupando alguna habitación antes de eliminarlo
            for (Habitacion habitacion : habitaciones) {
                if (dniPasajero.equals(habitacion.getDniPasajeroActual())) {
                    System.out.println("Error: No se puede eliminar el pasajero con DNI " + dniPasajero + " porque está ocupando la habitación " + habitacion.getNumero() + ".");
                    return false;
                }
            }
            pasajeros.remove(dniPasajero);
            System.out.println("Pasajero con DNI " + dniPasajero + " eliminado correctamente.");
            return true;
        }
        System.out.println("Error: Pasajero con DNI " + dniPasajero + " no encontrado para eliminar.");
        return false;
    }

    public boolean asignarHabitacion(String dniPasajero, int numeroHabitacion) {
        Habitacion habitacion = buscarHabitacion(numeroHabitacion);
        Pasajero pasajero = buscarPasajero(dniPasajero);

        if (habitacion == null) {
            System.out.println("Error: Habitación " + numeroHabitacion + " no encontrada.");
            return false;
        }
        if (pasajero == null) {
            System.out.println("Error: Pasajero con DNI " + dniPasajero + " no encontrado.");
            return false;
        }

        if (habitacion.estaEnUso()) {
            // Simular excepción o manejarla de forma controlada
            System.out.println("Excepción: La habitación " + numeroHabitacion + " ya está ocupada por el DNI " + habitacion.getDniPasajeroActual() + ".");
            return false;
        } else {
            LocalDateTime fechaIngreso = LocalDateTime.now();
            habitacion.ocupar(dniPasajero, fechaIngreso);
            System.out.println("Habitación " + numeroHabitacion + " asignada al pasajero " + pasajero.getNombre() + " (DNI: " + dniPasajero + ") el " + fechaIngreso.format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")) + ".");
            return true;
        }
    }

    public boolean liberarHabitacion(int numeroHabitacion, String nombreCajero) {
        Habitacion habitacion = buscarHabitacion(numeroHabitacion);

        if (habitacion == null) {
            System.out.println("Error: Habitación " + numeroHabitacion + " no encontrada.");
            return false;
        }

        if (!habitacion.estaEnUso()) {
            System.out.println("Advertencia: La habitación " + numeroHabitacion + " ya está libre.");
            return false;
        } else {
            LocalDateTime fechaSalida = LocalDateTime.now();
            long diasHospedaje = ChronoUnit.DAYS.between(habitacion.getFechaIngreso(), fechaSalida);
            if (diasHospedaje == 0) { // Si la estadía es menos de un día, cobrar un día
                diasHospedaje = 1;
            }

            double costoTotal = habitacion.getCostoPorNoche() * diasHospedaje;
            this.montoActualCaja += costoTotal;
            System.out.println("Cobro realizado por habitación " + numeroHabitacion + ": $" + String.format("%.2f", costoTotal) + " (" + diasHospedaje + " día(s) a $" + String.format("%.2f", habitacion.getCostoPorNoche()) + "/día).");
            System.out.println("Cajero (" + nombreCajero + ") ha liberado la habitación " + numeroHabitacion + ".");
            System.out.println("Monto actual en caja: $" + String.format("%.2f", this.montoActualCaja));

            habitacion.liberar();
            return true;
        }
    }

    public boolean registrarRetiroHotel(int numeroHabitacion, String nombreCajero) {
        return liberarHabitacion(numeroHabitacion, nombreCajero);
    }

    public void consultarHabitacionesDisponibles() {
        ArrayList<Habitacion> habitacionesLibres = new ArrayList<>();
        for (Habitacion h : habitaciones) {
            if (h.estaLibre()) {
                habitacionesLibres.add(h);
            }
        }

        if (!habitacionesLibres.isEmpty()) {
            System.out.println("\n--- Habitaciones Disponibles ---");
            for (Habitacion h : habitacionesLibres) {
                System.out.println("  - Habitación Nº" + h.getNumero() + " (" + h.getCategoria() + "): " + h.getDetalle() + ", Costo/Noche: $" + String.format("%.2f", h.getCostoPorNoche()));
            }
        } else {
            System.out.println("\nNo hay habitaciones disponibles en este momento.");
        }
    }

    public void listarTodasLasHabitaciones() {
        if (habitaciones.isEmpty()) {
            System.out.println("\nNo hay habitaciones registradas en el hotel.");
            return;
        }
        System.out.println("\n--- Listado de Todas las Habitaciones ---");
        for (Habitacion h : habitaciones) {
            System.out.println(h);
        }
    }

    public void mostrarEstadoCaja() {
        System.out.println("\n--- Estado Actual de Caja ---");
        System.out.println("Monto Inicial de Caja: $" + String.format("%.2f", cajaInicial));
        System.out.println("Monto Actual de Caja: $" + String.format("%.2f", montoActualCaja));
    }

    // Método para cargar datos desde un archivo (simulado)
    public void cargarHabitacionesDesdeArchivo(String nombreArchivo) {
        System.out.println("\nCargando habitaciones desde " + nombreArchivo + "...");
        try (Scanner fileScanner = new Scanner(new File(nombreArchivo))) {
            while (fileScanner.hasNextLine()) {
                String linea = fileScanner.nextLine();
                try {
                    String[] partes = linea.trim().split(",");
                    int numero = Integer.parseInt(partes[0]);
                    String categoria = partes[1];
                    String detalle = partes[2];
                    double costo = Double.parseDouble(partes[3]);
                    Habitacion habitacion = new Habitacion(numero, categoria, detalle, costo);
                    this.agregarHabitacion(habitacion); // Usar el método del hotel para verificar duplicados
                } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                    System.out.println("Error al leer línea del archivo de habitaciones: " + linea.trim() + " - " + e.getMessage());
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: Archivo de habitaciones '" + nombreArchivo + "' no encontrado.");
        }
    }

    public void cargarPasajerosDesdeArchivo(String nombreArchivo) {
        System.out.println("\nCargando pasajeros desde " + nombreArchivo + "...");
        try (Scanner fileScanner = new Scanner(new File(nombreArchivo))) {
            while (fileScanner.hasNextLine()) {
                String linea = fileScanner.nextLine();
                try {
                    String[] partes = linea.trim().split(",");
                    String dni = partes[0];
                    String nombre = partes[1];
                    String email = partes[2];
                    String telefono = partes[3];
                    Pasajero pasajero = new Pasajero(dni, nombre, email, telefono);
                    this.agregarPasajero(pasajero); // Usar el método del hotel para verificar duplicados
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Error al leer línea del archivo de pasajeros: " + linea.trim() + " - " + e.getMessage());
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: Archivo de pasajeros '" + nombreArchivo + "' no encontrado.");
        }
    }


    // Métodos de menú para interacción con el usuario
    public void menuPrincipal() {
        while (true) {
            System.out.println("\n--- Menú Principal del Hotel ---");
            System.out.println("1. Gestión de Habitaciones (Alta, Baja, Modificación)");
            System.out.println("2. Gestión de Pasajeros (Alta, Baja, Modificación)");
            System.out.println("3. Consultar Habitaciones Disponibles");
            System.out.println("4. Listar Todas las Habitaciones");
            System.out.println("5. Asignar Habitación (Check-in)");
            System.out.println("6. Registrar Retiro (Check-out)");
            System.out.println("7. Mostrar Estado de Caja");
            System.out.println("8. Salir");
            System.out.print("Seleccione una opción: ");

            String opcion = teclado.nextLine();

            switch (opcion) {
                case "1":
                    menuGestionHabitaciones();
                    break;
                case "2":
                    menuGestionPasajeros();
                    break;
                case "3":
                    consultarHabitacionesDisponibles();
                    break;
                case "4":
                    listarTodasLasHabitaciones();
                    break;
                case "5":
                    System.out.print("Ingrese DNI del pasajero: ");
                    String dniAsignar = teclado.nextLine();
                    System.out.print("Ingrese número de habitación a asignar: ");
                    try {
                        int numHabitacionAsignar = Integer.parseInt(teclado.nextLine());
                        asignarHabitacion(dniAsignar, numHabitacionAsignar);
                    } catch (NumberFormatException e) {
                        System.out.println("Entrada inválida. Por favor, ingrese un número entero para la habitación.");
                    }
                    break;
                case "6":
                    System.out.print("Ingrese número de habitación a liberar (checkout): ");
                    try {
                        int numHabitacionLiberar = Integer.parseInt(teclado.nextLine());
                        registrarRetiroHotel(numHabitacionLiberar, "Usuario Terminal");
                    } catch (NumberFormatException e) {
                        System.out.println("Entrada inválida. Por favor, ingrese un número entero para la habitación.");
                    }
                    break;
                case "7":
                    mostrarEstadoCaja();
                    break;
                case "8":
                    System.out.println("Saliendo del sistema. ¡Hasta pronto!");
                    teclado.close(); // Cerrar el scanner al salir
                    return;
                default:
                    System.out.println("Opción no válida. Por favor, intente de nuevo.");
            }
        }
    }

    private void menuGestionHabitaciones() {
        while (true) {
            System.out.println("\n--- Gestión de Habitaciones ---");
            System.out.println("a. Alta de Habitación");
            System.out.println("b. Baja de Habitación");
            System.out.println("c. Modificación de Habitación");
            System.out.println("d. Volver al Menú Principal");
            System.out.print("Seleccione una opción: ");

            String opcionHab = teclado.nextLine();

            switch (opcionHab) {
                case "a":
                    try {
                        System.out.print("Ingrese número de habitación: ");
                        int num = Integer.parseInt(teclado.nextLine());
                        System.out.print("Ingrese categoría: ");
                        String cat = teclado.nextLine();
                        System.out.print("Ingrese detalle: ");
                        String det = teclado.nextLine();
                        System.out.print("Ingrese costo por noche: ");
                        double costo = Double.parseDouble(teclado.nextLine());
                        Habitacion nuevaHab = new Habitacion(num, cat, det, costo);
                        agregarHabitacion(nuevaHab);
                    } catch (NumberFormatException e) {
                        System.out.println("Entrada inválida para número o costo. Intente de nuevo.");
                    }
                    break;
                case "b":
                    try {
                        System.out.print("Ingrese número de habitación a eliminar: ");
                        int num = Integer.parseInt(teclado.nextLine());
                        eliminarHabitacion(num);
                    } catch (NumberFormatException e) {
                        System.out.println("Entrada inválida para número. Intente de nuevo.");
                    }
                    break;
                case "c":
                    try {
                        System.out.print("Ingrese número de habitación a modificar: ");
                        int num = Integer.parseInt(teclado.nextLine());
                        System.out.print("Ingrese nueva categoría (dejar vacío para no cambiar): ");
                        String nuevaCat = teclado.nextLine();
                        System.out.print("Ingrese nuevo detalle (dejar vacío para no cambiar): ");
                        String nuevoDet = teclado.nextLine();
                        System.out.print("Ingrese nuevo costo por noche (dejar vacío para no cambiar): ");
                        String costoStr = teclado.nextLine();
                        Double nuevoCosto = costoStr.isEmpty() ? null : Double.parseDouble(costoStr);
                        actualizarHabitacion(num, nuevaCat.isEmpty() ? null : nuevaCat, nuevoDet.isEmpty() ? null : nuevoDet, nuevoCosto);
                    } catch (NumberFormatException e) {
                        System.out.println("Entrada inválida para número o costo. Intente de nuevo.");
                    }
                    break;
                case "d":
                    return; // Volver al menú principal
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }

    private void menuGestionPasajeros() {
        while (true) {
            System.out.println("\n--- Gestión de Pasajeros ---");
            System.out.println("a. Alta de Pasajero");
            System.out.println("b. Baja de Pasajero");
            System.out.println("c. Modificación de Pasajero");
            System.out.println("d. Volver al Menú Principal");
            System.out.print("Seleccione una opción: ");

            String opcionPas = teclado.nextLine();

            switch (opcionPas) {
                case "a":
                    System.out.print("Ingrese DNI del pasajero: ");
                    String dni = teclado.nextLine();
                    System.out.print("Ingrese nombre del pasajero: ");
                    String nombre = teclado.nextLine();
                    System.out.print("Ingrese email del pasajero: ");
                    String email = teclado.nextLine();
                    System.out.print("Ingrese teléfono del pasajero: ");
                    String telefono = teclado.nextLine();
                    Pasajero nuevoPas = new Pasajero(dni, nombre, email, telefono);
                    agregarPasajero(nuevoPas);
                    break;
                case "b":
                    System.out.print("Ingrese DNI del pasajero a eliminar: ");
                    String dniEliminar = teclado.nextLine();
                    eliminarPasajero(dniEliminar);
                    break;
                case "c":
                    System.out.print("Ingrese DNI del pasajero a modificar: ");
                    String dniModificar = teclado.nextLine();
                    System.out.print("Ingrese nuevo nombre (dejar vacío para no cambiar): ");
                    String nuevoNombre = teclado.nextLine();
                    System.out.print("Ingrese nuevo email (dejar vacío para no cambiar): ");
                    String nuevoEmail = teclado.nextLine();
                    System.out.print("Ingrese nuevo teléfono (dejar vacío para no cambiar): ");
                    String nuevoTelefono = teclado.nextLine();
                    actualizarPasajero(dniModificar, nuevoNombre.isEmpty() ? null : nuevoNombre, nuevoEmail.isEmpty() ? null : nuevoEmail, nuevoTelefono.isEmpty() ? null : nuevoTelefono);
                    break;
                case "d":
                    return; // Volver al menú principal
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }
}