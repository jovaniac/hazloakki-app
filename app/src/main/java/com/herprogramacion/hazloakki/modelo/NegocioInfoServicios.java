package com.herprogramacion.hazloakki.modelo;

import java.util.ArrayList;
import java.util.List;

public class NegocioInfoServicios extends RecyclerViewItem{

    private List<ServiciosDto> serviciosList = new ArrayList<>();
    private List<MetodoPagoDto> metodoPagoList = new ArrayList<>();
    private List<TipoTarjetaDto> tipoTarjetaList = new ArrayList<>();
    private List<HorarioNegocioDto> horarioNegocio = new ArrayList<>();

    public List<ServiciosDto> getServiciosList() {
        return serviciosList;
    }

    public void setServiciosList(List<ServiciosDto> serviciosList) {
        this.serviciosList = serviciosList;
    }

    public List<MetodoPagoDto> getMetodoPagoList() {
        return metodoPagoList;
    }

    public void setMetodoPagoList(List<MetodoPagoDto> metodoPagoList) {
        this.metodoPagoList = metodoPagoList;
    }

    public List<TipoTarjetaDto> getTipoTarjetaList() {
        return tipoTarjetaList;
    }

    public void setTipoTarjetaList(List<TipoTarjetaDto> tipoTarjetaList) {
        this.tipoTarjetaList = tipoTarjetaList;
    }

    public List<HorarioNegocioDto> getHorarioNegocio() {
        return horarioNegocio;
    }

    public void setHorarioNegocio(List<HorarioNegocioDto> horarioNegocio) {
        this.horarioNegocio = horarioNegocio;
    }
}
