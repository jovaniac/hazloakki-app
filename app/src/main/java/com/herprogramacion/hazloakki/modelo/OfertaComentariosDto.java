package com.herprogramacion.hazloakki.modelo;

public class OfertaComentariosDto extends RecyclerViewItem{

    private String usuario;
    private String fechaPublicacion;
    private String comentario;
    private String numeroMeGusta;

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(String fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getNumeroMeGusta() {
        return numeroMeGusta;
    }

    public void setNumeroMeGusta(String numeroMeGusta) {
        this.numeroMeGusta = numeroMeGusta;
    }
}
