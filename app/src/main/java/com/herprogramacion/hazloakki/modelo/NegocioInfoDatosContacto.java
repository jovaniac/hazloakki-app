package com.herprogramacion.hazloakki.modelo;

public class NegocioInfoDatosContacto extends RecyclerViewItem{

    private String sitioWeb;
    private String telefonoCelular;
    private String telefonoCel1;
    private String telefonoCel2;
    private String mail;
    private String responsable;

    public String getSitioWeb() {
        return sitioWeb;
    }

    public void setSitioWeb(String sitioWeb) {
        this.sitioWeb = sitioWeb;
    }

    public String getTelefonoCelular() {
        return telefonoCelular;
    }

    public void setTelefonoCelular(String telefonoCelular) {
        this.telefonoCelular = telefonoCelular;
    }

    public String getTelefonoCel1() {
        return telefonoCel1;
    }

    public void setTelefonoCel1(String telefonoCel1) {
        this.telefonoCel1 = telefonoCel1;
    }

    public String getTelefonoCel2() {
        return telefonoCel2;
    }

    public void setTelefonoCel2(String telefonoCel2) {
        this.telefonoCel2 = telefonoCel2;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }
}
