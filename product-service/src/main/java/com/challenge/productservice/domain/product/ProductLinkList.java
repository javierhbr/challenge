
package com.challenge.productservice.domain.product;

import com.fasterxml.jackson.annotation.JsonProperty;


public class ProductLinkList {

    @JsonProperty("type")
    private String type;
    @JsonProperty("name")
    private String name;
    @JsonProperty("url")
    private String url;
    @JsonProperty("image")
    private String image;
    @JsonProperty("altImage")
    private String altImage;
    @JsonProperty("pricing_information")
    private PricingInformation pricingInformation;
    @JsonProperty("default_color")
    private String defaultColor;
    @JsonProperty("search_color")
    private String searchColor;
    @JsonProperty("source")
    private String source;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAltImage() {
        return altImage;
    }

    public void setAltImage(String altImage) {
        this.altImage = altImage;
    }

    public PricingInformation getPricingInformation() {
        return pricingInformation;
    }

    public void setPricingInformation(PricingInformation pricingInformation) {
        this.pricingInformation = pricingInformation;
    }

    public String getDefaultColor() {
        return defaultColor;
    }

    public void setDefaultColor(String defaultColor) {
        this.defaultColor = defaultColor;
    }

    public String getSearchColor() {
        return searchColor;
    }

    public void setSearchColor(String searchColor) {
        this.searchColor = searchColor;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

}
