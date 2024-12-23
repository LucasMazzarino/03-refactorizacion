package src.booking.Models;

public class Finca extends Alojamiento {
    public Finca(String nombre, String detalles, String ciudad, double estrellas) {
        super(nombre, detalles, ciudad, estrellas);
    }

    @Override
    public double calcularCosto(int[] dias, int cantHabitaciones) {
        double tarifaPorNoche = obtenerTarifaMinimaPorNoche();
        double costoTotal = calcularCostoBase(tarifaPorNoche, dias.length, cantHabitaciones);
        double factorAjuste = obtenerFactorAjuste(dias);
        return costoTotal * factorAjuste;
    }

    private double obtenerTarifaMinimaPorNoche() {
        return getHabitaciones().stream()
                .mapToDouble(Habitacion::getTarifaPorNoche)
                .min()
                .orElse(0);
    }

    private double calcularCostoBase(double tarifaPorNoche, int cantidadDias, int cantHabitaciones) {
        return tarifaPorNoche * cantidadDias * cantHabitaciones;
    }

    private double obtenerFactorAjuste(int[] dias) {
        if (esUltimosCincoDias(dias)) {
            return 1.15;
        } else if (esDiezAlQuince(dias)) {
            return 1.10;
        } else if (esCincoAlDiez(dias)) {
            return 0.92;
        }
        return 1.0;
    }

    private boolean esUltimosCincoDias(int[] dias) {
        for (int dia : dias) {
            if (dia > 25) {
                return true;
            }
        }
        return false;
    }

    private boolean esDiezAlQuince(int[] dias) {
        for (int dia : dias) {
            if (dia >= 10 && dia <= 15) {
                return true;
            }
        }
        return false;
    }

    private boolean esCincoAlDiez(int[] dias) {
        for (int dia : dias) {
            if (dia >= 5 && dia <= 10) {
                return true;
            }
        }
        return false;
    }
}