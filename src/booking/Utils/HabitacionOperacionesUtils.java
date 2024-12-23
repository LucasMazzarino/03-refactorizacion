package src.booking.Utils;

import src.booking.Models.Alojamiento;
import src.booking.Models.Habitacion;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class HabitacionOperacionesUtils {

    public static List<Habitacion> obtenerHabitacionesDisponibles(Alojamiento alojamiento) {
        return alojamiento.getHabitaciones().stream()
                .filter(h -> h.getCantidadDisponible() > 0)
                .collect(Collectors.toList());
    }

    public static void mostrarHabitaciones(List<Habitacion> habitacionesDisponibles) {
        System.out.println("Seleccione una habitación:");
        for (int i = 0; i < habitacionesDisponibles.size(); i++) {
            System.out.println((i + 1) + ". " + habitacionesDisponibles.get(i));
        }
    }

    public static Integer obtenerIndiceHabitacion(Scanner scanner, List<Habitacion> habitacionesDisponibles) {
        int habitacionIndex = leerIndice(scanner);
        if (!esIndiceValido(habitacionIndex, habitacionesDisponibles.size())) {
            System.out.println("Opción no válida.");
            return null;
        }
        return habitacionIndex;
    }

    private static int leerIndice(Scanner scanner) {
        return scanner.nextInt() - 1;
    }

    private static boolean esIndiceValido(int indice, int tamano) {
        return indice >= 0 && indice < tamano;
    }
}