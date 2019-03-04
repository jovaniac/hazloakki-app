package com.herprogramacion.hazloakki.modelo;

/**
 * Created by amary on 25-03-2018.
 */
//Object of Header item
public class NegocioInfoHeader extends RecyclerViewItem{

    private NegocioDto negocioDto;
    private String ImageUrl;

    public NegocioInfoHeader(String imageUrl, NegocioDto negocioDto) {
        ImageUrl = imageUrl;
        this.negocioDto = negocioDto;
    }


    public String getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        ImageUrl = imageUrl;
    }

    public NegocioDto getNegocioDto() {
        return negocioDto;
    }

    public void setNegocioDto(NegocioDto negocioDto) {
        this.negocioDto = negocioDto;
    }
}
