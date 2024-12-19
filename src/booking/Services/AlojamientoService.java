package src.booking.Services;

import booking.Models.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class AlojamientoService {
    public List<Alojamiento> buscarAlojamientos(String ciudad, String tipoAlojamiento, LocalDate inicio, LocalDate fin, int cantAdultos, int cantNinos, int cantHabitaciones) {
        List<Alojamiento> alojamientos = SeedData.createAlojamientos();
        List<Alojamiento> resultados = new ArrayList<>();

        for (Alojamiento alojamiento : alojamientos) {
            if (alojamiento.getCiudad().equalsIgnoreCase(ciudad) && alojamiento.getClass().getSimpleName().equalsIgnoreCase(tipoAlojamiento)) {
                int[] dias = inicio.datesUntil(fin).mapToInt(LocalDate::getDayOfMonth).toArray();
                double costo = alojamiento.calcularCosto(dias, cantHabitaciones);
                resultados.add(alojamiento);
                System.out.println("Nombre: " + alojamiento.getNombre() + ", Calificación: " + alojamiento.getEstrellas() + ", Precio calculado: " + costo);
            }
        }

        return resultados;
    }

    public void buscarHotel(Scanner scanner) {
        List<Alojamiento> alojamientos = SeedData.createAlojamientos();
        List<String> ciudades = alojamientos.stream().map(Alojamiento::getCiudad).distinct().collect(Collectors.toList());
        List<String> tiposAlojamiento = List.of("Hotel", "Apartamento", "Finca", "DiaDeSol");

        System.out.println("Seleccione una ciudad:");
        for (int i = 0; i < ciudades.size(); i++) {
            System.out.println((i + 1) + ". " + ciudades.get(i));
        }
        int ciudadIndex = scanner.nextInt() - 1;
        scanner.nextLine(); // Consume newline
        if (ciudadIndex < 0 || ciudadIndex >= ciudades.size()) {
            System.out.println("Opción no válida.");
            return;
        }
        String ciudad = ciudades.get(ciudadIndex);

        System.out.println("Seleccione el tipo de alojamiento:");
        for (int i = 0; i < tiposAlojamiento.size(); i++) {
            System.out.println((i + 1) + ". " + tiposAlojamiento.get(i));
        }
        int tipoIndex = scanner.nextInt() - 1;
        scanner.nextLine(); // Consume newline
        if (tipoIndex < 0 || tipoIndex >= tiposAlojamiento.size()) {
            System.out.println("Opción no válida.");
            return;
        }
        String tipoAlojamiento = tiposAlojamiento.get(tipoIndex);

        if (tipoAlojamiento.equalsIgnoreCase("DiaDeSol")) {
            List<Hotel> diaDeSolHoteles = DiaDeSol.buscarHotelesConDiaDeSol(alojamientos, ciudad);

            if (diaDeSolHoteles.isEmpty()) {
                System.out.println("No hay hoteles que ofrezcan DiaDeSol.");
            } else {
                System.out.println("Hoteles que ofrecen DiaDeSol:");
                for (Hotel hotel : diaDeSolHoteles) {
                    DiaDeSol diaDeSol = hotel.getDiaDeSol();
                    System.out.println("Nombre: " + hotel.getNombre());
                    System.out.println("Ubicación: " + diaDeSol.getUbicacion());
                    System.out.println("Precio: " + diaDeSol.getCostoPorPersona());
                    System.out.println("Actividades: " + diaDeSol.getActividades());
                    System.out.println();
                }
            }
            return;
        }

        LocalDate inicio = null;
        LocalDate fin = null;
        while (true) {
            try {
                System.out.println("Ingrese la fecha de inicio (YYYY-MM-DD):");
                inicio = LocalDate.parse(scanner.nextLine());
                System.out.println("Ingrese la fecha de fin (YYYY-MM-DD):");
                fin = LocalDate.parse(scanner.nextLine());
                if (fin.isBefore(inicio)) {
                    System.out.println("La fecha de fin no puede ser anterior a la fecha de inicio. Intente de nuevo.");
                } else {
                    break;
                }
            } catch (DateTimeParseException e) {
                System.out.println("Formato de fecha no válido. Intente de nuevo.");
            }
        }

        System.out.println("Ingrese la cantidad de adultos:");
        int cantAdultos = scanner.nextInt();
        System.out.println("Ingrese la cantidad de niños:");
        int cantNinos = scanner.nextInt();
        System.out.println("Ingrese la cantidad de habitaciones:");
        int cantHabitaciones = scanner.nextInt();

        List<Alojamiento> resultados = buscarAlojamientos(ciudad, tipoAlojamiento, inicio, fin, cantAdultos, cantNinos, cantHabitaciones);
        for (Alojamiento alojamiento : resultados) {
            System.out.println(alojamiento);
        }
    }

    public void buscarDisponibilidad(Scanner scanner) {
        List<Alojamiento> alojamientos = SeedData.createAlojamientos();

        if (alojamientos.isEmpty()) {
            System.out.println("No hay alojamientos disponibles.");
            return;
        }

        System.out.println("Seleccione un alojamiento:");
        for (int i = 0; i < alojamientos.size(); i++) {
            System.out.println((i + 1) + ". " + alojamientos.get(i).getNombre() + " (" + alojamientos.get(i).getClass().getSimpleName() + ")");
        }

        int alojamientoIndex = scanner.nextInt() - 1;
        scanner.nextLine(); // Consume newline
        if (alojamientoIndex < 0 || alojamientoIndex >= alojamientos.size()) {
            System.out.println("Opción no válida.");
            return;
        }

        Alojamiento alojamiento = alojamientos.get(alojamientoIndex);

        LocalDate inicio = null;
        LocalDate fin = null;
        while (true) {
            try {
                System.out.println("Ingrese la fecha de inicio (YYYY-MM-DD):");
                inicio = LocalDate.parse(scanner.nextLine());
                System.out.println("Ingrese la fecha de fin (YYYY-MM-DD):");
                fin = LocalDate.parse(scanner.nextLine());
                if (fin.isBefore(inicio)) {
                    System.out.println("La fecha de fin no puede ser anterior a la fecha de inicio. Intente de nuevo.");
                } else {
                    break;
                }
            } catch (DateTimeParseException e) {
                System.out.println("Formato de fecha no válido. Intente de nuevo.");
            }
        }

        System.out.println("Ingrese la cantidad de adultos:");
        int cantAdultos = scanner.nextInt();
        System.out.println("Ingrese la cantidad de niños:");
        int cantNinos = scanner.nextInt();
        System.out.println("Ingrese la cantidad de habitaciones:");
        int cantHabitaciones = scanner.nextInt();

        LocalDate finalInicio = inicio;
        LocalDate finalFin = fin;
        List<Habitacion> habitacionesDisponibles = alojamiento.getHabitaciones().stream()
                .filter(h -> h.getReservas().stream().noneMatch(r -> r.getEntrada().isBefore(finalFin) && r.getSalida().isAfter(finalInicio)))
                .collect(Collectors.toList());

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