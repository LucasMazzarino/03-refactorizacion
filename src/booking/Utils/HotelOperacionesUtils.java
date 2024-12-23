package src.booking.Utils;

import src.booking.Models.Alojamiento;
import src.booking.Models.Habitacion;
import src.booking.Models.Reserva;
import src.booking.Repository.AlojamientoRepository;
import src.booking.Repository.ReservaRepository;

import java.time.LocalDate;
import java.util.Scanner;

public class HotelOperacionesUtils {

    public static String obtenerEmail(Scanner scanner) {
        System.out.println("Ingrese su email:");
        return scanner.nextLine();
    }

    public static LocalDate obtenerFechaCumpleanos(Scanner scanner) {
        System.out.println("Ingrese su fecha de cumpleaños (YYYY-MM-DD):");
        return LocalDate.parse(scanner.nextLine());
    }

    public static Reserva buscarReserva(String email, LocalDate cumpleanos) {
        return ReservaRepository.getInstance().getReservas().stream()
                .filter(r -> r.getCliente().getEmail().equals(email) && r.getCliente().getCumpleanos().equals(cumpleanos))
                .findFirst()
                .orElse(null);
    }

    public static void mostrarMensajeReservaNoEncontrada() {
        System.out.println("Reserva no encontrada o datos incorrectos.");
    }

    public static Alojamiento seleccionarNuevoAlojamiento(Scanner scanner) {
        return AlojamientoUtils.seleccionarAlojamiento(scanner, AlojamientoRepository.getInstancia().getAlojamientos());
    }

    public static LocalDate[] obtenerNuevasFechas(Scanner scanner) {
        return FechasUtils.obtenerFechas(scanner);
    }

    public static Habitacion seleccionarNuevaHabitacion(Scanner scanner, Alojamiento nuevoAlojamiento, int cantHabitaciones) {
        return HabitacionUtils.seleccionarHabitacion(scanner, nuevoAlojamiento, cantHabitaciones);
    }

    public static void actualizarReserva(Reserva reserva, Habitacion nuevaHabitacion, LocalDate[] nuevasFechas) {
        actualizarDisponibilidadHabitaciones(reserva, nuevaHabitacion);
        actualizarDatosReserva(reserva, nuevaHabitacion, nuevasFechas);
    }

    private static void actualizarDisponibilidadHabitaciones(Reserva reserva, Habitacion nuevaHabitacion) {
        reserva.getHabitacion().setCantidadDisponible(reserva.getHabitacion().getCantidadDisponible() + reserva.getCantHabitaciones());
        nuevaHabitacion.setCantidadDisponible(nuevaHabitacion.getCantidadDisponible() - reserva.getCantHabitaciones());
    }

    private static void actualizarDatosReserva(Reserva reserva, Habitacion nuevaHabitacion, LocalDate[] nuevasFechas) {
        reserva.setAlojamiento(nuevaHabitacion.getAlojamiento());
        reserva.setEntrada(nuevasFechas[0]);
        reserva.setSalida(nuevasFechas[1]);
        reserva.setHabitacion(nuevaHabitacion);
    }

    public static void mostrarMensajeCambioExitoso() {
        System.out.println("Se ha cambiado el hotel con éxito.");
    }
}