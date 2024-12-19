package src.booking.Interface;


import src.booking.Services.AlojamientoService;

import java.util.Scanner;

public interface IReserva {
    void crearReserva(Scanner scanner, AlojamientoService alojamientoService);
    void actualizarReserva(Scanner scanner, AlojamientoService alojamientoService);
}