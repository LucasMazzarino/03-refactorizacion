package src.booking.Models;

import java.time.LocalDate;

public class Cliente {
    private String nombre;
    private String apellido;
    private String email;
    private String nacionalidad;
    private String telefono;
    private LocalDate cumpleanos;

    public Cliente(String nombre, String apellido, String email, String nacionalidad, String telefono, LocalDate cumpleanos) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.nacionalidad = nacionalidad;
        this.telefono = telefono;
        this.cumpleanos = cumpleanos;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public LocalDate getCumpleanos() {
        return cumpleanos;
    }
}