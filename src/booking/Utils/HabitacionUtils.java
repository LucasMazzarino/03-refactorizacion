package src.booking.Utils;

import src.booking.Models.Alojamiento;
import src.booking.Models.Habitacion;
import src.booking.Models.Reserva;

import java.util.List;
import java.util.Scanner;

public class HabitacionUtils {

    public static Habitacion seleccionarHabitacion(Scanner scanner, Alojamiento alojamiento, int cantHabitaciones) {
        List<Habitacion> habitacionesDisponibles = HabitacionOperacionesUtils.obtenerHabitacionesDisponibles(alojamiento);
        HabitacionOperacionesUtils.mostrarHabitaciones(habitacionesDisponibles);

        Integer habitacionIndex = HabitacionOperacionesUtils.obtenerIndiceHabitacion(scanner, habitacionesDisponibles);
        return habitacionIndex == null ? null : habitacionesDisponibles.get(habitacionIndex);
    }

    public static void actualizarHabitacion(Scanner scanner) {
        String email = ClienteUtils.obtenerEmail(scanner);
        Reserva reserva = obtenerReserva(scanner, email);
        if (reserva == null) return;

        List<Habitacion> habitacionesDisponibles = HabitacionOperacionesUtils.obtenerHabitacionesDisponibles(reserva.getAlojamiento());
        HabitacionOperacionesUtils.mostrarHabitaciones(habitacionesDisponibles);

        Integer nuevaHabitacionIndex = validarHabitacionIndex(scanner, habitacionesDisponibles);
        if (nuevaHabitacionIndex == null) return;

        actualizarReserva(reserva, habitacionesDisponibles.get(nuevaHabitacionIndex));
    }

    private static Integer validarHabitacionIndex(Scanner scanner, List<Habitacion> habitacionesDisponibles) {
        Integer nuevaHabitacionIndex = HabitacionOperacionesUtils.obtenerIndiceHabitacion(scanner, habitacionesDisponibles);
        if (nuevaHabitacionIndex == null) return null;
        return nuevaHabitacionIndex;
    }

    private static Reserva obtenerReserva(Scanner scanner, String email) {
        return ReservaComplementariosUtils.seleccionarReserva(scanner, email);
    }

    private static void actualizarReserva(Reserva reserva, Habitacion nuevaHabitacion) {
        ReservaComplementariosUtils.actualizarReservaConNuevaHabitacion(reserva, nuevaHabitacion);
    }
}