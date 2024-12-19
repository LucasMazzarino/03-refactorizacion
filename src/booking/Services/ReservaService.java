package src.booking.Services;

import src.booking.Models.Alojamiento;
import src.booking.Models.Cliente;
import src.booking.Models.Habitacion;
import src.booking.Models.Reserva;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ReservaService extends ReservaControler {
    private List<Reserva> reservas;

    public ReservaService(List<Alojamiento> alojamientos) {
        super(alojamientos);
        this.reservas = new ArrayList<>();
    }

    @Override
    public void crearReserva(Scanner scanner, AlojamientoService alojamientoService) {
        if (alojamientos.isEmpty()) {
            System.out.println("No hay alojamientos disponibles.");
            return;
        }

        Alojamiento alojamiento = seleccionarAlojamiento(scanner);
        if (alojamiento == null) return;

        Cliente cliente = obtenerDatosCliente(scanner);
        LocalDate[] fechas = obtenerFechas(scanner);
        int[] cantidades = obtenerCantidades(scanner);

        Habitacion habitacion = seleccionarHabitacion(scanner, alojamiento, cantidades[2]);
        if (habitacion == null) return;

        System.out.println("Ingrese su hora aproximada de llegada:");
        String horaLlegada = scanner.nextLine();

        Reserva reserva = new Reserva(cliente, habitacion, alojamiento, fechas[0], fechas[1], horaLlegada, cantidades[1], cantidades[0], cantidades[2]);
        reservas.add(reserva);
        habitacion.setCantidadDisponible(habitacion.getCantidadDisponible() - cantidades[2]);

        System.out.println("Se ha realizado la reserva con éxito.");
    }

    public void actualizarReserva(Scanner scanner, AlojamientoService alojamientoService) {
        System.out.println("Seleccione una opción:");
        System.out.println("1. Actualizar Habitación");
        System.out.println("2. Cambiar de Hotel");

        int opcion = scanner.nextInt();
        scanner.nextLine();

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
        scanner.nextLine();
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

        Alojamiento nuevoAlojamiento = seleccionarAlojamiento(scanner);
        if (nuevoAlojamiento == null) return;

        LocalDate[] nuevasFechas = obtenerFechas(scanner);
        Habitacion nuevaHabitacion = seleccionarHabitacion(scanner, nuevoAlojamiento, reserva.getCantHabitaciones());
        if (nuevaHabitacion == null) return;

        reserva.getHabitacion().setCantidadDisponible(reserva.getHabitacion().getCantidadDisponible() + reserva.getCantHabitaciones());
        nuevaHabitacion.setCantidadDisponible(nuevaHabitacion.getCantidadDisponible() - reserva.getCantHabitaciones());

        reserva.setAlojamiento(nuevoAlojamiento);
        reserva.setEntrada(nuevasFechas[0]);
        reserva.setSalida(nuevasFechas[1]);
        reserva.setHabitacion(nuevaHabitacion);

        System.out.println("Se ha cambiado el hotel con éxito.");
    }
}