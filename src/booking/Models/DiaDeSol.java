package src.booking.Models;

import java.util.ArrayList;
import java.util.List;

public class DiaDeSol {
    private String ubicacion;
    private Double costoPorPersona;
    private String actividades;

    public DiaDeSol(String ubicacion, double costoPorPersona, String actividades) {
        this.ubicacion = ubicacion;
        this.costoPorPersona = costoPorPersona;
        this.actividades = actividades;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public double getCostoPorPersona() {
        return costoPorPersona;
    }

    public String getActividades() {
        return actividades;
    }

    @Override
    public String toString() {
        return "Ubicacion: " + ubicacion + ", Costo por persona: " + costoPorPersona + ", Actividades: " + actividades;
    }

    public static List<Hotel> buscarHotelesConDiaDeSol(List<Alojamiento> alojamientos, String ubicacion) {
        List<Hotel> hotelesConDiaDeSol = new ArrayList<>();
        for (Alojamiento alojamiento : alojamientos) {
            if (alojamiento instanceof Hotel && alojamiento.getCiudad().equalsIgnoreCase(ubicacion)) {
                hotelesConDiaDeSol.add((Hotel) alojamiento);
            }
        }
        return hotelesConDiaDeSol;
    }
}