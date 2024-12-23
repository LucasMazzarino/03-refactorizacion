package src.booking.Utils;

import src.booking.Models.Alojamiento;
import src.booking.Models.Habitacion;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class AlojamientoUtils {

    public static void mostrarHotelesConDiaDeSol(List<Alojamiento> alojamientos, String ciudad) {
        AlojamientoOperacionesUtils.mostrarHotelesConDiaDeSol(alojamientos, ciudad);
    }

    public static void mostrarAlojamientos(List<Alojamiento> alojamientos) {
        AlojamientoOperacionesUtils.mostrarAlojamientos(alojamientos);
    }

    public static Alojamiento seleccionarAlojamiento(Scanner scanner, List<Alojamiento> alojamientos) {
        return AlojamientoOperacionesUtils.seleccionarAlojamiento(scanner, alojamientos);
    }

    public static List<Habitacion> obtenerHabitacionesDisponibles(Alojamiento alojamiento, LocalDate[] fechas) {
        return AlojamientoOperacionesUtils.obtenerHabitacionesDisponibles(alojamiento, fechas);
    }

    public static void mostrarHabitacionesDisponibles(List<Habitacion> habitacionesDisponibles) {
        AlojamientoOperacionesUtils.mostrarHabitacionesDisponibles(habitacionesDisponibles);
    }
}