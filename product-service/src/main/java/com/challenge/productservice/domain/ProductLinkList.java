
package com.challenge.productservice.domain;


public class ProductLinkList {

    private String type;
    private String name;
    private String url;
    private String image;
    private String altImage;
    private PricingInformation_ pricingInformation;
    private String defaultColor;
    private String searchColor;
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

    public PricingInformation_ getPricingInformation() {
        return pricingInformation;
    }

    public void setPricingInformation(PricingInformation_ pricingInformation) {
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
