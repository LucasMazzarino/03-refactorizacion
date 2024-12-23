package src.booking.Utils;

import src.booking.Models.Cliente;
import src.booking.Repository.ClienteRepository;

import java.time.LocalDate;
import java.util.Scanner;

public class ClienteUtils {

    public static Cliente obtenerDatosCliente(Scanner scanner) {
        System.out.println("Ingrese su email:");
        String email = scanner.nextLine();

        Cliente cliente = ClienteRepository.getInstance().findClienteByEmail(email);
        if (cliente != null) {
            return cliente;
        }

        System.out.println("Ingrese su nombre:");
        String nombre = scanner.nextLine();
        System.out.println("Ingrese su apellido:");
        String apellido = scanner.nextLine();
        System.out.println("Ingrese su nacionalidad:");
        String nacionalidad = scanner.nextLine();
        System.out.println("Ingrese su número de teléfono:");
        String telefono = scanner.nextLine();
        System.out.println("Ingrese su fecha de cumpleaños (YYYY-MM-DD):");
        LocalDate cumpleanos = LocalDate.parse(scanner.nextLine());

        cliente = new Cliente(nombre, apellido, email, nacionalidad, telefono, cumpleanos);
        ClienteRepository.getInstance().addCliente(cliente);
        return cliente;
    }

    public static int[] obtenerCantidades(Scanner scanner) {
        System.out.println("Ingrese la cantidad de adultos:");
        int cantAdultos = scanner.nextInt();
        System.out.println("Ingrese la cantidad de niños:");
        int cantNinos = scanner.nextInt();
        System.out.println("Ingrese la cantidad de habitaciones:");
        int cantHabitaciones = scanner.nextInt();
        scanner.nextLine();
        return new int[]{cantAdultos, cantNinos, cantHabitaciones};
    }

    public static String obtenerEmail(Scanner scanner) {
        System.out.println("Ingrese su email:");
        return scanner.nextLine();
    }

    public static LocalDate obtenerFechaCumpleanos(Scanner scanner) {
        System.out.println("Ingrese su fecha de cumpleaños (YYYY-MM-DD):");
        return LocalDate.parse(scanner.nextLine());
    }
}