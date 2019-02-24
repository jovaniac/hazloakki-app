package com.herprogramacion.hazloakki.modelo;

/**
 * Created by amary on 25-03-2018.
 */
//Object of Header item
public class NegocioInfoHeader extends RecyclerViewItem{

    private NegocioDto negocioDto;
    private String HeaderText;
    private String Category;
    private String ImageUrl;

    public NegocioInfoHeader(String headerText, String category, String imageUrl, NegocioDto negocioDto) {
        HeaderText = headerText;
        Category = category;
        ImageUrl = imageUrl;
        this.negocioDto = negocioDto;
    }

    public String getHeaderText() {
        return HeaderText;
    }

    public void setHeaderText(String headerText) {
        HeaderText = headerText;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
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
