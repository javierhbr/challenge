
package com.challenge.productservice.domain;


public class PricingInformation {

    private Double standardPrice;
    private Double standardPriceNoVat;
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
