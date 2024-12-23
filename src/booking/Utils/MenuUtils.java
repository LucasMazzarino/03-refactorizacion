package src.booking.Utils;

import src.booking.Models.Alojamiento;
import src.booking.Services.AlojamientoService;
import src.booking.Services.ReservaService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MenuUtils {

    private static final Map<Integer, Consumer<Scanner>> menuOptions = new HashMap<>();

    private static void handleBuscarHotel(Scanner scanner, AlojamientoService alojamientoService, List<Alojamiento> alojamientos) {
        String ciudad = seleccionarCiudad(scanner, alojamientos);

        String tipoAlojamiento = seleccionarTipoAlojamiento(scanner);

        alojamientoService.buscarHotel(scanner, ciudad, tipoAlojamiento);
    }

    public static void initializeMenu(AlojamientoService alojamientoService, ReservaService reservaService, List<Alojamiento> alojamientos) {
        menuOptions.put(1, scanner -> handleBuscarHotel(scanner, alojamientoService, alojamientos));
        menuOptions.put(2, alojamientoService::buscarDisponibilidad);
        menuOptions.put(3, scanner -> reservaService.crearReserva(scanner, alojamientoService));
        menuOptions.put(4, scanner -> reservaService.actualizarReserva(scanner, alojamientoService));
        menuOptions.put(5, scanner -> {
            System.out.println("Saliendo...");
            System.exit(0);
        });
    }

    public static void mostrarMenu(Scanner scanner) {
        System.out.println("Seleccione una opción:");
        System.out.println("1. Buscar Hotel");
        System.out.println("2. Buscar Disponibilidad");
        System.out.println("3. Realizar Reserva");
        System.out.println("4. Actualizar Reserva");
        System.out.println("5. Salir");

        int opcion = scanner.nextInt();
        scanner.nextLine();

        Consumer<Scanner> action = menuOptions.get(opcion);
        if (action != null) {
            action.accept(scanner);
        } else {
            System.out.println("Opción no válida. Intente de nuevo.");
        }
    }

    private static String seleccionarCiudad(Scanner scanner, List<Alojamiento> alojamientos) {
        List<String> ciudades = alojamientos.stream().map(Alojamiento::getCiudad).distinct().collect(Collectors.toList());
        System.out.println("Seleccione una ciudad:");
        IntStream.range(0, ciudades.size()).forEach(i -> System.out.println((i + 1) + ". " + ciudades.get(i)));
        int ciudadIndex = scanner.nextInt() - 1;
        scanner.nextLine();
        return ciudades.get(ciudadIndex);
    }

    private static String seleccionarTipoAlojamiento(Scanner scanner) {
        List<String> tiposAlojamiento = List.of("Hotel", "Apartamento", "Finca", "DiaDeSol");
        System.out.println("Seleccione el tipo de alojamiento:");
        IntStream.range(0, tiposAlojamiento.size()).forEach(i -> System.out.println((i + 1) + ". " + tiposAlojamiento.get(i)));
        int tipoIndex = scanner.nextInt() - 1;
        scanner.nextLine();
        return tiposAlojamiento.get(tipoIndex);
    }

}