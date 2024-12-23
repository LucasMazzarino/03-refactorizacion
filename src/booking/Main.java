package src.booking;

import src.booking.Models.Alojamiento;
import src.booking.Services.AlojamientoService;
import src.booking.Services.ReservaService;
import src.booking.Services.SeedData;
import src.booking.Utils.MenuUtils;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AlojamientoService alojamientoService = new AlojamientoService();
        List<Alojamiento> alojamientos = SeedData.createAlojamientos();
        ReservaService reservaService = new ReservaService();

        MenuUtils.initializeMenu(alojamientoService, reservaService, alojamientos);

        while (true) {
            MenuUtils.mostrarMenu(scanner);
        }
    }
}