
package com.challenge.productservice.domain.product;


import com.fasterxml.jackson.annotation.JsonProperty;


public class PricingInformation {

    @JsonProperty("standard_price")
    private Double standardPrice;
    @JsonProperty("standard_price_no_vat")
    private Double standardPriceNoVat;
    @JsonProperty("currentPrice")
    private Double currentPrice;


    public Double getStandardPrice() {
        return standardPrice;
    }

    public void setStandardPrice(Double standardPrice) {
        this.standardPrice = standardPrice;
    }

    public Double getStandardPriceNoVat() {
        return standardPriceNoVat;
    }

    public void setStandardPriceNoVat(Double standardPriceNoVat) {
        this.standardPriceNoVat = standardPriceNoVat;
    }

    public Double getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(Double currentPrice) {
        this.currentPrice = currentPrice;
    }

}
