package src.booking.Models;

import java.util.ArrayList;
import java.util.List;

public class Habitacion {
    private TipoHabitacion tipo;
    private Double tarifaPorNoche;
    private String detalles;
    private Boolean[] disponibilidad;
    private Integer cantNinos;
    private Integer cantAdultos;
    private Integer cantidadDisponible;
    private List<Reserva> reservas;

    public Habitacion(TipoHabitacion tipo, double tarifaPorNoche, String detalles, int diasDelMes, int cantNinos, int cantAdultos, int cantidadDisponible) {
        this.tipo = tipo;
        this.tarifaPorNoche = tarifaPorNoche;
        this.detalles = detalles;
        this.disponibilidad = new Boolean[diasDelMes];
        this.cantNinos = cantNinos;
        this.cantAdultos = cantAdultos;
        this.cantidadDisponible = cantidadDisponible;
        this.reservas = new ArrayList<>();
    }

    public double getTarifaPorNoche() {
        return tarifaPorNoche;
    }

    public int getCantidadDisponible() {
        return cantidadDisponible;
    }

    public void setCantidadDisponible(int cantidadDisponible) {
        this.cantidadDisponible = cantidadDisponible;
    }

    public List<Reserva> getReservas() {
        return reservas;
    }

    @Override
    public String toString() {
        return "Tipo: " + tipo + ", Tarifa por noche: " + tarifaPorNoche + ", Detalles: " + detalles + ", Capacidad: " + cantAdultos + " adultos y " + cantNinos + " niños, Cantidad disponible: " + cantidadDisponible;
    }
}