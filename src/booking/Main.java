package src.booking;

import src.booking.Services.AlojamientoService;
import src.booking.Services.ReservaService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AlojamientoService alojamientoService = new AlojamientoService();
        ReservaService reservaService = new ReservaService();

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
                    alojamientoService.buscarHotel(scanner);
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
}