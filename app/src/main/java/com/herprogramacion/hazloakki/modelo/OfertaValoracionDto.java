package com.herprogramacion.hazloakki.modelo;

public class OfertaValoracionDto extends RecyclerViewItem{

    private String calificacion;
    private String rating;
    private String vistas;

    public String getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(String calificacion) {
        this.calificacion = calificacion;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getVistas() {
        return vistas;
    }

    public void setVistas(String vistas) {
        this.vistas = vistas;
    }
}
