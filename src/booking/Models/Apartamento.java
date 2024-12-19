package src.booking.Models;

public class Apartamento extends Alojamiento {
    public Apartamento(String nombre, String detalles, String ciudad, double estrellas) {
        super(nombre, detalles, ciudad, estrellas);
    }

    @Override
    public double calcularCosto(int[] dias, int cantHabitaciones) {
        double costoTotal = 0;
        double tarifaPorNoche = getHabitaciones().stream()
                .mapToDouble(Habitacion::getTarifaPorNoche)
                .min()
                .orElse(0);

        costoTotal = tarifaPorNoche * dias.length * cantHabitaciones;

        boolean ultimoCincoDias = false;
        boolean diezAlQuince = false;
        boolean cincoAlDiez = false;

        for (int dia : dias) {
            if (dia > 25) {
                ultimoCincoDias = true;
            }
            if (dia >= 10 && dia <= 15) {
                diezAlQuince = true;
            }
            if (dia >= 5 && dia <= 10) {
                cincoAlDiez = true;
            }
        }

        if (ultimoCincoDias) {
            costoTotal *= 1.15;
        } else if (diezAlQuince) {
            costoTotal *= 1.10;
        } else if (cincoAlDiez) {
            costoTotal *= 0.92;
        }

        return costoTotal;
    }
}