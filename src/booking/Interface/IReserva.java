package src.booking.Interface;


import booking.Models.Reserva;
import booking.Services.AlojamientoService;

import java.util.List;
import java.util.Scanner;

public interface IReserva {
    List<Reserva> getReservas();
    void addReserva(Reserva reserva);
    void removeReserva(Reserva reserva);
    void crearReserva(Scanner scanner, AlojamientoService alojamientoService);
    void actualizarReserva(Scanner scanner, AlojamientoService alojamientoService);
}