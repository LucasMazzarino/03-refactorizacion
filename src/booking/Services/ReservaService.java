package src.booking.Services;

import src.booking.Interface.IReserva;
import src.booking.Models.Reserva;
import src.booking.Repository.ReservaRepository;
import src.booking.Utils.ReservaUtils;

import java.util.Scanner;

public class ReservaService implements IReserva {

    public ReservaService() {
    }

    @Override
    public void crearReserva(Scanner scanner, AlojamientoService alojamientoService) {
        Reserva reserva = ReservaUtils.crearReserva(scanner, alojamientoService);
        if (reserva != null) {
            ReservaRepository.getInstance().addReserva(reserva);
            System.out.println("Reserva creada con éxito.");
        }
    }

    @Override
    public void actualizarReserva(Scanner scanner, AlojamientoService alojamientoService) {
        ReservaUtils.actualizarReserva(scanner, alojamientoService);
    }
}