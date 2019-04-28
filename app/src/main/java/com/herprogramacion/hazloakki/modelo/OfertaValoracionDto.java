package com.herprogramacion.hazloakki.modelo;

public class OfertaValoracionDto extends RecyclerViewItem{
    private String calificacion;
    private String vistas;
    private String interesadosTiempoReal;


    public String getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(String calificacion) {
        this.calificacion = calificacion;
    }

    public String getVistas() {
        return vistas;
    }

    public void setVistas(String vistas) {
        this.vistas = vistas;
    }

    public String getInteresadosTiempoReal() {
        return interesadosTiempoReal;
    }

    public void setInteresadosTiempoReal(String interesadosTiempoReal) {
        this.interesadosTiempoReal = interesadosTiempoReal;
    }
}
