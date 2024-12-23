package src.booking.Utils;

import src.booking.Models.Alojamiento;
import src.booking.Models.Habitacion;
import src.booking.Models.DiaDeSol;
import src.booking.Models.Hotel;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class AlojamientoOperacionesUtils {

    public static void mostrarHotelesConDiaDeSol(List<Alojamiento> alojamientos, String ciudad) {
        List<Hotel> diaDeSolHoteles = DiaDeSol.buscarHotelesConDiaDeSol(alojamientos, ciudad);
        if (diaDeSolHoteles.isEmpty()) {
            System.out.println("No hay hoteles que ofrezcan DiaDeSol.");
        } else {
            System.out.println("Hoteles que ofrecen DiaDeSol:");
            displayDiaDeSol(diaDeSolHoteles);
        }
    }

    private static void displayDiaDeSol(List<Hotel> diaDeSolHoteles) {
        for (Hotel hotel : diaDeSolHoteles) {
            DiaDeSol diaDeSol = hotel.getDiaDeSol();
            System.out.println("Nombre: " + hotel.getNombre()
                    + "\nUbicación: " + diaDeSol.getUbicacion()
                    + "\nPrecio: " + diaDeSol.getCostoPorPersona()
                    +"Actividades: " + diaDeSol.getActividades());
        }
    }

    public static void mostrarAlojamientos(List<Alojamiento> alojamientos) {
        for (Alojamiento alojamiento : alojamientos) {
            System.out.println(alojamiento);
        }
    }

    public static Alojamiento seleccionarAlojamiento(Scanner scanner, List<Alojamiento> alojamientos) {
        System.out.println("Seleccione un alojamiento:");
        for (int i = 0; i < alojamientos.size(); i++) {
            System.out.println((i + 1) + ". " + alojamientos.get(i).getNombre() + " (" + alojamientos.get(i).getClass().getSimpleName() + ")");
        }
        Integer alojamientoIndex = getInteger(scanner, alojamientos);

        return alojamientos.get(alojamientoIndex);
    }

    private static Integer getInteger(Scanner scanner, List<Alojamiento> alojamientos) {
        int alojamientoIndex = scanner.nextInt() - 1;
        scanner.nextLine();
        return alojamientoIndex;
    }


    public static List<Habitacion> obtenerHabitacionesDisponibles(Alojamiento alojamiento, LocalDate[] fechas) {
        return alojamiento.getHabitaciones().stream()
                .filter(h -> h.getReservas().stream().noneMatch(r -> r.getEntrada().isBefore(fechas[1]) && r.getSalida().isAfter(fechas[0])))
                .collect(Collectors.toList());
    }

    public static void mostrarHabitacionesDisponibles(List<Habitacion> habitacionesDisponibles) {
        if (habitacionesDisponibles.isEmpty()) {
            System.out.println("No hay habitaciones disponibles para las fechas seleccionadas.");
        } else {
            System.out.println("Habitaciones disponibles:");
            for (Habitacion habitacion : habitacionesDisponibles) {
                System.out.println(habitacion);
            }
        }
    }
}