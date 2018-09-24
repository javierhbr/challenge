
package com.challenge.productservice.domain.product;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AttributeList {

    @JsonProperty("isWaitingRoomProduct")
    private Boolean isWaitingRoomProduct;

    @JsonProperty("brand")
    private String brand;

    @JsonProperty("collection")
    private List<String> collection ;

    @JsonProperty("category")
    private String category;

    @JsonProperty("color")
    private String color;

    @JsonProperty("gender")
    private String gender;

    @JsonProperty("personalizable")
    private Boolean personalizable;

    @JsonProperty("mandatory_personalization")
    private Boolean mandatoryPersonalization;

    @JsonProperty("customizable")
    private Boolean customizable;

    @JsonProperty("pricebook")
    private String pricebook;

    @JsonProperty("sale")
    private Boolean sale;

    @JsonProperty("outlet")
    private Boolean outlet;

    @JsonProperty("isCnCRestricted")
    private Boolean isCnCRestricted;

    @JsonProperty("sport")
    private List<String> sport ;

    @JsonProperty("size_chart_link")
    private String sizeChartLink;

    @JsonProperty("preview_to")
    private String previewTo;

    @JsonProperty("coming_soon_signup")
    private Boolean comingSoonSignup;

    @JsonProperty("hashtag")
    private String hashtag;

    @JsonProperty("productType")
    private List<String> productType ;

    @JsonProperty("sportSub")
    private List<String> sportSub ;

    @JsonProperty("search_color")
    private String searchColor;

        public Boolean getIsWaitingRoomProduct() {
        return isWaitingRoomProduct;
    }

    public void setIsWaitingRoomProduct(Boolean isWaitingRoomProduct) {
        this.isWaitingRoomProduct = isWaitingRoomProduct;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public List<String> getCollection() {
        return collection;
    }

    public void setCollection(List<String> collection) {
        this.collection = collection;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Boolean getPersonalizable() {
        return personalizable;
    }

    public void setPersonalizable(Boolean personalizable) {
        this.personalizable = personalizable;
    }

    public Boolean getMandatoryPersonalization() {
        return mandatoryPersonalization;
    }

    public void setMandatoryPersonalization(Boolean mandatoryPersonalization) {
        this.mandatoryPersonalization = mandatoryPersonalization;
    }

    public Boolean getCustomizable() {
        return customizable;
    }

    public void setCustomizable(Boolean customizable) {
        this.customizable = customizable;
    }

    public String getPricebook() {
        return pricebook;
    }

    public void setPricebook(String pricebook) {
        this.pricebook = pricebook;
    }

    public Boolean getSale() {
        return sale;
    }

    public void setSale(Boolean sale) {
        this.sale = sale;
    }

    public Boolean getOutlet() {
        return outlet;
    }

    public void setOutlet(Boolean outlet) {
        this.outlet = outlet;
    }

    public Boolean getIsCnCRestricted() {
        return isCnCRestricted;
    }

    public void setIsCnCRestricted(Boolean isCnCRestricted) {
        this.isCnCRestricted = isCnCRestricted;
    }

    public List<String> getSport() {
        return sport;
    }

    public void setSport(List<String> sport) {
        this.sport = sport;
    }

    public String getSizeChartLink() {
        return sizeChartLink;
    }

    public void setSizeChartLink(String sizeChartLink) {
        this.sizeChartLink = sizeChartLink;
    }

    public String getPreviewTo() {
        return previewTo;
    }

    public void setPreviewTo(String previewTo) {
        this.previewTo = previewTo;
    }

    public Boolean getComingSoonSignup() {
        return comingSoonSignup;
    }

    public void setComingSoonSignup(Boolean comingSoonSignup) {
        this.comingSoonSignup = comingSoonSignup;
    }

    public String getHashtag() {
        return hashtag;
    }

    public void setHashtag(String hashtag) {
        this.hashtag = hashtag;
    }

    public List<String> getProductType() {
        return productType;
    }

    public void setProductType(List<String> productType) {
        this.productType = productType;
    }

    public List<String> getSportSub() {
        return sportSub;
    }

    public void setSportSub(List<String> sportSub) {
        this.sportSub = sportSub;
    }

    public String getSearchColor() {
        return searchColor;
    }

    public void setSearchColor(String searchColor) {
        this.searchColor = searchColor;
    }

}
