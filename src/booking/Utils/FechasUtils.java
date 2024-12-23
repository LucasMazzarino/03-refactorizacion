package src.booking.Utils;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class FechasUtils {

    public static LocalDate[] obtenerFechas(Scanner scanner) {
        LocalDate inicio = obtenerFecha(scanner, "inicio");
        LocalDate fin = obtenerFecha(scanner, "fin");

        while (fin.isBefore(inicio)) {
            System.out.println("La fecha de fin no puede ser anterior a la fecha de inicio. Intente de nuevo.");
            fin = obtenerFecha(scanner, "fin");
        }

        return new LocalDate[]{inicio, fin};
    }

    public static LocalDate obtenerFecha(Scanner scanner, String tipo) {
        LocalDate fecha = null;
        while (fecha == null) {
            try {
                System.out.println("Ingrese la fecha de " + tipo + " (YYYY-MM-DD):");
                fecha = LocalDate.parse(scanner.nextLine());
            } catch (DateTimeParseException e) {
                System.out.println("Formato de fecha no válido. Intente de nuevo.");
            }
        }
        return fecha;
    }
}