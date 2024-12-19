package src.booking;

import src.booking.Models.Alojamiento;
import src.booking.Services.AlojamientoService;
import src.booking.Services.ReservaService;
import src.booking.Services.SeedData;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AlojamientoService alojamientoService = new AlojamientoService();
        List<Alojamiento> alojamientos = SeedData.createAlojamientos();
        ReservaService reservaService = new ReservaService(alojamientos);

        while (true) {
            System.out.println("Seleccione una opción:");
            System.out.println("1. Buscar Hotel");
            System.out.println("2. Buscar Disponibilidad");
            System.out.println("3. Realizar Reserva");
            System.out.println("4. Actualizar Reserva");
            System.out.println("5. Salir");

            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (opcion) {
                case 1:
                    String ciudad = seleccionarCiudad(scanner, alojamientos);
                    if (ciudad == null) break;

                    String tipoAlojamiento = seleccionarTipoAlojamiento(scanner);
                    if (tipoAlojamiento == null) break;

                    alojamientoService.buscarHotel(scanner, ciudad, tipoAlojamiento);
                    break;
                case 2:
                    alojamientoService.buscarDisponibilidad(scanner);
                    break;
                case 3:
                    reservaService.crearReserva(scanner, alojamientoService);
                    break;
                case 4:
                    reservaService.actualizarReserva(scanner, alojamientoService);
                    break;
                case 5:
                    System.out.println("Saliendo...");
                    return;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        }
    }

    private static String seleccionarCiudad(Scanner scanner, List<Alojamiento> alojamientos) {
        List<String> ciudades = alojamientos.stream().map(Alojamiento::getCiudad).distinct().collect(Collectors.toList());
        System.out.println("Seleccione una ciudad:");
        for (int i = 0; i < ciudades.size(); i++) {
            System.out.println((i + 1) + ". " + ciudades.get(i));
        }
        int ciudadIndex = scanner.nextInt() - 1;
        scanner.nextLine(); // Consume newline
        if (ciudadIndex < 0 || ciudadIndex >= ciudades.size()) {
            System.out.println("Opción no válida.");
            return null;
        }
        return ciudades.get(ciudadIndex);
    }

    private static String seleccionarTipoAlojamiento(Scanner scanner) {
        List<String> tiposAlojamiento = List.of("Hotel", "Apartamento", "Finca", "DiaDeSol");
        System.out.println("Seleccione el tipo de alojamiento:");
        for (int i = 0; i < tiposAlojamiento.size(); i++) {
            System.out.println((i + 1) + ". " + tiposAlojamiento.get(i));
        }
        int tipoIndex = scanner.nextInt() - 1;
        scanner.nextLine(); // Consume newline
        if (tipoIndex < 0 || tipoIndex >= tiposAlojamiento.size()) {
            System.out.println("Opción no válida.");
            return null;
        }
        return tiposAlojamiento.get(tipoIndex);
    }
}