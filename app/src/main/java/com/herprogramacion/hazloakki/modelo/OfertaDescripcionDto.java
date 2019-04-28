package com.herprogramacion.hazloakki.modelo;

public class OfertaDescripcionDto extends RecyclerViewItem{

    private String tiempoPublicacion;
    private String descripcionOferta;
    private String imagenPerfil;
    private String imagen[];

    public String getTiempoPublicacion() {
        return tiempoPublicacion;
    }

    public void setTiempoPublicacion(String tiempoPublicacion) {
        this.tiempoPublicacion = tiempoPublicacion;
    }

    public String getDescripcionOferta() {
        return descripcionOferta;
    }

    public void setDescripcionOferta(String descripcionOferta) {
        this.descripcionOferta = descripcionOferta;
    }

    public String getImagenPerfil() {
        return imagenPerfil;
    }

    public void setImagenPerfil(String imagenPerfil) {
        this.imagenPerfil = imagenPerfil;
    }

    public String[] getImagen() {
        return imagen;
    }

    public void setImagen(String[] imagen) {
        this.imagen = imagen;
    }
}
