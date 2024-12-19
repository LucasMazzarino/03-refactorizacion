package src.booking.Services;

import src.booking.Models.Alojamiento;
import src.booking.Models.Cliente;
import src.booking.Models.Habitacion;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public abstract class ReservaControler {
    protected List<Alojamiento> alojamientos;

    public ReservaControler(List<Alojamiento> alojamientos) {
        this.alojamientos = alojamientos;
    }

    public abstract void crearReserva(Scanner scanner, AlojamientoService alojamientoService);

    protected Alojamiento seleccionarAlojamiento(Scanner scanner) {
        System.out.println("Seleccione un alojamiento:");
        for (int i = 0; i < alojamientos.size(); i++) {
            System.out.println((i + 1) + ". " + alojamientos.get(i).getNombre() + " (" + alojamientos.get(i).getClass().getSimpleName() + ")");
        }

        int alojamientoIndex = scanner.nextInt() - 1;
        scanner.nextLine();
        if (alojamientoIndex < 0 || alojamientoIndex >= alojamientos.size()) {
            System.out.println("Opción no válida.");
            return null;
        }

        return alojamientos.get(alojamientoIndex);
    }

    protected Cliente obtenerDatosCliente(Scanner scanner) {
        System.out.println("Ingrese su nombre:");
        String nombre = scanner.nextLine();
        System.out.println("Ingrese su apellido:");
        String apellido = scanner.nextLine();
        System.out.println("Ingrese su email:");
        String email = scanner.nextLine();
        System.out.println("Ingrese su nacionalidad:");
        String nacionalidad = scanner.nextLine();
        System.out.println("Ingrese su número de teléfono:");
        String telefono = scanner.nextLine();
        System.out.println("Ingrese su fecha de cumpleaños (YYYY-MM-DD):");
        LocalDate cumpleanos = LocalDate.parse(scanner.nextLine());

        return new Cliente(nombre, apellido, email, nacionalidad, telefono, cumpleanos);
    }

    protected LocalDate[] obtenerFechas(Scanner scanner) {
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
        return new LocalDate[]{inicio, fin};
    }

    protected int[] obtenerCantidades(Scanner scanner) {
        System.out.println("Ingrese la cantidad de adultos:");
        int cantAdultos = scanner.nextInt();
        System.out.println("Ingrese la cantidad de niños:");
        int cantNinos = scanner.nextInt();
        System.out.println("Ingrese la cantidad de habitaciones:");
        int cantHabitaciones = scanner.nextInt();
        scanner.nextLine();
        return new int[]{cantAdultos, cantNinos, cantHabitaciones};
    }

    protected Habitacion seleccionarHabitacion(Scanner scanner, Alojamiento alojamiento, int cantHabitaciones) {
        System.out.println("Seleccione una habitación:");
        List<Habitacion> habitacionesDisponibles = alojamiento.getHabitaciones().stream()
                .filter(h -> h.getCantidadDisponible() > 0)
                .collect(Collectors.toList());

        for (int i = 0; i < habitacionesDisponibles.size(); i++) {
            System.out.println((i + 1) + ". " + habitacionesDisponibles.get(i));
        }

        int habitacionIndex = scanner.nextInt() - 1;
        scanner.nextLine();
        if (habitacionIndex < 0 || habitacionIndex >= habitacionesDisponibles.size()) {
            System.out.println("Opción no válida.");
            return null;
        }

        return habitacionesDisponibles.get(habitacionIndex);
    }
}