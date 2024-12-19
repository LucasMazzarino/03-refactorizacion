package src.booking.Services;

import booking.Interface.IReserva;
import booking.Models.Alojamiento;
import booking.Models.Cliente;
import booking.Models.Habitacion;
import booking.Models.Reserva;
import booking.Services.SeedData;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ReservaService implements IReserva {
    private List<Alojamiento> alojamientos = SeedData.createAlojamientos();
    private List<Reserva> reservas;

    public ReservaService() {
        this.reservas = new ArrayList<>();
    }

    @Override
    public List<Reserva> getReservas() {
        return reservas;
    }

    @Override
    public void addReserva(Reserva reserva) {
        reservas.add(reserva);
    }

    @Override
    public void removeReserva(Reserva reserva) {
        reservas.remove(reserva);
    }

    @Override
    public void crearReserva(Scanner scanner, AlojamientoService alojamientoService) {

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

        Cliente cliente = new Cliente(nombre, apellido, email, nacionalidad, telefono, cumpleanos);

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
        scanner.nextLine(); // Consume newline

        System.out.println("Seleccione una habitación:");
        List<Habitacion> habitacionesDisponibles = alojamiento.getHabitaciones().stream()
                .filter(h -> h.getCantidadDisponible() > 0)
                .collect(Collectors.toList());

        for (int i = 0; i < habitacionesDisponibles.size(); i++) {
            System.out.println((i + 1) + ". " + habitacionesDisponibles.get(i));
        }

        int habitacionIndex = scanner.nextInt() - 1;
        scanner.nextLine(); // Consume newline
        if (habitacionIndex < 0 || habitacionIndex >= habitacionesDisponibles.size()) {
            System.out.println("Opción no válida.");
            return;
        }

        Habitacion habitacion = habitacionesDisponibles.get(habitacionIndex);

        System.out.println("Ingrese su hora aproximada de llegada:");
        String horaLlegada = scanner.nextLine();

        Reserva reserva = new Reserva(cliente, habitacion,alojamiento, inicio, fin, horaLlegada, cantNinos, cantAdultos, cantHabitaciones);
        addReserva(reserva);
        habitacion.setCantidadDisponible(habitacion.getCantidadDisponible() - cantHabitaciones);

        System.out.println("Se ha realizado la reserva con éxito.");
    }

    @Override
    public void actualizarReserva(Scanner scanner, AlojamientoService alojamientoService) {
        System.out.println("Seleccione una opción:");
        System.out.println("1. Actualizar Habitación");
        System.out.println("2. Cambiar de Hotel");

        int opcion = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (opcion) {
            case 1:
                actualizarHabitacion(scanner);
                break;
            case 2:
                cambiarDeHotel(scanner, alojamientoService);
                break;
            default:
                System.out.println("Opción no válida. Intente de nuevo.");
        }
    }

    private void actualizarHabitacion(Scanner scanner) {
        System.out.println("Ingrese su email:");
        String email = scanner.nextLine();
        System.out.println("Ingrese su fecha de cumpleaños (YYYY-MM-DD):");
        LocalDate cumpleanos = LocalDate.parse(scanner.nextLine());

        Reserva reserva = reservas.stream()
                .filter(r -> r.getCliente().getEmail().equals(email) && r.getCliente().getCumpleanos().equals(cumpleanos))
                .findFirst()
                .orElse(null);

        if (reserva == null) {
            System.out.println("Reserva no encontrada o datos incorrectos.");
            return;
        }

        System.out.println("Seleccione la habitación que desea cambiar:");
        System.out.println("1. " + reserva.getHabitacion());

        int habitacionIndex = scanner.nextInt() - 1;
        scanner.nextLine();
        if (habitacionIndex != 0) {
            System.out.println("Opción no válida.");
            return;
        }

        Alojamiento alojamiento = reserva.getAlojamiento();
        List<Habitacion> habitacionesDisponibles = alojamiento.getHabitaciones().stream()
                .filter(h -> h.getCantidadDisponible() > 0)
                .collect(Collectors.toList());

        System.out.println("Seleccione una nueva habitación:");
        for (int i = 0; i < habitacionesDisponibles.size(); i++) {
            System.out.println((i + 1) + ". " + habitacionesDisponibles.get(i));
        }

        int nuevaHabitacionIndex = scanner.nextInt() - 1;
        scanner.nextLine(); // Consume newline
        if (nuevaHabitacionIndex < 0 || nuevaHabitacionIndex >= habitacionesDisponibles.size()) {
            System.out.println("Opción no válida.");
            return;
        }

        Habitacion nuevaHabitacion = habitacionesDisponibles.get(nuevaHabitacionIndex);
        reserva.getHabitacion().setCantidadDisponible(reserva.getHabitacion().getCantidadDisponible() + reserva.getCantHabitaciones());
        nuevaHabitacion.setCantidadDisponible(nuevaHabitacion.getCantidadDisponible() - reserva.getCantHabitaciones());
        reserva.setHabitacion(nuevaHabitacion);

        System.out.println("Se ha actualizado la habitación con éxito.");
    }

    private void cambiarDeHotel(Scanner scanner, AlojamientoService alojamientoService) {
        // Implement the logic for changing the hotel
    }
}
