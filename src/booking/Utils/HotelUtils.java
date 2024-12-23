package src.booking.Utils;

import src.booking.Models.Alojamiento;
import src.booking.Models.Habitacion;
import src.booking.Models.Reserva;

import java.time.LocalDate;
import java.util.Scanner;

public class HotelUtils {

    public static void cambiarDeHotel(Scanner scanner) {
        String email = HotelOperacionesUtils.obtenerEmail(scanner);
        LocalDate cumpleanos = HotelOperacionesUtils.obtenerFechaCumpleanos(scanner);

        Reserva reserva = HotelOperacionesUtils.buscarReserva(email, cumpleanos);
        if (reserva == null) {
            HotelOperacionesUtils.mostrarMensajeReservaNoEncontrada();
            return;
        }

        Alojamiento nuevoAlojamiento = HotelOperacionesUtils.seleccionarNuevoAlojamiento(scanner);

        LocalDate[] nuevasFechas = HotelOperacionesUtils.obtenerNuevasFechas(scanner);
        Habitacion nuevaHabitacion = HotelOperacionesUtils.seleccionarNuevaHabitacion(scanner, nuevoAlojamiento, reserva.getCantHabitaciones());

        HotelOperacionesUtils.actualizarReserva(reserva, nuevaHabitacion, nuevasFechas);
        HotelOperacionesUtils.mostrarMensajeCambioExitoso();
    }
}