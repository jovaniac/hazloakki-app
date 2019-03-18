package com.herprogramacion.hazloakki.modelo;

import com.herprogramacion.hazloakki.ui.AdaptadorDirecciones;

import java.util.ArrayList;
import java.util.List;

public class NegocioInfoServicios extends RecyclerViewItem{

    private String nombre;
    private String descripcion;

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
}
