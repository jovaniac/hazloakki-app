package com.herprogramacion.hazloakki.modelo;

/**
 * Created by JAarzate on 19/11/2017.
 */

public class AccionesDto {


    private String idAccion;
    private String nombre;
    private String descripcion;
    private boolean estatus;

    public String getIdAccion() {
        return idAccion;
    }

    public void setIdAccion(String idAccion) {
        this.idAccion = idAccion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isEstatus() {
        return estatus;
    }

    public void setEstatus(boolean estatus) {
        this.estatus = estatus;
    }

    @Override
    public String toString() {
        return "AccionesDto{" +
                "idAccion='" + idAccion + '\'' +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", estatus=" + estatus +
                '}';
    }
}
