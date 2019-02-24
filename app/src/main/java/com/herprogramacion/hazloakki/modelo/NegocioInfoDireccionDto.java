package com.herprogramacion.hazloakki.modelo;

public class NegocioInfoDireccionDto extends RecyclerViewItem{

    private String direccion;
    private String distancia;
    private String horario;
    private String numeroOfertasPublicadas;
    private String colonia;
    private String ImageUrl;

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getDistancia() {
        return distancia;
    }

    public void setDistancia(String distancia) {
        this.distancia = distancia;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getNumeroOfertasPublicadas() {
        return numeroOfertasPublicadas;
    }

    public void setNumeroOfertasPublicadas(String numeroOfertasPublicadas) {
        this.numeroOfertasPublicadas = numeroOfertasPublicadas;
    }

    public String getColonia() {
        return colonia;
    }

    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    public String getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        ImageUrl = imageUrl;
    }
}
