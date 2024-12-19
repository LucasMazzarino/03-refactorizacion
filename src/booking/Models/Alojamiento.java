package src.booking.Models;

import booking.Interface.IReserva;
import java.util.ArrayList;
import java.util.List;

public abstract class Alojamiento  {
    private String nombre;
    private String detalles;
    private List<Habitacion> habitaciones;
    private List<Reserva> reservas;
    private String ciudad;
    private double estrellas;

    public Alojamiento(String nombre, String detalles, String ciudad, double estrellas) {
        this.nombre = nombre;
        this.detalles = detalles;
        this.habitaciones = new ArrayList<>();
        this.reservas = new ArrayList<>();
        this.ciudad = ciudad;
        this.estrellas = estrellas;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDetalles() {
        return detalles;
    }

    public void setDetalles(String detalles) {
        this.detalles = detalles;
    }

    public List<Habitacion> getHabitaciones() {
        return habitaciones;
    }

    public void setHabitaciones(List<Habitacion> habitaciones) {
        this.habitaciones = habitaciones;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public double getEstrellas() {
        return estrellas;
    }

    public void setEstrellas(double estrellas) {
        this.estrellas = estrellas;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Nombre de Alojamiento: ").append(nombre).append("\n");
        sb.append("Ubicación: ").append(ciudad).append("\n");
        sb.append("Estrellas: ").append(estrellas).append(" estrellas\n");
        sb.append("Habitaciones:\n");
        for (Habitacion habitacion : habitaciones) {
            sb.append("  - ").append(habitacion).append("\n");
        }
        sb.append("Reservas: ").append(reservas.isEmpty() ? "No hay reservas" : reservas).append("\n");
        return sb.toString();
    }

    public abstract double calcularCosto(int[] dias, int cantHabitaciones);
}