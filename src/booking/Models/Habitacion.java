package src.booking.Models;

import java.util.ArrayList;
import java.util.List;

public class Habitacion {
    private TipoHabitacion tipo;
    private double tarifaPorNoche;
    private String detalles;
    private boolean[] disponibilidad;
    private int cantNinos;
    private int cantAdultos;
    private int cantidadDisponible;
    private List<Reserva> reservas;

    public Habitacion(TipoHabitacion tipo, double tarifaPorNoche, String detalles, int diasDelMes, int cantNinos, int cantAdultos, int cantidadDisponible) {
        this.tipo = tipo;
        this.tarifaPorNoche = tarifaPorNoche;
        this.detalles = detalles;
        this.disponibilidad = new boolean[diasDelMes];
        this.cantNinos = cantNinos;
        this.cantAdultos = cantAdultos;
        this.cantidadDisponible = cantidadDisponible;
        this.reservas = new ArrayList<>();
    }

    public TipoHabitacion getTipo() {
        return tipo;
    }

    public void setTipo(TipoHabitacion tipo) {
        this.tipo = tipo;
    }

    public double getTarifaPorNoche() {
        return tarifaPorNoche;
    }

    public void setTarifaPorNoche(double tarifaPorNoche) {
        this.tarifaPorNoche = tarifaPorNoche;
    }

    public String getDetalles() {
        return detalles;
    }

    public void setDetalles(String detalles) {
        this.detalles = detalles;
    }

    public boolean[] getDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(boolean[] disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    public int getCantNinos() {
        return cantNinos;
    }

    public void setCantNinos(int cantNinos) {
        this.cantNinos = cantNinos;
    }

    public int getCantAdultos() {
        return cantAdultos;
    }

    public void setCantAdultos(int cantAdultos) {
        this.cantAdultos = cantAdultos;
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

    public void setReservas(List<Reserva> reservas) {
        this.reservas = reservas;
    }

    public void addReserva(Reserva reserva) {
        reservas.add(reserva);
    }

    @Override
    public String toString() {
        return "Tipo: " + tipo + ", Tarifa por noche: " + tarifaPorNoche + ", Detalles: " + detalles + ", Capacidad: " + cantAdultos + " adultos y " + cantNinos + " niños, Cantidad disponible: " + cantidadDisponible;
    }
}