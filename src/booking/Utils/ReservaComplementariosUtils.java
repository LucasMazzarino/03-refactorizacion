package src.booking.Utils;

import src.booking.Models.Cliente;
import src.booking.Models.Habitacion;
import src.booking.Models.Reserva;
import src.booking.Repository.ReservaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class ReservaComplementariosUtils {

    public static Reserva seleccionarReserva(Scanner scanner, String email) {
        List<Reserva> reservas = ReservaRepository.getInstance().encontrarReservasPorEmail(email);
        if (reservas.isEmpty()) {
            System.out.println("No se encontraron reservas para el email proporcionado.");
            return null;
        }

        seleccionarReserva(reservas);

        return mostrarReservas(scanner, reservas);
    }

    private static Reserva mostrarReservas(Scanner scanner, List<Reserva> reservas) {
        int reservaIndex = scanner.nextInt() - 1;
        scanner.nextLine();
        return reservas.get(reservaIndex);
    }

    private static void seleccionarReserva(List<Reserva> reservas) {
        System.out.println("Seleccione una reserva:");
        for (int i = 0; i < reservas.size(); i++) {
            System.out.println((i + 1) + ". " + reservas.get(i));
        }
    }

    public static void actualizarReservaConNuevaHabitacion(Reserva reserva, Habitacion nuevaHabitacion) {
        Habitacion habitacionActual = reserva.getHabitacion();
        habitacionActual.setCantidadDisponible(habitacionActual.getCantidadDisponible() + reserva.getCantHabitaciones());
        nuevaHabitacion.setCantidadDisponible(nuevaHabitacion.getCantidadDisponible() - reserva.getCantHabitaciones());
        reserva.setHabitacion(nuevaHabitacion);
        System.out.println("Se ha actualizado la habitación con éxito.");
    }
}