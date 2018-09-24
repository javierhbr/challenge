
package com.challenge.productservice.domain.product;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;


public class ProductDescription {

    @JsonProperty("title")
    private String title;
    @JsonProperty("subtitle")
    private String subtitle;
    @JsonProperty("text")
    private String text;
    @JsonProperty("usps")
    private List<String> usps ;
//    @JsonProperty("description_assets")
//    private DescriptionAssets descriptionAssets;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<String> getUsps() {
        return usps;
    }

    public void setUsps(List<String> usps) {
        this.usps = usps;
    }

//    public DescriptionAssets getDescriptionAssets() {
//        return descriptionAssets;
//    }




}
