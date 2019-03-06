package com.herprogramacion.hazloakki.modelo;

public class NegocioInfoDatosContacto extends RecyclerViewItem{

    private String sitioWeb;
    private String telefonoCelular;
    private String telefonoMovil1;
    private String telefonoMovil2;
    private String mail;

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

    public String getTelefonoMovil1() {
        return telefonoMovil1;
    }

    public void setTelefonoMovil1(String telefonoMovil1) {
        this.telefonoMovil1 = telefonoMovil1;
    }

    public String getTelefonoMovil2() {
        return telefonoMovil2;
    }

    public void setTelefonoMovil2(String telefonoMovil2) {
        this.telefonoMovil2 = telefonoMovil2;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}
