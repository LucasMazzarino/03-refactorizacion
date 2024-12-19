package src.booking.Services;

import src.booking.Models.*;
import java.util.ArrayList;
import java.util.List;

public class SeedData {
    public static List<Alojamiento> createAlojamientos() {
        List<Alojamiento> alojamientos = new ArrayList<>();

        DiaDeSol diaDeSol1 = new DiaDeSol("Montevideo", 50, "Piscina, Playa, Deportes");
        DiaDeSol diaDeSol2 = new DiaDeSol("Maldonado", 60, "Piscina, Playa, Spa");
        DiaDeSol diaDeSol3 = new DiaDeSol("Rocha", 40, "Playa, Deportes, Excursiones");

        Hotel hotel1 = new Hotel("Hotel Montevideo", "Detalles del Hotel Montevideo", "Montevideo", 4.5, diaDeSol1);
        Hotel hotel2 = new Hotel("Hotel Maldonado", "Detalles del Hotel Maldonado", "Maldonado", 4.0, diaDeSol2);
        Hotel hotel3 = new Hotel("Hotel Rocha", "Detalles del Hotel Rocha", "Rocha", 3.5, diaDeSol3);

        Finca finca1 = new Finca("Finca Montevideo", "Detalles de la Finca A", "Montevideo", 4.5);
        Finca finca2 = new Finca("Finca Maldonado", "Detalles de la Finca B", "Maldonado", 4.0);
        Finca finca3 = new Finca("Finca Rocha", "Detalles de la Finca C", "Rocha", 3.5);


        Apartamento apartamento1 = new Apartamento("Apartamento Monteveideo", "Detalles del Apartamento A", "Montevideo", 4.5);
        Apartamento apartamento2 = new Apartamento("Apartamento Maldonado", "Detalles del Apartamento B", "Maldonado", 4.0);
        Apartamento apartamento3 = new Apartamento("Apartamento Rocha", "Detalles del Apartamento C", "Rocha", 3.5);

        addHabitaciones(hotel1);
        addHabitaciones(hotel2);
        addHabitaciones(hotel3);
        addHabitaciones(finca1);
        addHabitaciones(finca2);
        addHabitaciones(finca3);
        addHabitaciones(apartamento1);
        addHabitaciones(apartamento2);
        addHabitaciones(apartamento3);

        alojamientos.add(hotel1);
        alojamientos.add(hotel2);
        alojamientos.add(hotel3);
        alojamientos.add(finca1);
        alojamientos.add(finca2);
        alojamientos.add(finca3);
        alojamientos.add(apartamento1);
        alojamientos.add(apartamento2);
        alojamientos.add(apartamento3);

        return alojamientos;
    }

    private static void addHabitaciones(Alojamiento alojamiento) {
        alojamiento.getHabitaciones().add(new Habitacion(TipoHabitacion.COMUN, 100, "Habitación común", 30, 2, 2, 10));
        alojamiento.getHabitaciones().add(new Habitacion(TipoHabitacion.DOBLE, 150, "Habitación doble", 30, 2, 2, 15));
        alojamiento.getHabitaciones().add(new Habitacion(TipoHabitacion.PREMIUM, 200, "Habitación premium", 30, 2, 2, 20));
        alojamiento.getHabitaciones().add(new Habitacion(TipoHabitacion.ESTRELLA, 250, "Habitación estrella", 30, 2, 2, 20));
        alojamiento.getHabitaciones().add(new Habitacion(TipoHabitacion.SUIT, 300, "Habitación suit", 30, 2, 2, 5));
    }
}