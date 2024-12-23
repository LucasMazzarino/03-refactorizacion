package src.booking.Interface;

import src.booking.Models.Reserva;
import java.util.List;

public interface IReservaRepository {
    List<Reserva> getReservas();
    void addReserva(Reserva reserva);
    void setReservas(List<Reserva> reservas);
    List<Reserva> encontrarReservasPorEmail(String email);
}