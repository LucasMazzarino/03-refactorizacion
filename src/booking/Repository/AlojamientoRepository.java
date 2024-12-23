package src.booking.Repository;

import src.booking.Models.Alojamiento;
import src.booking.Services.SeedData;

import java.util.List;

public class AlojamientoRepository {
    private static AlojamientoRepository instance;
    private static List<Alojamiento> alojamientos;

    private AlojamientoRepository() {
        alojamientos = SeedData.createAlojamientos();
    }

    public static synchronized AlojamientoRepository getInstancia() {
        if (instance == null) {
            instance = new AlojamientoRepository();
        }
        return instance;
    }

    public List<Alojamiento> getAlojamientos() {
        return alojamientos;
    }

}