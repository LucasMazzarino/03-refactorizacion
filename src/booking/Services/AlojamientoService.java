package src.booking.Services;

import src.booking.Models.Alojamiento;
import src.booking.Models.Habitacion;
import src.booking.Repository.AlojamientoRepository;
import src.booking.Utils.AlojamientoUtils;
import src.booking.Utils.ClienteUtils;
import src.booking.Utils.FechasUtils;
import src.booking.Utils.HabitacionUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AlojamientoService {

    public void buscarHotel(Scanner scanner, String ciudad, String tipoAlojamiento) {
        List<Alojamiento> alojamientos = AlojamientoRepository.getInstancia().getAlojamientos();

        if (tipoAlojamiento.equalsIgnoreCase("DiaDeSol")) {
            AlojamientoUtils.mostrarHotelesConDiaDeSol(alojamientos, ciudad);
            return;
        }

        LocalDate[] fechas = FechasUtils.obtenerFechas(scanner);
        int[] cantidades = ClienteUtils.obtenerCantidades(scanner);

        List<Alojamiento> resultados = buscarAlojamientos(ciudad, tipoAlojamiento, fechas[0], fechas[1], cantidades[0], cantidades[1], cantidades[2]);
        AlojamientoUtils.mostrarAlojamientos(resultados);
    }

    public void buscarDisponibilidad(Scanner scanner) {
        List<Alojamiento> alojamientos = AlojamientoRepository.getInstancia().getAlojamientos();

        Alojamiento alojamiento = AlojamientoUtils.seleccionarAlojamiento(scanner, alojamientos);
        if (alojamiento == null) return;

        LocalDate[] fechas = FechasUtils.obtenerFechas(scanner);
        List<Habitacion> habitacionesDisponibles = AlojamientoUtils.obtenerHabitacionesDisponibles(alojamiento, fechas);

        AlojamientoUtils.mostrarHabitacionesDisponibles(habitacionesDisponibles);
    }

    private List<Alojamiento> buscarAlojamientos(String ciudad, String tipoAlojamiento, LocalDate inicio, LocalDate fin, int cantAdultos, int cantNinos, int cantHabitaciones) {
        List<Alojamiento> alojamientos = AlojamientoRepository.getInstancia().getAlojamientos();
        List<Alojamiento> resultados = new ArrayList<>();

        for (Alojamiento alojamiento : alojamientos) {
            if (alojamiento.getCiudad().equalsIgnoreCase(ciudad) && alojamiento.getClass().getSimpleName().equalsIgnoreCase(tipoAlojamiento)) {
                int[] dias = inicio.datesUntil(fin).mapToInt(LocalDate::getDayOfMonth).toArray();
                double costo = alojamiento.calcularCosto(dias, cantHabitaciones);
                resultados.add(alojamiento);
                System.out.println("Nombre: " + alojamiento.getNombre() + ", Calificación: " + alojamiento.getEstrellas() + ", Precio calculado: " + costo);
            }
        }

        return resultados;
    }
}