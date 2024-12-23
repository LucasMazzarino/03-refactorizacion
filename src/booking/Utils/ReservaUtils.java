package src.booking.Utils;

import src.booking.Models.Alojamiento;
import src.booking.Models.Cliente;
import src.booking.Models.Habitacion;
import src.booking.Models.Reserva;
import src.booking.Repository.AlojamientoRepository;
import src.booking.Services.AlojamientoService;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class ReservaUtils {

    public static Reserva crearReserva(Scanner scanner, AlojamientoService alojamientoService) {
        List<Alojamiento> alojamientos = obtenerAlojamientosDisponibles();
        Alojamiento alojamiento = seleccionarAlojamiento(scanner, alojamientos);
        Cliente cliente = obtenerDatosCliente(scanner);
        LocalDate[] fechas = obtenerFechas(scanner);
        int[] cantidades = obtenerCantidades(scanner);
        Habitacion habitacion = seleccionarHabitacion(scanner, alojamiento, cantidades[2]);
        String horaLlegada = obtenerHoraLlegada(scanner);

        return crearReserva(cliente, habitacion, alojamiento, fechas, horaLlegada, cantidades);
    }

    private static List<Alojamiento> obtenerAlojamientosDisponibles() {
        List<Alojamiento> alojamientos = AlojamientoRepository.getInstancia().getAlojamientos();
        if (alojamientos.isEmpty()) {
            System.out.println("No hay alojamientos disponibles.");
            throw new IllegalStateException("No hay alojamientos disponibles.");
        }
        return alojamientos;
    }

    private static Alojamiento seleccionarAlojamiento(Scanner scanner, List<Alojamiento> alojamientos) {
        Alojamiento alojamiento = AlojamientoUtils.seleccionarAlojamiento(scanner, alojamientos);
        if (alojamiento == null) {
            throw new IllegalArgumentException("Alojamiento no seleccionado.");
        }
        return alojamiento;
    }

    private static Cliente obtenerDatosCliente(Scanner scanner) {
        return ClienteUtils.obtenerDatosCliente(scanner);
    }

    private static LocalDate[] obtenerFechas(Scanner scanner) {
        return FechasUtils.obtenerFechas(scanner);
    }

    private static int[] obtenerCantidades(Scanner scanner) {
        return ClienteUtils.obtenerCantidades(scanner);
    }

    private static Habitacion seleccionarHabitacion(Scanner scanner, Alojamiento alojamiento, int cantidadHabitaciones) {
        Habitacion habitacion = HabitacionUtils.seleccionarHabitacion(scanner, alojamiento, cantidadHabitaciones);
        if (habitacion == null) {
            throw new IllegalArgumentException("Habitación no seleccionada.");
        }
        return habitacion;
    }

    private static String obtenerHoraLlegada(Scanner scanner) {
        System.out.println("Ingrese su hora aproximada de llegada:");
        return scanner.nextLine();
    }

    private static Reserva crearReserva(Cliente cliente, Habitacion habitacion, Alojamiento alojamiento, LocalDate[] fechas, String horaLlegada, int[] cantidades) {
        Reserva reserva = new Reserva(cliente, habitacion, alojamiento, fechas[0], fechas[1], horaLlegada, cantidades[1], cantidades[0], cantidades[2]);
        habitacion.setCantidadDisponible(habitacion.getCantidadDisponible() - cantidades[2]);
        return reserva;
    }

    public static void actualizarReserva(Scanner scanner, AlojamientoService alojamientoService) {
        System.out.println("Seleccione una opción: \n1.Actualizar Habitación\n2. Cambiar de Hotel");

        int opcion = scanner.nextInt();
        scanner.nextLine();

        switch (opcion) {
            case 1:
                HabitacionUtils.actualizarHabitacion(scanner);
                break;
            case 2:
                HotelUtils.cambiarDeHotel(scanner);
                break;
            default:
                System.out.println("Opción no válida. Intente de nuevo.");
        }
    }
}