package src.booking.Repository;

import src.booking.Interface.IReservaRepository;
import src.booking.Models.Reserva;
import java.util.List;
import java.util.ArrayList;

public class ReservaRepository implements IReservaRepository {
    private static ReservaRepository instance;
    private List<Reserva> reservas;

    private ReservaRepository() {
        reservas = new ArrayList<>();
    }

    public static synchronized ReservaRepository getInstance() {
        if (instance == null) {
            instance = new ReservaRepository();
        }
        return instance;
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
    public void setReservas(List<Reserva> reservas) {
        this.reservas = reservas;
    }

    @Override
    public List<Reserva> encontrarReservasPorEmail(String email) {
        List<Reserva> result = new ArrayList<>();
        for (Reserva reserva : reservas) {
            if (reserva.getCliente().getEmail().equalsIgnoreCase(email)) {
                result.add(reserva);
            }
        }
        return result;
    }
}